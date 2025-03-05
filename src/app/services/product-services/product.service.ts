import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../../models/product-model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/admin/product';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  createProduct(formData: FormData): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, formData);
  }

  updateProduct(id: number, product: Product, imageFile?: File): Observable<Product> {
    const formData = new FormData();
    formData.append('name', product.name);
    if (product.description) {
      formData.append('description', product.description);
    }
    formData.append('categoryId', product.categoryId.toString());
    formData.append('brandId', product.brandId.toString());
    formData.append('price', product.price.toString());
    formData.append('discountPrice', product.discountPrice.toString());
    formData.append('stock', product.stock.toString());
  
    if (imageFile) {
      formData.append('image', imageFile);
    }
  
    return this.http.put<Product>(`${this.apiUrl}/${id}`, formData);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
