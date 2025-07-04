import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginRequest, RegisterRequest, JwtResponse } from '../../models/auth.model';

const AUTH_API = 'http://localhost:8080/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) { }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  login(credentials: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(AUTH_API + 'signin', credentials).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userId', response.id.toString());
        localStorage.setItem('username', response.nombreUsuario);
        localStorage.setItem('userEmail', response.email);
        localStorage.setItem('userRole', response.rol);
        this.loggedIn.next(true);
      })
    );
  }

  register(user: RegisterRequest): Observable<any> {
    return this.http.post(AUTH_API + 'signup', user);
  }

  logout(): void {
    localStorage.clear(); // Limpia todo el almacenamiento local
    this.loggedIn.next(false);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserId(): number | null {
    const id = localStorage.getItem('userId');
    return id ? parseInt(id, 10) : null;
  }

  getUserRole(): string | null {
    return localStorage.getItem('userRole');
  }
}