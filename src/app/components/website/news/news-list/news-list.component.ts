import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewsService } from 'src/app/services/news-services/news.service';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {
  newsList: any[] = [];

  constructor(private newsService: NewsService, private router: Router) {}

  ngOnInit(): void {
    this.newsService.getNews().subscribe(data => {
      this.newsList = data;
    });
  }

  viewDetails(id: number) {
    this.router.navigate(['/news', id]);
  }
}
