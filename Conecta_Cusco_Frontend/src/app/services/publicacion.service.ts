import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PublicacionServicio } from '../models/publicacion.model';
import { OfertaTrabajo } from '../models/oferta.model';

const API_URL = 'http://localhost:8080/api/publicaciones/';

@Injectable({
  providedIn: 'root'
})
export class PublicacionService {

  constructor(private http: HttpClient) { }

  // Métodos para PublicacionServicio
  getAllPublicacionesServicio(): Observable<PublicacionServicio[]> {
    return this.http.get<PublicacionServicio[]>(API_URL + 'servicios');
  }

  getPublicacionServicioById(id: number): Observable<PublicacionServicio> {
    return this.http.get<PublicacionServicio>(API_URL + 'servicios/' + id);
  }

  createPublicacionServicio(publicacion: any, userId: number, categoriaId: number): Observable<PublicacionServicio> {
    return this.http.post<PublicacionServicio>(`${API_URL}servicios/crear/${userId}/${categoriaId}`, publicacion);
  }

  updatePublicacionServicio(id: number, publicacion: PublicacionServicio): Observable<PublicacionServicio> {
    return this.http.put<PublicacionServicio>(API_URL + 'servicios/actualizar/' + id, publicacion);
  }

  deletePublicacionServicio(id: number): Observable<any> {
    return this.http.delete(API_URL + 'servicios/eliminar/' + id);
  }

  searchPublicacionesServicio(query: string): Observable<PublicacionServicio[]> {
    return this.http.get<PublicacionServicio[]>(`${API_URL}servicios/buscar?query=${query}`);
  }

  getPublicacionesServicioByCategoria(category: string): Observable<PublicacionServicio[]> {
    return this.http.get<PublicacionServicio[]>(`${API_URL}servicios/categoria?category=${category}`);
  }

  // Métodos para OfertaTrabajo
  getAllOfertasTrabajo(): Observable<OfertaTrabajo[]> {
    return this.http.get<OfertaTrabajo[]>(API_URL + 'ofertas');
  }

  getOfertaTrabajoById(id: number): Observable<OfertaTrabajo> {
    return this.http.get<OfertaTrabajo>(API_URL + 'ofertas/' + id);
  }

  createOfertaTrabajo(oferta: any, userId: number, categoriaId: number): Observable<OfertaTrabajo> {
    return this.http.post<OfertaTrabajo>(`${API_URL}ofertas/crear/${userId}/${categoriaId}`, oferta);
  }

  updateOfertaTrabajo(id: number, oferta: OfertaTrabajo): Observable<OfertaTrabajo> {
    return this.http.put<OfertaTrabajo>(API_URL + 'ofertas/actualizar/' + id, oferta);
  }

  deleteOfertaTrabajo(id: number): Observable<any> {
    return this.http.delete(API_URL + 'ofertas/eliminar/' + id);
  }

  searchOfertasTrabajo(query: string): Observable<OfertaTrabajo[]> {
    return this.http.get<OfertaTrabajo[]>(`${API_URL}ofertas/buscar?query=${query}`);
  }

  getOfertasTrabajoByCategoria(category: string): Observable<OfertaTrabajo[]> {
    return this.http.get<OfertaTrabajo[]>(`${API_URL}ofertas/categoria?category=${category}`);
  }
}