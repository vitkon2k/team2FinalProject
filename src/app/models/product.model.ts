export interface Product {
    id: number;
    name: string;
    description?: string;
    category_id?: number;
    brand_id?: number;
    price: number;
    discount_price?: number;
    stock: number;
    image_url?: string;
    created_at?: Date;
    updated_at?: Date;

}
