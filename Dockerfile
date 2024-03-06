# 빌드 스테이지
FROM openjdk:17-alpine as build
# 프로젝트 디렉토리 설정
WORKDIR /app

# Gradle wrapper 및 프로젝트 정의 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 프로젝트 의존성 캐싱을 위해 먼저 의존성 다운로드 실행
RUN ./gradlew --no-daemon dependencies

# 프로젝트 소스 복사 및 빌드, 테스트 스킵
COPY src src
RUN ./gradlew --no-daemon build -x test --stacktrace

# 실행 스테이지, JRE slim 버전 사용
FROM openjdk:17-alpine
# 애플리케이션 파일을 컨테이너에 복사
COPY --from=build ./build/libs/initial-0.0.1-SNAPSHOT-plain.jar /app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
