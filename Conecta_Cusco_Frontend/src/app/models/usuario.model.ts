export interface Usuario {
  id: number;
  nombreUsuario: string;
  email: string;
  nombreCompleto?: string;
  telefono?: string;
  direccion?: string;
  urlFotoPerfil?: string;
  rol: { id: number; nombre: string }; // 'TRABAJADOR' or 'EMPLEADOR'
}