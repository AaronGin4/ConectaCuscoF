import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../core/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: false
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nombreUsuario: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', Validators.required],
      nombreCompleto: [''],
      telefono: [''],
      direccion: [''],
      urlFotoPerfil: [''],
      rol: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.successMessage = '';
    this.errorMessage = '';
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: data => {
          this.successMessage = '¡Registro exitoso! Ahora puedes iniciar sesión.';
          this.registerForm.reset(); // Limpiar el formulario
          // Opcionalmente, redirigir al login después de un tiempo
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: err => {
          // Manejo mejorado de errores
          if (err.error && typeof err.error === 'object') {
            // Si el backend devuelve un objeto con mensaje
            this.errorMessage = err.error.message || 'Ocurrió un error al registrarse. Intenta de nuevo.';
          } else if (typeof err.error === 'string') {
            // Si el backend devuelve un string
            this.errorMessage = err.error;
          } else if (err.status === 0) {
            // Error de red o CORS
            this.errorMessage = 'No se pudo conectar con el servidor. Verifica tu conexión o contacta al administrador.';
          } else {
            this.errorMessage = 'Ocurrió un error al registrarse. Intenta de nuevo.';
          }
          console.error('Error de registro', err);
        }
      });
    } else {
      this.errorMessage = 'Por favor, completa todos los campos requeridos correctamente.';
    }
  }
}