import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product-services/product.service';
import { Product } from 'src/app/models/product-model/product.model';
import { CategoryService } from 'src/app/services/category-services/category.service'; 
import { BrandService } from 'src/app/services/brand-services/brand.service'; 
import { Category } from 'src/app/models/category-model/category.model';
import { Brand } from 'src/app/models/brand-model/brand.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit { 
  product: Product = {} as Product;
  isEdit: boolean = false;
  selectedFile: File | null = null;
  categories: Category[] = [];
  brands: Brand[] = [];
  imagePreview: string | null = null;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private brandService: BrandService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.loadBrands();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      const idNumber = parseInt(id, 10);
      this.productService.getProductById(idNumber).subscribe(
        data => {
          this.product = data;
          this.imagePreview = this.product.imageUrl;
        },
        error => console.log(error)
      );
    }
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(
      data => this.categories = data,
      error => console.log(error)
    );
  }
  loadBrands(): void {
    this.brandService.getBrands().subscribe(
      data => this.brands = data,
      error => console.log(error)
    );
  }

  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];

      const reader = new FileReader();
      reader.onload = (e: any) => this.imagePreview = e.target.result;
      if (this.selectedFile) {
        reader.readAsDataURL(this.selectedFile);
      }
    }
  }

  saveProduct(): void {
    if (!this.product.name || !this.product.categoryId || !this.product.brandId || !this.product.price) {
      alert("Vui lòng điền đầy đủ thông tin sản phẩm!");
      return;
    }
  
    if (this.isEdit) {
      this.productService.updateProduct(this.product.id, this.product, this.selectedFile ?? undefined).subscribe(
        () => {
          alert('Sản phẩm đã được cập nhật thành công!');
          this.router.navigate(['/admin/products']);
        },
        error => {
          alert('Lỗi khi cập nhật sản phẩm!');
          console.log(error);
        }
      );
    } else {
      const formData = new FormData();
      formData.append('name', this.product.name);
      formData.append('description', this.product.description || '');
      formData.append('categoryId', this.product.categoryId.toString());
      formData.append('brandId', this.product.brandId.toString());
      formData.append('price', this.product.price.toString());
      formData.append('discountPrice', this.product.discountPrice.toString());
      formData.append('stock', this.product.stock.toString());
    
      if (this.selectedFile) {
        formData.append('image', this.selectedFile);
      }
  
      this.productService.createProduct(formData).subscribe(
        () => {
          alert('Sản phẩm đã được thêm mới thành công!');
          this.router.navigate(['/admin/products']);
        },
        error => {
          alert('Lỗi khi thêm sản phẩm!');
          console.log(error);
        }
      );
    }
  }
  
}
