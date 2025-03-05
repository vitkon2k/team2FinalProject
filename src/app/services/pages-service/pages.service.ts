import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../../models/product-model/product.model';
import { Brand } from 'src/app/models/brand-model/brand.model';
import { Category } from 'src/app/models/category-model/category.model';
import { News } from 'src/app/models/news-model/news.model';

@Injectable({
  providedIn: 'root'
})
export class PagesService {

  private productUrl = 'http://localhost:8080/public/product';
  private brandUrl = 'http://localhost:8080/public/brand';
  private categoryUrl = 'http://localhost:8080/public/category';
  private newsUrl = 'http://localhost:8080/public/news';

  constructor(private http:HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.productUrl}/${id}`);
  }

  getProductsByCategoryId(category_id: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productUrl}/category/${category_id}`);
  }

  getProductsByBrandId(brand_id: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productUrl}/brand/${brand_id}`);
  }

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.brandUrl);
  }

  getBrandById(id: number): Observable<Brand> {
    return this.http.get<Brand>(`${this.brandUrl}/${id}`);
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.categoryUrl);
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.categoryUrl}/${id}`);
  }

  getNews(): Observable<News[]> {
    return this.http.get<News[]>(this.newsUrl);
  }

  getNewsById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.newsUrl}/${id}`);
  }

}
