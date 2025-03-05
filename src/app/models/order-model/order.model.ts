import { User } from "../user-model/user.model";


export interface Order {
    id: number;
    totalPrice: number;
    orderStatus: string;
    shippingAddress: string;
    paymentStatus: string;
    createdAt: string;
    userId: User;
    order_status: 'pending' | 'shipped' | 'delivered' | 'canceled';
  }
  