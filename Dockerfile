# Usar la imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el jar de la aplicación en el contenedor desde la ruta correcta
COPY build/libs/Ucommerce-0.0.1-SNAPSHOT.jar /app/Ucommerce-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/Ucommerce-0.0.1-SNAPSHOT.jar"]