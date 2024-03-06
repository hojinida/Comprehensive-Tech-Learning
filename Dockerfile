# 빌드 스테이지
FROM openjdk:17-alpine as build
RUN apk add --no-cache nodejs npm
RUN $JAVA_HOME/bin/jlink \
    --add-modules java.base,java.sql,java.logging,java.desktop \
    --output /jre

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
RUN ./gradlew --no-daemon build -x test


FROM alpine
COPY --from=build /jre /opt/jre
COPY --from=build /app/build/libs/my-application.jar /app.jar
ENTRYPOINT ["/opt/jre/bin/java", "-jar", "/app.jar"]
