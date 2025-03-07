import { NgModule } from '@angular/core';
import { RouterModule, RouterOutlet, Routes } from '@angular/router';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductFormComponent } from './components/product/product-form/product-form.component';
import { IndexComponent } from './components/website/index/index.component';
import { LayoutAdminComponent } from './components/admin/layout/layout-admin/layout-admin.component';
import { LayoutPagesComponent } from './components/website/pages-layout/layout/layout.component';
import { ProductFilterComponent } from './components/website/product-filter/product-filter.component';
import { ProductDetailComponent } from './components/product/product-detail/product-detail.component';
import { LoginComponent } from './components/admin/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { AdminOrderComponent } from './components/admin/admin-order/admin-order.component';
import { AdminProductComponent } from './components/admin/admin-product/admin-product.component';
import { AdminUserComponent } from './components/admin/admin-user/admin-user.component';
import { NewsListComponent } from './components/website/news/news-list/news-list.component';
import { NewsDetailComponent } from './components/website/news/news-detail/news-detail.component';
import { ContactComponent } from './components/website/contact/contact/contact.component';
import { InstallmentComponent } from './components/website/installment/installment.component';
import { CheckoutComponent } from './components/website/checkout/checkout.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },

  {
    path: '',
    component: LayoutPagesComponent, 
    children: [
      { path: '', redirectTo: 'index', pathMatch: 'full' },
      { path: 'index', component: IndexComponent },
      { path: 'products/category/:category_id', component: ProductFilterComponent }, 
      { path: 'products/:product_id', component: ProductDetailComponent }, 
      { path: 'products', component: ProductListComponent },
      { path: 'news', component: NewsListComponent },
      { path: 'news/:id', component: NewsDetailComponent},
      { path: 'contact', component: ContactComponent },
      { path: 'installment', component: InstallmentComponent },
      { path: 'checkout', component: CheckoutComponent }
    ]
  },

  { 
    path: 'admin',
    component: LayoutAdminComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'orders', component: AdminOrderComponent },
      { path: 'products', component: AdminProductComponent },
      { path: 'products/add', component: ProductFormComponent },
      { path: 'products/edit/:id', component: ProductFormComponent },
      { path: 'users', component: AdminUserComponent}
    ]
  },

  { path: '**', redirectTo: 'index' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), RouterModule.forChild(routes), RouterOutlet],
  exports: [RouterModule]
})
export class AppRoutingModule { }
