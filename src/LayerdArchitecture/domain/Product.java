package LayerdArchitecture.domain;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private LocalDate expiryDate;
    private int criticalLevel;

    public Product(String id, String name, LocalDate expiryDate, int criticalLevel) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.criticalLevel = criticalLevel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }
}
