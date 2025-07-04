export interface LoginRequest {
  nombreUsuario: string;
  contrasena: string;
}

export interface RegisterRequest {
  nombreUsuario: string;
  email: string;
  contrasena: string;
  nombreCompleto?: string;
  telefono?: string;
  direccion?: string;
  urlFotoPerfil?: string;
  rol: 'TRABAJADOR' | 'EMPLEADOR'; // Coincide con ERole del backend
}

export interface JwtResponse {
  token: string;
  type: string;
  id: number;
  nombreUsuario: string;
  email: string;
  rol: string; // Puede ser 'TRABAJADOR' o 'EMPLEADOR'
}