export interface Product {
brand: any;
    id: number;
    name: string;
    description?: string;
    categoryId: number;
    brandId: number;
    price: number;
    discountPrice: number;
    stock: number;
    imageUrl: string;
    createdAt?: Date;
    updatedAt?: Date;
}
