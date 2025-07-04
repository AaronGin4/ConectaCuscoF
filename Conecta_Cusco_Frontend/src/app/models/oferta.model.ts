import { Usuario } from './usuario.model';
import { CategoriaServicio } from './categoria.model';

export interface OfertaTrabajo {
  id?: number;
  titulo: string;
  descripcion: string;
  presupuesto?: number;
  ubicacion?: string;
  fechaPublicacion?: string;
  usuario?: Usuario;
  categoria?: CategoriaServicio;
}