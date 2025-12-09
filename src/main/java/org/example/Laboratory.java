package org.example;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Laboratory {
    private final Map<String, Double> stock;

    public Laboratory(List<String> knownSubstances) {
        this.stock = new HashMap<>();
        for (String sub : knownSubstances) {
            this.stock.put(sub, 0.0);
        }
    }

    public double getQuantity(String substance) {
        if (!this.stock.containsKey(substance)) {
            throw new IllegalArgumentException("Unknown substance: " + substance);
        }
        return this.stock.get(substance);
    }

    public void add(String substance, double quantity) {
        if (!this.stock.containsKey(substance)) {
            throw new IllegalArgumentException("Unknown substance: " + substance);
        }

        double newQuantity = this.stock.get(substance) + quantity;
        this.stock.put(substance, newQuantity);
    }

}
