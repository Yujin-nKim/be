# 베이스 이미지
FROM openjdk:17-jdk-slim

# JAR 파일 복사
ARG JAR_FILE=build/libs/your-app.jar
COPY ${JAR_FILE} app.jar

# 포트
EXPOSE 8080

# 실행
ENTRYPOINT ["java","-jar","/app.jar"]