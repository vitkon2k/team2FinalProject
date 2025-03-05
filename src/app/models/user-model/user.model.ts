export interface User {
    id?: number;
    fullName: string;
    email: string;
    phone?: string;
    password?: string;
    address?: string;
    role: 'customer' | 'admin';
    createdAt?: string;
}
