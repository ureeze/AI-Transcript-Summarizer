import { FormEvent, useMemo, useState } from 'react';

type SummarizeResponse = {
  summary: string[];
  keyPoints: string[];
  keywords: string[];
};

type ApiErrorResponse = {
  message?: string;
};

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080';
const YOUTUBE_URL_PATTERN =
  /^(https?:\/\/)?(www\.)?(youtube\.com\/watch\?v=|youtu\.be\/)[A-Za-z0-9_-]{11}.*$/;
const DEFAULT_ERROR_MESSAGE = '요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요.';

function isApiErrorResponse(value: unknown): value is ApiErrorResponse {
  return typeof value === 'object' && value !== null && 'message' in value;
}

async function readErrorMessage(response: Response) {
  try {
    const data: unknown = await response.json();
    if (isApiErrorResponse(data) && typeof data.message === 'string' && data.message.trim()) {
      return data.message;
    }
  } catch {
    return DEFAULT_ERROR_MESSAGE;
  }
  return DEFAULT_ERROR_MESSAGE;
}

function App() {
  const [youtubeUrl, setYoutubeUrl] = useState('');
  const [result, setResult] = useState<SummarizeResponse | null>(null);
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const trimmedUrl = youtubeUrl.trim();
  const isValidUrl = useMemo(() => YOUTUBE_URL_PATTERN.test(trimmedUrl), [trimmedUrl]);

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setResult(null);

    if (!isValidUrl) {
      setError('유효한 유튜브 영상 URL을 입력해주세요.');
      return;
    }

    setError('');
    setIsLoading(true);

    try {
      const response = await fetch(`${API_BASE_URL}/api/summarize`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ youtubeUrl: trimmedUrl }),
      });

      if (!response.ok) {
        setError(await readErrorMessage(response));
        return;
      }

      const data = (await response.json()) as SummarizeResponse;
      setResult(data);
    } catch {
      setError(DEFAULT_ERROR_MESSAGE);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <main className="min-h-screen bg-zinc-50 px-5 py-8 text-zinc-950">
      <section className="mx-auto max-w-3xl">
        <div className="mb-8">
          <p className="text-sm font-medium text-teal-700">AI Transcript Summarizer</p>
          <h1 className="mt-2 text-3xl font-semibold">유튜브 영상 요약기</h1>
        </div>

        <form className="rounded-lg border border-zinc-200 bg-white p-5 shadow-sm" onSubmit={handleSubmit}>
          <label className="text-sm font-medium text-zinc-800" htmlFor="youtubeUrl">
            유튜브 영상 URL
          </label>
          <div className="mt-3 flex flex-col gap-3 sm:flex-row">
            <input
              className="min-h-11 flex-1 rounded-md border border-zinc-300 px-3 text-base outline-none transition focus:border-teal-600 focus:ring-2 focus:ring-teal-100"
              id="youtubeUrl"
              name="youtubeUrl"
              onChange={(event) => setYoutubeUrl(event.target.value)}
              placeholder="https://www.youtube.com/watch?v=..."
              type="url"
              value={youtubeUrl}
            />
            <button
              className="min-h-11 rounded-md bg-zinc-950 px-5 text-sm font-semibold text-white transition hover:bg-zinc-800 disabled:cursor-not-allowed disabled:bg-zinc-400"
              disabled={isLoading}
              type="submit"
            >
              {isLoading ? '요약 중' : '요약하기'}
            </button>
          </div>
          {error ? <p className="mt-3 text-sm text-red-600">{error}</p> : null}
        </form>

        {result ? (
          <section className="mt-6 space-y-5">
            <div className="rounded-lg border border-zinc-200 bg-white p-5 shadow-sm">
              <h2 className="text-lg font-semibold">요약</h2>
              <ul className="mt-3 space-y-2 text-zinc-700">
                {result.summary.map((item) => (
                  <li key={item}>{item}</li>
                ))}
              </ul>
            </div>

            <div className="rounded-lg border border-zinc-200 bg-white p-5 shadow-sm">
              <h2 className="text-lg font-semibold">핵심 포인트</h2>
              <ul className="mt-3 list-disc space-y-2 pl-5 text-zinc-700">
                {result.keyPoints.map((item) => (
                  <li key={item}>{item}</li>
                ))}
              </ul>
            </div>

            <div className="rounded-lg border border-zinc-200 bg-white p-5 shadow-sm">
              <h2 className="text-lg font-semibold">키워드</h2>
              <div className="mt-3 flex flex-wrap gap-2">
                {result.keywords.map((keyword) => (
                  <span className="rounded-md bg-teal-50 px-2.5 py-1 text-sm font-medium text-teal-800" key={keyword}>
                    #{keyword}
                  </span>
                ))}
              </div>
            </div>
          </section>
        ) : null}
      </section>
    </main>
  );
}

export default App;
