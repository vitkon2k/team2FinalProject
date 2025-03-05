import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PagesService } from 'src/app/services/pages-service/pages.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product_id!: number;
  product: any;

  constructor(private route: ActivatedRoute, private pageService: PagesService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.product_id = Number(params.get('product_id'));
      this.loadProductDetail();
    });
  }

  loadProductDetail(): void {
    this.pageService.getProductById(this.product_id).subscribe(
      data => {
        this.product = data;
      },
      error => console.error("Error fetching product:", error)
    );
  }

  calculateDiscount(price: number, discount_price?: number): number {
    if (!discount_price || discount_price >= price) return 0;
    return Math.round(((price - discount_price) / price) * 100);
  }  

}
