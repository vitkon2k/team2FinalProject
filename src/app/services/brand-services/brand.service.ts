import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Brand } from 'src/app/models/brand-model/brand.model';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  private apiUrl = 'http://localhost:8080/api/brands';

  constructor(private http:HttpClient) { }

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.apiUrl);
  }

  getBrandById(id: number): Observable<Brand> {
    return this.http.get<Brand>(`${this.apiUrl}/${id}`);
  }

  createBrand(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.apiUrl, brand);
  }

  updateBrand(id: number, brand: Brand): Observable<Brand> {
    return this.http.put<Brand>(`${this.apiUrl}/${brand.id}`, brand);
  }

  deleteBrand(id: number): Observable<Brand> {
    return this.http.delete<Brand>(`${this.apiUrl}/${id}`);
  }
}
