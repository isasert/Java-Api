# 1. Aşama: Build (Projeyi derleme)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Bağımlılıkları ve kaynak kodları kopyala
COPY pom.xml .
COPY src ./src
# Testleri atlayarak projeyi paketle
RUN mvn clean package -DskipTests

# 2. Aşama: Run (Projeyi çalıştırma)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Build aşamasında oluşan jar dosyasını kopyala
COPY --from=build /app/target/*.jar app.jar
# Render'ın dışarıya açacağı port
EXPOSE 8080
# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]