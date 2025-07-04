# Despliegue Backend - Conecta Cusco

## Opción 1: Railway (Recomendado)

### Pasos para desplegar en Railway:

1. **Crear cuenta en Railway**
   - Ve a [railway.app](https://railway.app)
   - Regístrate con tu cuenta de GitHub

2. **Crear nuevo proyecto**
   - Haz clic en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Conecta tu repositorio de GitHub

3. **Configurar base de datos MySQL**
   - En tu proyecto de Railway, haz clic en "New"
   - Selecciona "Database" → "MySQL"
   - Railway te dará las credenciales automáticamente

4. **Configurar variables de entorno**
   - Ve a la pestaña "Variables"
   - Agrega las siguientes variables:
   ```
   SPRING_PROFILES_ACTIVE=prod
   DATABASE_URL=jdbc:mysql://tu-host-mysql:3306/tu-database
   DB_USERNAME=tu-username
   DB_PASSWORD=tu-password
   JWT_SECRET=TuClaveSecretaMuyLargaYSegura
   FRONTEND_URL=https://tu-frontend-url.railway.app
   ```

5. **Desplegar**
   - Railway detectará automáticamente que es un proyecto Spring Boot
   - El despliegue comenzará automáticamente

## Opción 2: Render

### Pasos para desplegar en Render:

1. **Crear cuenta en Render**
   - Ve a [render.com](https://render.com)
   - Regístrate con tu cuenta de GitHub

2. **Crear nuevo Web Service**
   - Haz clic en "New" → "Web Service"
   - Conecta tu repositorio de GitHub

3. **Configurar el servicio**
   - **Build Command**: `mvn clean install -DskipTests`
   - **Start Command**: `java -jar target/Conecta_Cusco-0.0.1-SNAPSHOT.jar`
   - **Environment**: Java

4. **Configurar base de datos**
   - Crea un nuevo "PostgreSQL" service
   - Usa las credenciales proporcionadas

5. **Variables de entorno**
   ```
   SPRING_PROFILES_ACTIVE=prod
   DATABASE_URL=jdbc:postgresql://tu-host:5432/tu-database
   DB_USERNAME=tu-username
   DB_PASSWORD=tu-password
   JWT_SECRET=TuClaveSecretaMuyLargaYSegura
   FRONTEND_URL=https://tu-frontend-url.onrender.com
   ```

## Opción 3: Heroku

### Pasos para desplegar en Heroku:

1. **Instalar Heroku CLI**
   - Descarga desde [heroku.com](https://heroku.com)

2. **Crear aplicación**
   ```bash
   heroku create tu-app-name
   ```

3. **Configurar base de datos**
   ```bash
   heroku addons:create cleardb:ignite
   ```

4. **Configurar variables**
   ```bash
   heroku config:set SPRING_PROFILES_ACTIVE=prod
   heroku config:set JWT_SECRET=TuClaveSecretaMuyLargaYSegura
   heroku config:set FRONTEND_URL=https://tu-frontend-url.herokuapp.com
   ```

5. **Desplegar**
   ```bash
   git push heroku main
   ```

## Notas importantes:

- Asegúrate de que tu repositorio esté en GitHub
- La base de datos debe estar configurada antes del despliegue
- Las variables de entorno son sensibles, no las compartas
- El JWT_SECRET debe ser una cadena larga y segura
- Actualiza la URL del frontend en las variables de entorno 