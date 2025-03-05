import { Injectable } from '@angular/core';
import { ProductService } from '../product-services/product.service';
import { CategoryService } from '../category-services/category.service';
import { BrandService } from '../brand-services/brand.service';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private productService: ProductService, private categoryService: CategoryService, private brandService: BrandService) { }

  getProducts() {
    return this.productService.getProducts();
  }

  getProductById(id: number) {
    return this.productService.getProductById(id);
  }

  createProduct(product: any) {
    return this.productService.createProduct(product);
  }

  updateProduct(id: number, product: any) {
    return this.productService.updateProduct(id, product);
  }

  deleteProduct(id: number) {
    return this.productService.deleteProduct(id);
  }

  getCategories() {
    return this.categoryService.getCategories();
  }

  getCategoryById(id: number) {
    return this.categoryService.getCategoryById(id);
  }

  createCategory(category: any) {
    return this.categoryService.createCategory(category);
  }

  updateCategory(id: number, category: any) {
    return this.categoryService.updateCategory(id, category);
  }

  deleteCategory(id: number) {
    return this.categoryService.deleteCategory(id);
  }

  getBrands() {
    return this.brandService.getBrands();
  }

  getBrandById(id: number) {
    return this.brandService.getBrandById(id);
  }

  createBrand(brand: any) {
    return this.brandService.createBrand(brand);
  }

  updateBrand(id: number, brand: any) {
    return this.brandService.updateBrand(id, brand);
  }

  deleteBrand(id: number) {
    return this.brandService.deleteBrand(id);
  }
  


}
