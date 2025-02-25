import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit { 
  product : Product = {
    id: 0,
    name: '',
    description: '',
    category_id: 0,
    brand_id: 0,
    price: 0,
    discount_price: 0,
    stock: 0,
    image_url: ''
  };

  isEdit :boolean = false;

  constructor(private productService: ProductService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      const idNumber = parseInt(id, 10);
      this.productService.getProductById(idNumber).subscribe(
        data => this.product = data,
        error => console.log(error));
    }
  }

  saveProduct(): void {
    if (this.isEdit) {
      this.productService.updateProduct(this.product.id, this.product).subscribe(
        () => this.router.navigate(['/products']),
        error => console.log(error)
      );
    } else {
      this.productService.createProduct(this.product).subscribe(
        () => this.router.navigate(['/products']),
        error => console.log(error)
      );
    }
  }
}
