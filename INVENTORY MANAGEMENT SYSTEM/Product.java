public class Product {

    private int productId;
    private String productName;
    private String category;
    private int quantity;
    private double price;
    private String supplierName;

    public Product(
            String productName,
            String category,
            int quantity,
            double price,
            String supplierName){

        this.productName=productName;
        this.category=category;
        this.quantity=quantity;
        this.price=price;
        this.supplierName=supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplierName() {
        return supplierName;
    }
}