import { Component, OnInit } from '@angular/core';
import ApexCharts from 'apexcharts';
import { OverlayScrollbars } from 'overlayscrollbars';
import { AdminService } from 'src/app/services/admin-services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  readonly SELECTOR_SIDEBAR_WRAPPER = '.sidebar-wrapper';
  products: any[] = [];
  brandNames: { [key: string]: string } = {};
  readonly Default = {
    scrollbarTheme: 'os-theme-light',
    scrollbarAutoHide: 'leave',
    scrollbarClickScroll: true,
  };

  constructor(private adminService: AdminService) {
    this.adminService.getProducts().subscribe(
      data => {
        this.products = data;
        this.loadBrandNames();
      },
      error => console.log(error)
    );
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

  ngOnInit(): void {
    this.loadProducts();
    document.addEventListener('DOMContentLoaded', (event: Event) => {
      const sidebarWrapper = document.querySelector(this.SELECTOR_SIDEBAR_WRAPPER);
      if (sidebarWrapper && typeof OverlayScrollbars !== 'undefined') {
        OverlayScrollbars(sidebarWrapper as HTMLElement, {
          scrollbars: {
            theme: this.Default.scrollbarTheme,
            clickScroll: this.Default.scrollbarClickScroll,
          },
        });
      }
    });
    const visitors_chart_options = {
      series: [
        {
          name: 'High - 2023',
          data: [100, 120, 170, 167, 180, 177, 160],
        },
        {
          name: 'Low - 2023',
          data: [60, 80, 70, 67, 80, 77, 100],
        },
      ],
      chart: {
        height: 200,
        type: 'line',
        toolbar: {
          show: false,
        },
      },
      colors: ['#0d6efd', '#adb5bd'],
      stroke: {
        curve: 'smooth',
      },
      grid: {
        borderColor: '#e7e7e7',
        row: {
          colors: ['#f3f3f3', 'transparent'], 
          opacity: 0.5,
        },
      },
      legend: {
        show: false,
      },
      markers: {
        size: 1,
      },
      xaxis: {
        categories: ['22th', '23th', '24th', '25th', '26th', '27th', '28th'],
      },
    };

    const visitors_chart = new ApexCharts(
      document.querySelector('#visitors-chart'),
      visitors_chart_options,
    );
    visitors_chart.render();

    const sales_chart_options = {
      series: [
        {
          name: 'Net Profit',
          data: [44, 55, 57, 56, 61, 58, 63, 60, 66],
        },
        {
          name: 'Revenue',
          data: [76, 85, 101, 98, 87, 105, 91, 114, 94],
        },
        {
          name: 'Free Cash Flow',
          data: [35, 41, 36, 26, 45, 48, 52, 53, 41],
        },
      ],
      chart: {
        type: 'bar',
        height: 200,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: '55%',
          endingShape: 'rounded',
        },
      },
      legend: {
        show: false,
      },
      colors: ['#0d6efd', '#20c997', '#ffc107'],
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ['transparent'],
      },
      xaxis: {
        categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val: string) {
            return '$ ' + val + ' thousands';
          },
        },
      },
    };

    const sales_chart = new ApexCharts(
      document.querySelector('#sales-chart'),
      sales_chart_options,
    );
    sales_chart.render();
  }
}
