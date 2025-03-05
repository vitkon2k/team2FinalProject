import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product-model/product.model';
import { AdminService } from 'src/app/services/admin-services/admin.service';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {

  products: Product[] = [];
  brandNames: { [key: number]: string } = {}; 

  constructor(private adminService: AdminService ) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.adminService.getProducts().subscribe(
      data => {
        this.products = data;
        this.loadBrandNames();
      },
      error => console.log(error)
    );
  }

  loadBrandNames() {
    const requests = this.products.map(product => {
      if (product.brandId !== undefined) {
        return this.adminService.getBrandById(product.brandId).toPromise().then(
          brand => {
            if (product.brandId !== undefined) {
              if (brand) {
                this.brandNames[product.brandId] = brand.name;
              }
            }
          }
        ).catch(error => console.log("Lỗi lấy brand:", error));
      }
      return Promise.resolve();
    });
  
    Promise.all(requests)
  }

  deleteProduct(id: number) {
    this.adminService.deleteProduct(id).subscribe(
      data => {
        console.log(data);
        this.loadProducts();
      },
      error => console.log(error)
    );
  }
}