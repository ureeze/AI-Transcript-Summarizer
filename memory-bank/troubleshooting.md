# Troubleshooting

## 2026-06-05 - 프론트엔드 Vite 타입 오류

### 상태

해결

### 문제

프론트엔드 `npm run build` 실행 시 Vite와 React DOM 타입에서 `@types/node`, Node built-in module, DOM 타입 관련 TypeScript 오류가 발생했다.

### 원인

`vite.config.ts`를 검사하는 `tsconfig.node.json`에 Node 타입 선언이 없었고, Vite 타입에서 필요한 `ESNext`/DOM lib 범위가 부족했다.

### 해결

- `frontend/package.json`에 `@types/node`를 추가했다.
- `frontend/tsconfig.node.json`의 `target`과 `lib`를 `ESNext` 기준으로 조정했다.
- `types`에 `node`를 추가했다.

### 재발 방지

Vite + TypeScript 프로젝트 생성 시 `@types/node`와 Node용 tsconfig 설정을 함께 추가한다.

---

## 2026-06-05 - 백엔드 Gradle Wrapper 누락

### 상태

해결

### 문제

백엔드 빌드 검증을 실행할 수 없었다.

### 원인

초기 Gradle 구조에는 Gradle Wrapper가 없었고, 현재 로컬 PATH에 Gradle CLI(`gradle`)가 설치되어 있지 않았다.

### 해결

Spring Initializr에서 Gradle 프로젝트 ZIP을 받아 `gradlew`, `gradlew.bat`, `gradle/wrapper/`를 추가했다. 이후 `.\gradlew.bat test`를 실행해 백엔드 테스트 성공을 확인했다.

### 재발 방지

Spring Boot 백엔드는 Initializr 기반으로 생성하고 Gradle Wrapper를 프로젝트에 포함한다. 백엔드 검증은 로컬 Gradle CLI가 아니라 `.\gradlew.bat test`로 수행한다.

---

## 2026-06-05 - Vite 환경변수 타입 오류

### 상태

해결

### 문제

프론트엔드 `npm run build` 실행 시 `import.meta.env` 타입 오류가 발생했다.

### 원인

Vite 환경변수 타입을 제공하는 `vite/client` 참조 선언 파일이 없었다.

### 해결

`frontend/src/vite-env.d.ts`를 추가하고 `/// <reference types="vite/client" />`를 선언했다.

### 재발 방지

Vite + TypeScript 프로젝트에는 `src/vite-env.d.ts`를 포함해 `import.meta.env` 타입을 사용할 수 있게 한다.

---

## 2026-06-13 - OpenAiSummaryClient Bean 생성 실패

### 상태

해결

### 문제

백엔드 `bootRun` 실행 시 `OpenAiSummaryClient` Bean 생성에 실패해 애플리케이션이 기동되지 않았다.

### 원인

`OpenAiSummaryClient`에 운영용 public 생성자와 테스트용 package-private 생성자가 함께 있었지만, Spring이 사용할 생성자가 명시되어 있지 않았다. 이로 인해 Spring이 기본 생성자를 찾으려다 실패했다.

### 해결

운영용 public 생성자에 `@Autowired`를 추가해 Spring DI 생성자를 명시했다. 또한 `OpenAiSummaryClientBeanTests`를 추가해 실제 Spring Context에서 Bean 생성이 가능한지 검증한다.

### 재발 방지

Spring Bean에 생성자가 여러 개 필요한 경우 운영 DI 생성자에 `@Autowired`를 명시하고, Bean 생성 테스트를 유지한다.

---

## 2026-06-13 - YouTube timedtext 빈 자막 응답

### 상태

관찰 중

### 문제

공개 자막이 있는 YouTube 영상으로 `/api/summarize`를 호출했지만 422 응답과 `"이 영상의 자막을 가져올 수 없습니다."` 메시지가 반환되었다.

### 원인

YouTube watch page에는 `captionTracks`가 포함되어 있었지만, 추출한 `timedtext` URL 호출 결과가 200 응답과 빈 본문을 반환했다. 여러 공개 영상과 `type=list` 엔드포인트에서도 동일하게 빈 본문이 반환되어, 현재 비공식 공개 자막 추출 방식의 외부 응답 제약 또는 구조 변경 영향으로 판단한다.

### 해결

자막 트랙 후보를 한국어, 영어, 나머지 트랙 순서로 시도하도록 fallback 로직을 추가했다. 특정 트랙이 빈 본문 또는 파싱 실패를 반환하면 다음 후보 트랙을 시도한다.

### 재발 방지

비공식 YouTube 자막 추출 방식은 실패 가능성을 전제로 검증한다. 로컬/배포 검증 시 실제 자막 응답 본문이 비어 있는지 확인하고, 계속 실패하면 공식 API 또는 별도 자막 입력 fallback 같은 대안을 검토한다.

---

## 2026-06-14 - Docker Compose 설정 검증 시 환경변수 값 출력

### 상태

해결

### 문제

`docker compose config` 실행 시 로컬 PowerShell에 설정된 `OPENAI_API_KEY` 값이 Compose 설정 출력에 펼쳐질 수 있다.

### 원인

`docker-compose.yml`에서 `OPENAI_API_KEY: ${OPENAI_API_KEY}`처럼 직접 interpolation을 사용하면 Docker Compose가 설정 렌더링 단계에서 실제 환경변수 값을 출력한다.

### 해결

백엔드 컨테이너의 `OPENAI_API_KEY` 전달 방식을 `env_file` 기반으로 변경해 실제 값을 Compose 파일에 직접 기록하지 않도록 했다. 실제 값은 커밋하지 않는 `deploy/.env`에 두고, 저장소에는 `deploy/.env.example`만 포함한다.

### 재발 방지

`env_file`을 사용하더라도 `docker compose config`는 `.env` 값을 렌더링할 수 있으므로 실제 API Key가 설정된 환경에서 실행한 출력 결과를 공유하지 않는다. 배포 Secret은 GitHub Secrets 또는 서버의 비공개 `.env` 파일로만 관리한다.

---

## 2026-06-15 - EC2 Docker Compose 서버 빌드 중 SSH 응답 불가

### 상태

해결

### 문제

AWS EC2 Amazon Linux 2023 서버에서 `docker compose up -d --build`를 실행한 뒤 SSH 접속이 `Connection timed out during banner exchange`로 실패했다.

### 원인

EC2 프리티어급 인스턴스에서 백엔드 Gradle 빌드와 프론트엔드 npm 빌드를 서버 내부에서 동시에 수행하면서 CPU 또는 메모리 자원이 고갈된 것으로 추정한다. 명령은 15분 이상 완료되지 않았고 이후 SSH banner 응답 단계에서도 타임아웃이 발생했다.

### 해결

AWS 콘솔에서 EC2 인스턴스를 재부팅한 뒤 SSH 접속이 복구되었다.

2026-06-15에 PR #15를 `develop`에 병합한 뒤 SSH 접속을 재확인했지만 `Connection timed out during banner exchange`가 다시 발생했다.

이후 다시 SSH 접속을 확인했을 때 정상 접속되었고, EC2 서버의 저장소를 최신 `develop`으로 fast-forward 동기화했다. 컨테이너는 실행 중이지 않았으며, Docker build cache가 약 257MB 남아 있었다.

### 재발 방지

EC2 프리티어 서버에서 직접 Docker 이미지를 빌드하지 않는다. 후속 작업에서는 GitHub Actions에서 이미지를 빌드해 Amazon ECR에 push하고, EC2에서는 `docker compose pull`과 `docker compose up -d`만 수행하는 방식으로 전환한다.

---

## 2026-06-16 - ECR 배포 검증을 위한 AWS 인증 정보 부재

### 상태

해결

### 문제

GitHub Actions + Amazon ECR 기반 배포 workflow를 실제 실행하려면 ECR repository 생성, AWS 인증 정보, GitHub Secrets 등록이 필요하지만 현재 Codex 세션에서는 해당 외부 설정을 완료할 수 없다.

### 원인

로컬 환경에는 `aws` CLI와 `gh` CLI가 설치되어 있지 않다. EC2에는 AWS CLI가 설치되어 있지만 `aws sts get-caller-identity` 실행 시 `Unable to locate credentials`가 발생해 AWS credentials 또는 IAM Role이 설정되어 있지 않음을 확인했다. 또한 현재 GitHub 커넥터에는 repository secrets 생성/수정 도구가 없다.

### 해결

AWS 콘솔과 GitHub repository Secrets에서 필요한 배포 설정을 수동으로 등록했다. 첫 GitHub Actions 실행은 `AWS_REGION` Secret 누락으로 `Input required and not supplied: aws-region` 오류가 발생했지만, `AWS_REGION=ap-northeast-2`를 추가한 뒤 재실행해 `Build and push images`와 `Deploy to EC2` job이 모두 성공했다.

배포 후 사용자가 EC2 public IP 기준으로 프론트엔드 화면 표시, YouTube URL 입력, 요약 요청 시 백엔드 API 호출, 실패 응답 시 에러 메시지 표시를 확인했다.

### 재발 방지

AWS 기반 CI/CD 검증 전에 ECR repository와 GitHub Secrets 목록을 체크리스트로 확인한다. 특히 `AWS_REGION`, AWS 인증 정보, EC2 SSH 접속 정보, `OPENAI_API_KEY`, `APP_CORS_ALLOWED_ORIGIN` 누락 여부를 먼저 확인한다. 외부 Secret 값은 채팅에 노출하지 않고 GitHub Secrets 또는 AWS IAM Role로 관리한다.

---

## 2026-06-16 - 운영 환경 direct transcript fallback 요약 실패

### 상태

해결

### 문제

`develop` 최신 커밋 `f63fb057a058fd4f174849a8fb04fc02b29287d0`가 EC2에 배포된 뒤 운영 환경을 검증했을 때, 루트 페이지 `GET /`는 200으로 응답했고 YouTube URL 실패 케이스는 `"이 영상의 자막을 가져올 수 없습니다."` 메시지로 정상 동작했다. 그러나 direct transcript 요청은 `"요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요."` 메시지와 함께 502 응답을 반환했다.

### 원인

운영 서버의 Git checkout과 frontend/backend 컨테이너 이미지는 모두 최신 `develop` SHA로 갱신되어 있었지만, EC2의 `deploy/.env`에 들어 있던 `OPENAI_API_KEY` 값이 로컬 정상 키와 달랐다. 이 값으로 direct transcript 요청을 수행하면 OpenAI가 인증을 거절해 백엔드가 `"요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요."`를 반환했다.

### 해결

EC2 서버의 `/home/ec2-user/apps/AI-Transcript-Summarizer/deploy/.env`에 로컬 PowerShell의 정상 `OPENAI_API_KEY`를 다시 반영하고 backend 컨테이너를 `docker compose up -d --force-recreate backend`로 재생성했다. 이후 direct transcript 요청이 200 응답과 요약 결과를 정상 반환하는 것을 확인했다.

### 재발 방지

운영 배포 후에는 `GET /` 확인만으로 끝내지 않고 다음 smoke test를 함께 수행한다. 또한 GitHub repository `OPENAI_API_KEY` Secret과 EC2 `deploy/.env` 값이 같은지 함께 관리해야 한다.

- YouTube URL 실패 응답 확인
- direct transcript fallback 요청 확인
- 성공 요약 응답 확인
