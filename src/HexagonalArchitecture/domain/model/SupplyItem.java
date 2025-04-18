package HexagonalArchitecture.domain.model;

public class SupplyItem {
    private final Product product;
    private final int quantity;

    public SupplyItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
               "product=" + product +
               ", quantity=" + quantity +
               '}';
    }
}
