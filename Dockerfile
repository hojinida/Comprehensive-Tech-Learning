# Java 베이스 이미지 사용
FROM openjdk:17

# 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너로 복사
COPY ./build/libs/initial-0.0.1-SNAPSHOT-plain.jar /app/initial-0.0.1-SNAPSHOT-plain.jar

CMD ["java", "-jar", "/app/initial-0.0.1-SNAPSHOT-plain.jar"]
