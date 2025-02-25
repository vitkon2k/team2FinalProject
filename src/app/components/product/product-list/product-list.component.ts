import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  
  products: Product[] = [];

  constructor(private productService: ProductService) { }

  loadProducts(): void {
    this.productService.getProducts().subscribe(
      data => this.products = data,
      error => console.log(error)
    );
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  deleteProduct(id: number): void {
    if (confirm('Xác nhận xóa sản phẩm này?')){
      this.productService.deleteProduct(id).subscribe(
        () => this.loadProducts(),
        error => console.log(error)
      );
    }
  } 
}

