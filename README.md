# 중간 진행 상황(24.03.22)
## Frontend
### Progress
1. Why React: 프로젝트 구조 및 특성상 비동기 호출이 많고, 컴포넌트를 재사용 하여 구축하는 페이지가 많기 때문에 React를 사용
2. Why Vite: 짧은 서버 시작 시간, Hot Module Replacement(HMR), 확장성을 이유로 Vite 번들러 사용
- HMR: 앱 실행 중에도 코드 변경 사항을 즉시 반영하여 페이지 새로고침이 필요하지 않음
3. Progressive Web App: 서비스에 대한 접근성을 높이고, 웹과 앱 모두에서 동일한 서비스를 제공하기 위해 PWA 설정
- 업데이트를 위해 앱을 재설치할 필요가 없고, 푸쉬 알림 등 사용자 친화적인 기능 제공하여 UX 향상
4. Atomic Design: 프로젝트의 확장성 및 유지보수를 고려하여 디자인 설계 단계부터 Atomic Design 방법론을 적용
5. Storybook: 프론트엔드 협업 툴로 Storybook을 채택, 컴포넌트를 문서화하여 용도와 사용 방법, 그리고 디자인을 명확히 전달
- 컴포넌트의 재사용에 용이하고, 이를 가이드라인으로 활용하여 일관되고 통일성 있는 개발 진행
- 백엔드 등 다른 포지션의 개발자도 쉽게 파악할 수 있도록 디렉터리 구조화 진행
6. Typescript/Eslint: 코드의 안정성을 높이고, 에러가 발생할 수 있는 부분을 사전에 방지하기 위한 장치
- Eslint는 엄격한 Airbnb 방식으로 적용, 팀에 맞는 방식으로 커스텀 하여 사용
- 코드의 가독성을 높이고 일관성을 주기 위해 Prittier 함께 적용
7. Zustand: 상태 관리 라이브러리로 Zustand를 채택, 상태 관리 과정 및 코드 간소화
- 사용자의 로그인 상태, 양식의 입력 값 등을 항상 유지하기 위해 persist 적용
- 로컬에서 데이터를 바로 접근할 수 있어 로딩 시간 단축 및 상태 지속성 확보
8. Tailwind CSS/shadcn: Tailwind CSS 및 이를 기반으로 하는 라이브러리 사용으로 일관성 있는 디자인 시스템 구축

### Troubleshooting
1. StoryBook을 이용한 컴포넌트 문서화의 어려움
문제: 컴포넌트의 다양한 상태를 문서화하는 과정에서 StoryBook의 설정과 사용법에 대한 이해도가 낮아 효율적인 문서화가 이루어지지 않았습니다.
해결: StoryBook 공식 문서와 커뮤니티 가이드를 참고하여 학습을 강화했습니다. 특히, Args와 Controls 기능을 적극적으로 활용하여 인터랙티브한 컴포넌트 예시를 만들어내, 다른 개발자들이 컴포넌트를 쉽게 이해하고 사용할 수 있도록 했습니다.
2. 협업 시, 상이한 코드 작성 방식에 따른 어려움
문제: 컴포넌트를 재사용하거나 서로의 코드를 참고할 때, 코드 작성 방식이 달라 가독성이 떨어지고 파악하는데 시간이 오래 걸렸습니다.
해결: 클린 코드를 지향하고, 웬만하면 대부분의 코드에 주석을 달아놓음으로써 협업 및 의사소통의 효율성을 높이도록 했습니다.
3. 엄격한 Eslint 설정으로 인한 Jenkins 빌드 에러 발생
문제: 불필요한 코드를 허용하지 않는 등 Eslint를 엄격하게 적용한 탓에 빌드 시 에러가 발생하는 문제가 있었습니다.
해결: 'no-unused-vars': ['off']와 같이 .eslintrc 파일 내에 예외처리 코드 작성하여 해결할 수 있었습니다.
4. PWA 형식에 맞는 사이즈 제한의 어려움
문제: 앱 형식이다보니, 사이즈 별 출력을 어떤 방식으로 할지에 대해 고민이 많았습니다. 특히, 화면이 너무 작으면 내용이나 이미지가 깨지는 경우가 있었습니다.
해결: 최소 크기를 개발자 도구에서 가장 작은 폰인 Galaxy s8+(360*740)에 맞추고, 최대 크기는 모바일 환경의 최대 크기인 너비 574px에 맞췄습니다. 최소 크기 미만에서는 서비스를 사용할 수 없다는 안내를 띄우고, 최대 크기는 574px에 맞춰 더 이상 화면이 늘어나지 않도록 설정했습니다.
5. Tailwind `className`를 동적으로 활용하려는 부분에서 문제 발생
문제: atomic 구조로 컴포넌트를 관리하다보니, 같은 색상인데 각자 따로 prop하는 것이 비효율적이라 생각이 들었습니다. 예를 들어, 버튼에서 배경 색상과 호버 했을 때 반전된 글자 색상이 같은 red일 때, 한 번에 묶어서 prop 하고싶어서 `text-${color}` 형태로 만들었더니 인식을 하지 못하는 문제가 발생했습니다.
해결: Tailwind는 사용되지 않는 CSS를 제거하는데, 코드 컴파일시에 존재하는 `className`만을 수정할 수 있는 값으로 인지하기 때문에 `text-${color}` 형태는 처음에 text- 로 인식이 되어 제거 되는 탓에 수정할 수 없는 상황에 부딛히게 된 것이었습니다. 결론적으로, prop을 사용할 때는 아무리 귀찮더라도 온전한 className을 내려보내도록 설정했습니다.
6. Storybook에서 router 오류 발생
문제: atomic 구조를 활용하기 위해 Storybook을 도입했는데, router를 사용하는 부분에서 오류가 발생했습니다.
원인: Storybook에서 plugin, 또는 라이브러리 역할을 하는 addon이 존재합니다. "@storybook-addon-react-router"를 사용하면 된다고 파악했으나, 현재 이 addon은 Storybook 7버전까지 지원해서 버전 충돌이 발생합니다.(현재 버전 Storybook v.8)
해결: Storybook 공식 문서를 찾아본 결과, 같은 제작자가 addon-remix-react-router를 만들었는데, react-router를 업그레이드해서 만든 버전이라고 합니다. 현재 프로젝트에서는 remix를 사용하지 않아서 호환이 될까 걱정했으나, 다행히 addon이 정상작동해서 storybook에서 router를 사용한 컴포넌트도 에러 없이 불러올 수 있게 되었습니다.
7. Storybook에서 router 적용 방법
문제: router를 하나의 컴포넌트 stories 에서 적용하였는데, 매번 같은 코드가 중복되고, 작업이 반복되는 비효율성이 발생했습니다.
해결: 이를 전역적으로 적용할 수 있는 방법에 대해 알아보았고, storybook의 `decorators`와 `parameters`는 세가지 위치에서 적용 가능하다는 것을 알게 되었습니다. 첫 번째는 story 내부입니다. Story는 하나의 컴포넌트에서 나오는 Stories에 존재하는 하나의 예시입니다. 두 번째는 stories 에서 가능합니다. 하나의 컴포넌트에서 파생된 Stories에서 적용하면 하위 Story에 모두 적용됩니다. 세 번째는 Global로, 설정 페이지인 `.storybook/preview.tsx`에서 적용이 가능합니다. 이로 인해, Storybook을 더욱 효율적으로 사용할 수 있게 되었습니다.

### Overallall
- 기획한 디자인의 90% 완료 했으며, API 호출 및 데이터 연결은 아직 진행되지 않은 상태입니다.

## Backend
### Architecture
- DDD : 도메인 주도 개발에 따른 패키징 적용
- MSA : 트래킹 된 데이터로부터 기록 산출하는 서비스가 많은 트래픽과 리소스를 차지할 것이라고 예상했고, 그에 따라 SPOF 문제로 발전할 수 있다고 생각하여 MSA 채택
    - Eureka & Gateway
    - OpenFeign : 서버 간 REST 통신을 위해 OpenFeign 사용
- S3 : 경기당 동영상 또는 이미지들을 저장하기 위한 저장소
    - CloudFront : 동영상 캐싱처리 적용
    - FFMPeg : 동영상 썸네일 생성
- Jenkins : CI / CD 위한 툴
- Nginx : 리버스 프록시, SSL 적용
- Docker : 경량화된 컨테이너 기반으로 모든 서버 형상 관리

### Progress
1. [CI/CD 위한 각 스텝 간단 공부](https://www.notion.so/leeseny/CI-CD-fc972c295afb4984b926b269ead28f38?pvs=4)

각 서버 배포를 위한 툴들을 대략적인 흐름 위주로 공부 및 정리했습니다.

2. [각 서버 CI/CD 적용](https://www.notion.so/leeseny/ec2-bee13b973cdd46a89370604bd1581a77?pvs=4)

backend 및 frontend CI/CD를 적용하면서 사용했던 툴들의 버전이나 명령어들을 정리했습니다.

3. 기능에 따른 API를 개발 중입니다.
   <details>
   <summary>User 개발</summary>
      - 회원 정보수정
      - 회원 fcm 토큰 수정
      - 회원 정보 조회
      - 이메일 중복 확인
      - 닉네임 중복 확인
      - 임시 회원 로그인
      - 임시 회원가입
      - 스탯 누적
      - 스탯 평균값 조회
      - 스탯 전체값 조회
   </details>
   <details>
      <summary>Social 개발</summary>
      - 매치 생성
      - 매치 경기 동영상 업로드
      - 매치 ID를 통한 매치 경기 상세 조회
      - 회원별 참여한 매치 전체 조회
      - 분석 완료 시 경기 기록 할당
      - 개인 플레이어 기록 할당
      - 매니저 여부 조회
   </details>
   <details>
   <summary>calc 개발</summary>
      - 
   </details>
   <details>
   <summary>Common 개발</summary>
      - S3 객체 생성 및 동영상 업로드 기능 구현
      - OpenFeign Interface
      - Swagger UI
   </details>

4. 서버의 CI/ CD를 적용 중입니다.
   <details>
   <summary>CI / CD 진행사항</summary>
      - FRONTEND 
         - React 서버 `완료`
      - BACKEND 
         - Gateway 서버 `완료`
         - Eureka 서버 `완료`
         - User 서버 `완료`
         - Calc 서버 `완료`
         - Social 서버 `완료`
      - AI
         - Flask 서버 `진행중`
      - DB
         - User DB `완료`
         - Calc DB `완료`
         - Social DB `완료`
   </details>

### Troubleshooting
1. [jenkinsfile, dockerfile 이슈](https://www.notion.so/leeseny/Dockerfile-Jenkinsfile-dd2b7077cad5436594d2a44f3c2b6c9a?pvs=4)

   문제: 컨테이너와 이미지를 매번 배포 때마다 같은 이름을 사용하는데 있어서 삭제 처리를 해주지 않아 run을 시키지 못하는 에러 발생.<br>젠킨스 파일 문법 착오로 컨테이너 삭제가 제대로 되지 않는 문제 발생.

   해결: returnStatus 문법은 docker ps 시 컨테이너가 검색이 되지 않아도 정상 Status 반환을 합니다. 따라서 returnStdout으로 변경하여 검색이 되지 않으면 0이 반환되도록 해서 정상적으로 이전 컨테이너를 삭제 했습니다. image prune 문법을 사용하여 태그가 `<none>` 인 이미지들을 서버 배포 시 삭제하도록 했습니다. 

2. [MySQL 예약어 사용에 따른 오류 발생](https://velog.io/@gnoesnooj/ERROR-Mysql-JPA-Caused-by-java.sql.SQLSyntaxErrorException-)

3. [Entity 컬렉션 사용 시, update 시 `all-delete-orphan` 오류](https://velog.io/@gnoesnooj/ERROR-A-collection-with-cascadeall-delete-orphan-was-no-longer-referenced-by-the-owning-entity-instance)

4. gateway에서 호스트 못 찾는 오류
   
   해결 : `eureka.host.instance` 를 통해 호스트 지정하였습니다.

5. 동일 엔티티를 두 개 이상 참조 할 때 id 오류 발생
   - `@JoinColumn` 통한 다른 id 칼럼을 통해서 해결하였습니다.

6. Nginx 설정 파일이 적용이 안되는 이슈

   문제 : nginx 의 설정파일을 변경해도 SSL, 프록시 등 해당 사항들이 반영이 되지 않았습니다.

   해결 : nginx 컨테이너가 구동 시, ec2의 nginx 가 무시되므로 해당 변경 사항은 컨테이너에 적용되어야 합니다. 따라서 nginx 컨테이너를 수정하여 정상 적용하였습니다.

7. [S3 업로드 용량 제한 이슈](https://velog.io/@gnoesnooj/ERROR-Spring-MaxUploadSizeExceededException)

## AI
### Progress
1. 환경설정  
   python 3.9.12  
   cuda 12.1  
   cudnn 8700  
   Driver Version: 515.65.01  
2. 모델 선택
   - 초기 서비스 기획은 실시간 분석 서비스였습니다. 따라서 yolo 시리즈 모델을 선택했었습니다.
   - 저희의 서비스 특징인 탑뷰라는 환경에서의 detection 성능 짐작을 위해 간단하게 테스트가 필요해 작은 custom dataset을 만들어 yolov8 모델로 테스트를 진행하였습니다.
   - 서비스 기획이 실시간이 아닌 분석 시간을 가지는 서비스로 변경 되었고 굳이 1 stage 모델을 사용할 필요가 없어졌습니다.
   - 2 stage model이나 tiny object detection 모델을 염두 했으나 테스트에서 적은 데이터와 학습으로도 기대보다 잘 나와, 추가 data와 충분한 학습이 이루어지면 서비스에 필요한 만큼의 성능과 속도가 나올 것으로 판단되어 속도의 이점이 있는 yolo 모델을 그대로 사용했습니다.
   - 다만 서비스 특성상 속도보단 탐지 성능이 중요하므로 테스트에 쓰인 모델보다 조금 더 무거운 모델과 yolov9 모델을 함께 학습시키며 최종으로 사용할 모델을 선택할 예정입니다.
### Troubleshooting
- 환경설정 문제
   - GPU 환경설정에 이틀정도 소모되었습니다.
- Detection
   - 선수이슈
      - 탑 뷰 특성상 특정 각도에서 객체가 잘 안보여 탐지가 잘 안되는 경우 발생
      - 해결 : 적은 데이터로 적은 학습 후에 테스트에서 발생한 이슈라고 판단되어 데이터 양과 학습량을 늘리면 해결될 것이라고 예상
- 공 이슈
   - 크기
      - 약 30m~40m 상공에서 촬영한 풋살공(지름 약 17~21 cm)을 얼마나 잘 찾을 것인가 확인 필요
         - 해결 : 2 stage모델이나 tiny object detection model을 염두 했으나 기대보다 성능이 잘 나옴
   - 속도
      - 빠른 속도로 인해 프레임을 확인 했을 때 공이 늘어져서 보이는 현상
         - 늘어진 공과 선수 신체의 일부 혼동이 예상
      - 해결 : 데이터 양 증가와 후처리 -> 공 class 객체가 경기장 내에서 여러개 잡힐 경우 confidence score가 가장 높은 하나만 인식 
      - tracking 알고리즘은 객체의 이전 프레임의 bounding box와의 iou를 고려하므로 빠른 속도로 인해 이전 프레임과의 위치 차이가 커 끊어짐 없는 탐지에 영향
      - 해결 : 2안 중 확인 중
         1. 공만 tracking에서 제외 시키고 detection만 진행
         2. 공만 탐지하는 모델을 따로 사용
   - 가려짐
      - Top view 특성 상 player에 의해서 공이 가려지는 경우가 자주 발생
      - 해결 : 가려짐 특성상 이동이 크게 일어나지 않으므로 가려진 시점의 위치와 객체가 다시 보이는 시점의 위치 사이의 프레임의 위치 보간
- Tracking
   - tracking 중 객체를 잃었다가 다시 찾았을 때 새로운 객체로 인식
      - 자체적으로 생각한 방법
         - - 객체 classifier를 추가하여 객체를 잃었다가 다시 찾았을 때 기존 객체와의 유사도를 비교하여 기존 객체인지 판별
      - 전문가 리뷰
         - 위 방법은 그리 좋아보이지 않고 현재 프로젝트에 적용 시키기에 시간적, 리소스적 비용이 커 보임
         - object reidenfitication이라는 task가 있지만 현재 프로젝트에 적용시키기에는 배보다 배꼽이 더 커질 것 같아 추천하진 않음
         - 차라리 tracking 중 객체를 잃지 않는 부분에 더 신경을 쓰는 것 추천
      - 해결
         - 객체를 잃었다가 재탐지시 새 객체가 잃은 객체와 같은 class 일 경우 잃은 객체의 id 부여 -> tracking 성능이 생각보다 안나올 경우 개선 필요
- Image Processing
   - 경기장 탐지
      - opencv를 이용한 탐지를 하려했으나 전문가 리뷰에서 멘토님 의견을 반영하여 detection 모델이 찾는 방식으로 변경

## [✨team BABOO notion✨](https://leeseny.notion.site/2024-1f665d712e7a4cef98bbc8be9050c0b7?pvs=4)

## Git Commit Convention

### Commit Message 구조
type : 어떤 의도로 커밋했는지를 type 에 명시(ex. Feat, Fix, Docs)

Subject : 제목. 코드 변경사항에 대한 짧은 요약

body : 긴 설명이 필요한 경우, 어떻게 작성했는지가 아닌 무엇을 왜 했는지 를 작성(부연설명 혹은 커밋이유)

footer : issue tracker ID 를 명시하고 싶은 경우에 작성

### 타입(Commit Type)

- 태그(tag) + 제목(subject) 형식
- 첫 문자만 대문자
- "태그: 제목" 의 형태이며, ":" 뒤에 space 가 있음에 유의[ex) Feat: 회원가입 api (Feat 가 태그이고, 회원가입 api 가 제목)]

### 태그 종류

- Feat:	기능 추가, 삭제, 변경
- Fix:	버그, 오류 수정
- Docs:	README.md, json 파일 등 수정, 라이브러리 설치 (문서 관련, 코드 수정 없음)
- Style:	CSS 등 사용자 UI 디자인 변경 (제품 코드 수정 발생, 코드 형식, 정렬 등의 변경)
- Refactor:	코드 리팩토링
- Test:	테스트 코드 추가, 삭제, 변경 등 (코드 수정 없음, 테스트 코드에 관련된 모든 변경에 해당)
- Config:	npm 모듈 설치 등
- Chore:	패키지 매니저 설정할 경우, etc 등 (ex. gitignore)
- Comment:	필요한 주석 추가 및 변경
- Rename:	파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우
- Remove:	파일을 삭제하는 작업만 수행한 경우

### 제목

- 제목은 최대 50글자가 넘지 않고, 마침표 및 특수기호는 사용 금지
- 동사(원형)를 가장 앞에 두고 첫 글자는 대문자로 표기
- 제목은 개조식 구문으로 작성 --> 완전한 서술형 문장이 아니라, 간결하고 요점적인 서술을 의미.

* Fixed --> Fix
* Added --> Add
* Modified --> Modify

### 본문(Body)

- 본문은 한 줄 당 72자 내로 작성
- 본문 내용은 양에 구애받지 않고 최대한 상세히 작성
- 어떻게 변경했는지 보다 무엇을 변경했는지 또는 왜 변경했는지를 설명

### 꼬릿말(Footer)

- 꼬리말은 선택사항, 이슈 트래커 ID 작성
- "유형: #이슈 번호" 형식으로 사용
- 여러 개의 이슈 번호를 적을 때는 쉼표(,)로 구분
- 이슈 트래커 유형은 다음 중 하나를 사용

1. Fixes: 이슈 수정중 (아직 해결되지 않은 경우)
2. Resolves: 이슈를 해결했을 때 사용
3. Ref: 참고할 이슈가 있을 때 사용
4. Related to: 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우)
   ex) Fixes: #45 Related to: #34, #23
