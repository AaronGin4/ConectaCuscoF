import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PublicacionService } from '../../services/publicacion.service';
import { CategoriaService } from '../../services/categoria.service';
import { AuthService } from '../../core/auth/auth.service';
import { CategoriaServicio } from '../../models/categoria.model';

@Component({
  selector: 'app-publicar-servicio',
  templateUrl: './publicar-servicio.component.html',
  styleUrls: ['./publicar-servicio.component.css'],
  standalone: false
})
export class PublicarServicioComponent implements OnInit {
  publicacionForm!: FormGroup;
  categorias: CategoriaServicio[] = [];
  successMessage: string = '';
  errorMessage: string = '';
  userId: number | null = null; // Para almacenar el ID del usuario logueado

  constructor(
    private fb: FormBuilder,
    private publicacionService: PublicacionService,
    private categoriaService: CategoriaService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userId = this.authService.getUserId(); // Obtener el ID del usuario del servicio de autenticación

    this.publicacionForm = this.fb.group({
      tipoPublicacion: ['', Validators.required],
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      categoria: ['', Validators.required], // ID de la categoría
      ubicacion: [''],
      precio: [null], // Solo para PublicacionServicio
      presupuesto: [null] // Solo para OfertaTrabajo
    });

    this.loadCategorias();
  }

  loadCategorias(): void {
    this.categoriaService.getCategorias().subscribe({
      next: (data) => {
        this.categorias = data;
      },
      error: (err) => {
        console.error('Error al cargar categorías', err);
        this.errorMessage = 'No se pudieron cargar las categorías.';
      }
    });
  }

  onTipoPublicacionChange(): void {
    const tipo = this.publicacionForm.get('tipoPublicacion')?.value;
    if (tipo === 'servicio') {
      this.publicacionForm.get('presupuesto')?.clearValidators();
      this.publicacionForm.get('presupuesto')?.updateValueAndValidity();
      this.publicacionForm.get('precio')?.setValidators(Validators.min(0));
      this.publicacionForm.get('precio')?.updateValueAndValidity();
    } else if (tipo === 'oferta') {
      this.publicacionForm.get('precio')?.clearValidators();
      this.publicacionForm.get('precio')?.updateValueAndValidity();
      this.publicacionForm.get('presupuesto')?.setValidators(Validators.min(0));
      this.publicacionForm.get('presupuesto')?.updateValueAndValidity();
    }
  }

  onSubmit(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.publicacionForm.valid && this.userId) {
      const formValue = this.publicacionForm.value;
      const categoriaId = parseInt(formValue.categoria, 10); // Asegurarse de que sea un número

      if (formValue.tipoPublicacion === 'servicio') {
        const publicacionServicio = {
          titulo: formValue.titulo,
          descripcion: formValue.descripcion,
          precio: formValue.precio,
          ubicacion: formValue.ubicacion,
          // usuario y categoria se asignarán en el backend
        };
        this.publicacionService.createPublicacionServicio(publicacionServicio, this.userId, categoriaId).subscribe({
          next: () => {
            this.successMessage = '¡Servicio publicado exitosamente!';
            this.publicacionForm.reset();
            this.publicacionForm.get('tipoPublicacion')?.setValue('');
            this.publicacionForm.get('categoria')?.setValue('');
          },
          error: (err) => {
            this.errorMessage = 'Error al publicar el servicio.';
            console.error('Error al publicar servicio', err);
          }
        });
      } else if (formValue.tipoPublicacion === 'oferta') {
        const ofertaTrabajo = {
          titulo: formValue.titulo,
          descripcion: formValue.descripcion,
          presupuesto: formValue.presupuesto,
          ubicacion: formValue.ubicacion,
          // usuario y categoria se asignarán en el backend
        };
        this.publicacionService.createOfertaTrabajo(ofertaTrabajo, this.userId, categoriaId).subscribe({
          next: () => {
            this.successMessage = '¡Oferta de trabajo publicada exitosamente!';
            this.publicacionForm.reset();
            this.publicacionForm.get('tipoPublicacion')?.setValue('');
            this.publicacionForm.get('categoria')?.setValue('');
          },
          error: (err) => {
            this.errorMessage = 'Error al publicar la oferta de trabajo.';
            console.error('Error al publicar oferta de trabajo', err);
          }
        });
      }
    } else {
      this.errorMessage = 'Por favor, completa todos los campos requeridos correctamente.';
    }
  }
}