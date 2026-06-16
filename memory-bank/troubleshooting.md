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

미해결

### 문제

GitHub Actions + Amazon ECR 기반 배포 workflow를 실제 실행하려면 ECR repository 생성, AWS 인증 정보, GitHub Secrets 등록이 필요하지만 현재 Codex 세션에서는 해당 외부 설정을 완료할 수 없다.

### 원인

로컬 환경에는 `aws` CLI와 `gh` CLI가 설치되어 있지 않다. EC2에는 AWS CLI가 설치되어 있지만 `aws sts get-caller-identity` 실행 시 `Unable to locate credentials`가 발생해 AWS credentials 또는 IAM Role이 설정되어 있지 않음을 확인했다. 또한 현재 GitHub 커넥터에는 repository secrets 생성/수정 도구가 없다.

### 해결

아직 해결되지 않았다. AWS 콘솔에서 ECR repository 2개를 생성하고, GitHub repository Secrets에 AWS 인증 정보, EC2 접속 정보, OpenAI API Key를 등록해야 한다.

### 재발 방지

AWS 기반 CI/CD 검증 전에 AWS CLI 인증 방식 또는 EC2 IAM Role, GitHub Secrets 등록 권한을 먼저 준비한다. 외부 Secret 값은 채팅에 노출하지 않고 GitHub Secrets 또는 AWS IAM Role로 관리한다.
