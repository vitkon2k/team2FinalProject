import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product-model/product.model';
import { PagesService } from 'src/app/services/pages-service/pages.service';

@Component({
  selector: 'app-product-filter',
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.css']
})
export class ProductFilterComponent implements OnInit {
  category_id: number | undefined;
  categoryName: string = '';
  allProducts: Product[] = [];
  filteredProducts: Product[] = [];
  brands: { id: number, name: string }[] = [];
  selectedBrand: number | null = null;
  selectedPriceRange: { min: number, max: number } | null = null;

  priceRanges = [
    { label: 'Dưới 5 triệu', min: 0, max: 5000000 },
    { label: '5 - 10 triệu', min: 5000000, max: 10000000 },
    { label: '10 - 15 triệu', min: 10000000, max: 15000000 },
    { label: '15 - 20 triệu', min: 15000000, max: 20000000 },
    { label: 'Trên 20 triệu', min: 20000000, max: 100000000 }
  ];

  constructor(private pageService: PagesService, private route: ActivatedRoute, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.category_id = params['category_id'];
      if (this.category_id) {
        this.loadProductsByCategory(this.category_id.toString());
        this.loadCategoryById(Number(this.category_id));
      }
    });
    this.loadBrands();
  }
  
  

  loadProductsByCategory(categoryId: string): void {
    this.pageService.getProductsByCategoryId(Number(categoryId)).subscribe(
      data => {
        this.allProducts = data.map(product => ({
          ...product,
          discount: this.calculateDiscount(product.price, product.discountPrice)
        }));
        this.applyFilters();
        this.cdr.detectChanges();
      },
      error => console.log(error)
    );
  }
  

  loadCategoryById(category_id: number): void {
    this.pageService.getCategoryById(category_id).subscribe(
      data => this.categoryName = data.name,
      error => console.log(error)
    );
  }

  loadBrands(): void {
    this.pageService.getBrands().subscribe(
      data => this.brands = data,
      error => console.log(error)
    );
  }

  applyFilters(): void {
    this.filteredProducts = this.allProducts.filter(product => {
      const matchBrand = this.selectedBrand ? product.brandId === this.selectedBrand : true;
      const matchPrice = this.selectedPriceRange
        ? product.price >= this.selectedPriceRange.min && product.price <= this.selectedPriceRange.max
        : true;
      return matchBrand && matchPrice;
    });
  }

  filterByPrice(range: { min: number, max: number }): void {
    this.selectedPriceRange = range;
    this.applyFilters();
  }

  filterByBrand(brandId: number): void {
    this.selectedBrand = brandId;
    this.applyFilters();
  }

  calculateDiscount(price: number, discount_price?: number): number {
    if (!discount_price || discount_price >= price) return 0;
    return Math.round(((price - discount_price) / price) * 100);
  }
}
