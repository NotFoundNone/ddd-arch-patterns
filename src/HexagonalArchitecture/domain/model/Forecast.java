package HexagonalArchitecture.domain.model;

import java.util.Map;

public class Forecast {
    private Map<String, Integer> productDemand; // productId -> требуемое количество
    
    public Forecast(Map<String, Integer> productDemand) {
        this.productDemand = productDemand;
    }
    
    public Map<String, Integer> getProductDemand() {
        return productDemand;
    }
    
    @Override
    public String toString() {
        return "Forecast{" +
                "productDemand=" + productDemand +
                '}';
    }
}
