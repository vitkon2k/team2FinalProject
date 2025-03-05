import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order-services/order.service';
import { Order } from 'src/app/models/order-model/order.model';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['./admin-order.component.css']
})
export class AdminOrderComponent implements OnInit {

  orders: Order[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.orderService.getOrders().subscribe(
      data => this.orders = data,
      error => console.log(error)
    );
  }

  viewOrder(id: number) {
    console.log(`Xem chi tiết đơn hàng ${id}`);
  }

  cancelOrder(id: number) {
    if (confirm('Bạn có chắc chắn muốn hủy đơn hàng này?')) {
      this.orderService.cancelOrder(id).subscribe(() => {
        this.orders = this.orders.map(order => 
          order.id === id ? { ...order, order_status: 'canceled' } : order
        );
      });
    }
  }
}
