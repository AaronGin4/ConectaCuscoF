import { Component, OnInit } from '@angular/core';
import { PublicacionService } from '../../services/publicacion.service';
import { CategoriaService } from '../../services/categoria.service';
import { PublicacionServicio } from '../../models/publicacion.model';
import { OfertaTrabajo } from '../../models/oferta.model';
import { CategoriaServicio } from '../../models/categoria.model';

@Component({
  selector: 'app-buscar-servicios',
  templateUrl: './buscar-servicios.component.html',
  styleUrls: ['./buscar-servicios.component.css'],
  standalone: false
})
export class BuscarServiciosComponent implements OnInit {
  servicios: PublicacionServicio[] = [];
  ofertas: OfertaTrabajo[] = [];
  categorias: CategoriaServicio[] = [];
  searchTerm: string = '';
  selectedCategory: string = '';
  loadingServicios: boolean = false;
  loadingOfertas: boolean = false;

  constructor(
    private publicacionService: PublicacionService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit(): void {
    this.loadCategorias();
    this.loadAllPublicaciones();
  }

  loadCategorias(): void {
    this.categoriaService.getCategorias().subscribe({
      next: (data) => {
        this.categorias = data;
      },
      error: (err) => {
        console.error('Error al cargar categorías', err);
      }
    });
  }

  loadAllPublicaciones(): void {
    this.loadingServicios = true;
    this.loadingOfertas = true;
    this.publicacionService.getAllPublicacionesServicio().subscribe({
      next: (data) => {
        this.servicios = data;
        this.loadingServicios = false;
      },
      error: (err) => {
        console.error('Error al cargar servicios', err);
        this.loadingServicios = false;
      }
    });
    this.publicacionService.getAllOfertasTrabajo().subscribe({
      next: (data) => {
        this.ofertas = data;
        this.loadingOfertas = false;
      },
      error: (err) => {
        console.error('Error al cargar ofertas', err);
        this.loadingOfertas = false;
      }
    });
  }

  search(): void {
    this.loadingServicios = true;
    this.loadingOfertas = true;
    if (this.searchTerm) {
      this.publicacionService.searchPublicacionesServicio(this.searchTerm).subscribe({
        next: (data) => {
          this.servicios = data;
          this.loadingServicios = false;
        },
        error: (err) => {
          console.error('Error al buscar servicios', err);
          this.loadingServicios = false;
        }
      });
      this.publicacionService.searchOfertasTrabajo(this.searchTerm).subscribe({
        next: (data) => {
          this.ofertas = data;
          this.loadingOfertas = false;
        },
        error: (err) => {
          console.error('Error al buscar ofertas', err);
          this.loadingOfertas = false;
        }
      });
    } else if (this.selectedCategory) {
      this.publicacionService.getPublicacionesServicioByCategoria(this.selectedCategory).subscribe({
        next: (data) => {
          this.servicios = data;
          this.loadingServicios = false;
        },
        error: (err) => {
          console.error('Error al filtrar servicios por categoría', err);
          this.loadingServicios = false;
        }
      });
      this.publicacionService.getOfertasTrabajoByCategoria(this.selectedCategory).subscribe({
        next: (data) => {
          this.ofertas = data;
          this.loadingOfertas = false;
        },
        error: (err) => {
          console.error('Error al filtrar ofertas por categoría', err);
          this.loadingOfertas = false;
        }
      });
    } else {
      this.loadAllPublicaciones();
    }
  }
}