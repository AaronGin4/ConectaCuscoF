import { Usuario } from './usuario.model';
import { CategoriaServicio } from './categoria.model';

export interface PublicacionServicio {
  id?: number;
  titulo: string;
  descripcion: string;
  precio?: number;
  ubicacion?: string;
  fechaPublicacion?: string; // Usar string y formatear en Angular si es necesario
  usuario?: Usuario; // Puede ser un objeto completo o solo el ID
  categoria?: CategoriaServicio; // Puede ser un objeto completo o solo el ID
}
