import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  currentIndex = 0;
  slides = [
    { image: 'assets/images/slide1.jpg', link: 'https://example.com/slide1' },
    { image: 'assets/images/slide2.jpg', link: 'https://example.com/slide2' },
    { image: 'assets/images/slide3.jpg', link: 'https://example.com/slide3' },
    { image: 'assets/images/slide4.jpg', link: 'https://example.com/slide3' }
  ];

  constructor() {
    setInterval(() => {
      this.nextSlide();
    }, 3000);
  }

  nextSlide() {
    this.currentIndex = (this.currentIndex + 1) % this.slides.length;
  }

  prevSlide() {
    this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
  }

  navigateTo(url: string) {
    window.location.href = url;
  }


}
