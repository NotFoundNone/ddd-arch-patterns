package LayerdArchitecture.domain;

public class InventoryItem {
    private Product product;
    private int quantity;

    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public boolean reduceQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
