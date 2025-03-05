import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category-model/category.model';
import { PagesService } from 'src/app/services/pages-service/pages.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  category: Category[] = [];

  constructor(private pageService: PagesService, private router:Router) { }

  ngOnInit(): void {
    this.pageService.getCategories().subscribe(
      data => this.category = data,
      error => console.log(error)
    );
  }

  goToCategory(categoryId: number): void {
    this.router.navigate(['/products', categoryId]);
  }

}
