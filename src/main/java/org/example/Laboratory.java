package org.example;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Laboratory {

    private final Map<String, Double> stock;
    private final Map<String, Map<String, Double>> reactions;

    public Laboratory(List<String> knownSubstances, Map<String, Map<String, Double>> reactions) {
        this.stock = new HashMap<>();
        this.reactions = reactions;

        for (String sub : knownSubstances) {
            this.stock.put(sub, 0.0);
        }

        if (reactions != null) {
            for (String product : reactions.keySet()) {
                this.stock.put(product, 0.0);
            }
        }
    }

    private void ensureSubstanceExists(String substance) {
        if (!this.stock.containsKey(substance)) {
            throw new IllegalArgumentException("Unknown substance: " + substance);
        }
    }

    public double getQuantity(String substance) {
        ensureSubstanceExists(substance);
        return this.stock.get(substance);
    }

    public void add(String substance, double quantity) {
        ensureSubstanceExists(substance);

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        double newQuantity = this.stock.get(substance) + quantity;
        this.stock.put(substance, newQuantity);
    }

}
