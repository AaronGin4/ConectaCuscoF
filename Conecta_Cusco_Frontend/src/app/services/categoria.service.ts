import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriaServicio } from '../models/categoria.model';

const API_URL = 'http://localhost:8080/api/'; // Ajusta esta URL si tus categorías están en otro endpoint

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private http: HttpClient) { }

  // Asumamos que tenemos un endpoint para categorías, si no, puedes crearlo en el backend.
  // Por simplicidad, podríamos agregar un endpoint `/api/categorias` en Spring Boot.
  // Si no tienes un controlador específico de categorías, puedes simularlo o añadirlos.
  // Para este ejemplo, haremos una llamada directa.
  getCategorias(): Observable<CategoriaServicio[]> {
    // Si necesitas un endpoint específico, añade uno en Spring Boot, ej: @GetMapping("/api/categorias")
    // Por ahora, simularemos con un endpoint general si no existe uno dedicado.
    // O puedes crear un controlador `CategoriaController` en Spring Boot.
    // Dado que las categorías son fijas, también podrías cargarlas desde un JSON local.
    // Para simplificar, asumiremos que existe un endpoint en Spring Boot:
    return this.http.get<CategoriaServicio[]>(API_URL + 'categorias');
  }
}