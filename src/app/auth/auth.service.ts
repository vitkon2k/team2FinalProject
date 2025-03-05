import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUser(): any {
    const token = this.getToken();
    return token ? jwtDecode(token) : null;
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
