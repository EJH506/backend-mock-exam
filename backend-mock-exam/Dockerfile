# Java 17용 OpenJDK 기본 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 jar 파일을 복사
COPY build/libs/backend-mock-exam-0.0.1-SNAPSHOT.jar app.jar

# 앱이 실행될 포트
EXPOSE 8080

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]