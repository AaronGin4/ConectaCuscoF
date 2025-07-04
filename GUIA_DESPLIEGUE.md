# 🚀 Guía Completa de Despliegue - Conecta Cusco

## 📋 Resumen del Proyecto
- **Backend**: Spring Boot 3.5.3 + MySQL + JWT
- **Frontend**: Angular 20 + TypeScript
- **Base de datos**: MySQL

## 🎯 Opción Recomendada: Railway

Railway es la opción más fácil y gratuita para desplegar tu aplicación completa.

### Paso 1: Preparar el Repositorio

1. **Subir tu código a GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/tu-usuario/tu-repositorio.git
   git push -u origin main
   ```

### Paso 2: Desplegar el Backend

1. **Ir a Railway**
   - Ve a [railway.app](https://railway.app)
   - Regístrate con tu cuenta de GitHub

2. **Crear proyecto para el backend**
   - Haz clic en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Selecciona tu repositorio
   - **IMPORTANTE**: En "Root Directory" especifica: `Conecta_Cusco`

3. **Configurar base de datos MySQL**
   - En tu proyecto, haz clic en "New"
   - Selecciona "Database" → "MySQL"
   - Railway te dará las credenciales automáticamente

4. **Configurar variables de entorno**
   - Ve a la pestaña "Variables"
   - Agrega estas variables:
   ```
   SPRING_PROFILES_ACTIVE=prod
   DATABASE_URL=jdbc:mysql://tu-host-mysql:3306/tu-database
   DB_USERNAME=tu-username
   DB_PASSWORD=tu-password
   JWT_SECRET=TuClaveSecretaMuyLargaYSeguraParaJWT123456789
   FRONTEND_URL=https://conecta-cusco-frontend.railway.app
   ```

5. **Desplegar**
   - Railway detectará automáticamente que es Spring Boot
   - El despliegue comenzará automáticamente
   - Anota la URL que te da Railway (ej: `https://conecta-cusco-backend.railway.app`)

### Paso 3: Desplegar el Frontend

1. **Crear nuevo proyecto en Railway para el frontend**
   - Haz clic en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Selecciona el mismo repositorio
   - **IMPORTANTE**: En "Root Directory" especifica: `Conecta_Cusco_Frontend`

2. **Configurar variables de entorno**
   - Ve a la pestaña "Variables"
   - Agrega estas variables:
   ```
   NODE_ENV=production
   API_URL=https://conecta-cusco-backend.railway.app
   ```

3. **Actualizar environment.prod.ts**
   - Edita el archivo `Conecta_Cusco_Frontend/src/environments/environment.prod.ts`
   - Cambia la URL por la que te dio Railway para el backend

4. **Desplegar**
   - Railway detectará automáticamente que es Angular
   - El despliegue comenzará automáticamente

## 🔧 Configuración Adicional

### Actualizar CORS en el Backend

Si tienes problemas de CORS, actualiza el archivo `SecurityConfig.java`:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("https://conecta-cusco-frontend.railway.app"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```

### Verificar el Despliegue

1. **Backend**: Ve a `https://tu-backend-url.railway.app/actuator/health`
2. **Frontend**: Ve a `https://tu-frontend-url.railway.app`

## 🌐 Otras Opciones de Hosting

### Opción 2: Render
- **Backend**: [render.com](https://render.com) - Web Service
- **Frontend**: [render.com](https://render.com) - Static Site
- **Base de datos**: PostgreSQL (gratis)

### Opción 3: Vercel + Railway
- **Backend**: Railway
- **Frontend**: [vercel.com](https://vercel.com) (muy fácil para Angular)
- **Base de datos**: MySQL en Railway

### Opción 4: Heroku
- **Backend**: [heroku.com](https://heroku.com)
- **Frontend**: Heroku o Vercel
- **Base de datos**: ClearDB MySQL (addon de Heroku)

## 🚨 Problemas Comunes y Soluciones

### Error de CORS
- Verifica que las URLs en CORS coincidan exactamente
- Asegúrate de que el frontend use HTTPS si el backend está en HTTPS

### Error de Base de Datos
- Verifica que las credenciales de la base de datos sean correctas
- Asegúrate de que la base de datos esté creada y accesible

### Error de Build
- Verifica que todas las dependencias estén en el `pom.xml` y `package.json`
- Revisa los logs de build en la plataforma de hosting

### Error de JWT
- Asegúrate de que el `JWT_SECRET` sea una cadena larga y segura
- Verifica que el mismo secret se use en desarrollo y producción

## 📞 Soporte

Si tienes problemas:
1. Revisa los logs en la plataforma de hosting
2. Verifica que todas las variables de entorno estén configuradas
3. Asegúrate de que el repositorio esté actualizado en GitHub

## 🎉 ¡Listo!

Una vez desplegado, tu aplicación estará disponible en:
- **Frontend**: `https://conecta-cusco-frontend.railway.app`
- **Backend**: `https://conecta-cusco-backend.railway.app`

¡Tu aplicación Conecta Cusco estará en línea! 🚀 