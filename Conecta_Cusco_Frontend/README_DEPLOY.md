# Despliegue Frontend - Conecta Cusco

## Opción 1: Railway (Recomendado)

### Pasos para desplegar en Railway:

1. **Crear cuenta en Railway**
   - Ve a [railway.app](https://railway.app)
   - Regístrate con tu cuenta de GitHub

2. **Crear nuevo proyecto**
   - Haz clic en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Conecta tu repositorio de GitHub (carpeta del frontend)

3. **Configurar variables de entorno**
   - Ve a la pestaña "Variables"
   - Agrega las siguientes variables:
   ```
   NODE_ENV=production
   API_URL=https://tu-backend-url.railway.app
   ```

4. **Desplegar**
   - Railway detectará automáticamente que es un proyecto Angular
   - El despliegue comenzará automáticamente

## Opción 2: Vercel (Muy fácil)

### Pasos para desplegar en Vercel:

1. **Crear cuenta en Vercel**
   - Ve a [vercel.com](https://vercel.com)
   - Regístrate con tu cuenta de GitHub

2. **Importar proyecto**
   - Haz clic en "New Project"
   - Importa tu repositorio de GitHub
   - Selecciona la carpeta del frontend

3. **Configurar build**
   - **Framework Preset**: Angular
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist/conecta-cusco-frontend`

4. **Variables de entorno**
   ```
   API_URL=https://tu-backend-url.railway.app
   ```

5. **Desplegar**
   - Haz clic en "Deploy"

## Opción 3: Netlify

### Pasos para desplegar en Netlify:

1. **Crear cuenta en Netlify**
   - Ve a [netlify.com](https://netlify.com)
   - Regístrate con tu cuenta de GitHub

2. **Importar proyecto**
   - Haz clic en "New site from Git"
   - Conecta tu repositorio de GitHub

3. **Configurar build**
   - **Build command**: `npm run build`
   - **Publish directory**: `dist/conecta-cusco-frontend`

4. **Variables de entorno**
   - Ve a Site settings → Environment variables
   ```
   API_URL=https://tu-backend-url.railway.app
   ```

5. **Desplegar**
   - Haz clic en "Deploy site"

## Opción 4: GitHub Pages

### Pasos para desplegar en GitHub Pages:

1. **Configurar Angular para GitHub Pages**
   ```bash
   ng add angular-cli-ghpages
   ```

2. **Construir y desplegar**
   ```bash
   ng deploy --base-href=https://tu-usuario.github.io/tu-repo/
   ```

3. **Configurar variables de entorno**
   - Crea un archivo `environment.prod.ts`:
   ```typescript
   export const environment = {
     production: true,
     apiUrl: 'https://tu-backend-url.railway.app'
   };
   ```

## Configuración adicional necesaria:

### 1. Actualizar environment.ts
Crea o actualiza el archivo `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'
};
```

### 2. Actualizar environment.prod.ts
Crea el archivo `src/environments/environment.prod.ts`:

```typescript
export const environment = {
  production: true,
  apiUrl: 'https://tu-backend-url.railway.app'
};
```

### 3. Actualizar angular.json
Asegúrate de que tu `angular.json` tenga la configuración correcta:

```json
{
  "projects": {
    "conecta-cusco-frontend": {
      "architect": {
        "build": {
          "configurations": {
            "production": {
              "fileReplacements": [
                {
                  "replace": "src/environments/environment.ts",
                  "with": "src/environments/environment.prod.ts"
                }
              ]
            }
          }
        }
      }
    }
  }
}
```

## Notas importantes:

- Asegúrate de que tu repositorio esté en GitHub
- Actualiza la URL del backend en las variables de entorno
- El frontend debe apuntar a la URL correcta del backend desplegado
- Verifica que CORS esté configurado correctamente en el backend
- Prueba la aplicación después del despliegue 