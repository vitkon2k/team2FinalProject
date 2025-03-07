import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductFormComponent } from './components/product/product-form/product-form.component';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { LoginComponent } from './components/admin/login/login.component';
import { HeaderComponent } from './components/website/pages-layout/header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FooterComponent } from './components/website/pages-layout/footer/footer.component';
import { IndexComponent } from './components/website/index/index.component';
import { ProductDetailComponent } from './components/product/product-detail/product-detail.component';
import { LayoutAdminComponent } from './components/admin/layout/layout-admin/layout-admin.component';
import { LayoutPagesComponent } from './components/website/pages-layout/layout/layout.component';
import { AdminHeaderComponent } from './components/admin/layout/admin-header/admin-header.component';
import { AdminFooterComponent } from './components/admin/layout/admin-footer/admin-footer.component';
import { AdminSidebarComponent } from './components/admin/layout/admin-sidebar/admin-sidebar.component';
import { ProductFilterComponent } from './components/website/product-filter/product-filter.component';
import { AuthInterceptorService } from './auth/auth-interceptor.service';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdminOrderComponent } from './components/admin/admin-order/admin-order.component';
import { AdminProductComponent } from './components/admin/admin-product/admin-product.component';
import { AdminUserComponent } from './components/admin/admin-user/admin-user.component';
import { NewsListComponent } from './components/website/news/news-list/news-list.component';
import { NewsDetailComponent } from './components/website/news/news-detail/news-detail.component';
import { ContactComponent } from './components/website/contact/contact/contact.component';
import { PromotionComponent } from './components/website/promotion/promotion.component';
import { InstallmentComponent } from './components/website/installment/installment.component';
import { CheckoutComponent } from './components/website/checkout/checkout.component';

@NgModule({
  declarations: [
    AdminSidebarComponent,
    AdminFooterComponent,
    AdminHeaderComponent,
    AppComponent,
    ProductListComponent,
    ProductFormComponent,
    DashboardComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    IndexComponent,
    ProductDetailComponent,
    LayoutAdminComponent,
    LayoutPagesComponent,
    AdminHeaderComponent,
    AdminFooterComponent,
    AdminSidebarComponent,
    ProductFilterComponent,
    AdminOrderComponent,
    AdminProductComponent,
    AdminUserComponent,
    NewsListComponent,
    NewsDetailComponent,
    ContactComponent,
    PromotionComponent,
    InstallmentComponent,
    CheckoutComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterOutlet,
    FontAwesomeModule,
    RouterModule,
    CommonModule
  ],
  providers: [
   { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent, LayoutAdminComponent]
})
export class AppModule { }
