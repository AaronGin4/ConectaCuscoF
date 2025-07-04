import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PublicarServicioComponent } from './pages/publicar-servicio/publicar-servicio.component';
import { BuscarServiciosComponent } from './pages/buscar-servicios/buscar-servicios.component';
import { AuthGuard } from './core/auth/auth.guard'; // Para proteger rutas

const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegisterComponent },
  { path: 'publicar-servicio', component: PublicarServicioComponent, canActivate: [AuthGuard] }, // Protegida
  { path: 'buscar-servicios', component: BuscarServiciosComponent },
  { path: '**', redirectTo: '/inicio' } // Ruta comodín para páginas no encontradas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }