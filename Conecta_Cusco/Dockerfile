# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Crea el directorio de la app
WORKDIR /app

# Copia el pom.xml y el wrapper de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Da permisos de ejecución a mvnw
RUN chmod +x mvnw

# Descarga las dependencias
RUN ./mvnw dependency:go-offline -B

# Copia el resto del código
COPY . .

# Construye el proyecto
RUN ./mvnw clean install -DskipTests

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "target/Conecta_Cusco-0.0.1-SNAPSHOT.jar"] 