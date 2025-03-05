import { Component, Input, OnChanges } from '@angular/core';
import { Product } from 'src/app/models/product-model/product.model';
import { PagesService } from 'src/app/services/pages-service/pages.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnChanges {
  @Input() category_id: number | undefined;
  products: Product[] = [];

  constructor(private pageService: PagesService) {}

  ngOnChanges(): void {
    if (this.category_id) {
      this.loadProductsByCategory(this.category_id);
    }
  }

  loadProductsByCategory(categoryId: number): void {
    this.pageService.getProductsByCategoryId(categoryId).subscribe(
      data => {
        console.log("Dữ liệu API:", data);
        this.products = data.map(product => ({
          ...product,
        }));
      },
      error => console.log('Lỗi API:', error)
    );
  }
  
}
