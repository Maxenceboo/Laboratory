package org.example;

import java.util.*;

public class Laboratory {

    private final Map<String, Double> stock;
    private final Map<String, Map<String, Double>> reactions;

    public Laboratory(List<String> knownSubstances, Map<String, Map<String, Double>> reactions) {
        this.stock = new HashMap<>();
        this.reactions = reactions;

        initializeStock(knownSubstances, reactions);
    }

    private void initializeStock(List<String> knownSubstances, Map<String, Map<String, Double>> reactions) {
        for (String sub : knownSubstances) {
            this.stock.put(sub, 0.0);
        }

        if (reactions != null) {
            for (String product : reactions.keySet()) {
                this.stock.putIfAbsent(product, 0.0);
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

    public double make(String productName, double requestedQuantity) {
        return makeRecursive(productName, requestedQuantity, new HashSet<>());
    }

    private double makeRecursive(String productName, double requestedQuantity, Set<String> visiting) {
        if (!this.reactions.containsKey(productName)) {
            return 0.0;
        }

        if (visiting.contains(productName)) {
            return 0.0;
        }

        visiting.add(productName);

        try {
            Map<String, Double> recipe = this.reactions.get(productName);

            for (Map.Entry<String, Double> ingredient : recipe.entrySet()) {
                String ingredientName = ingredient.getKey();
                double neededPerUnit = ingredient.getValue();

                double totalNeeded = neededPerUnit * requestedQuantity;
                double currentStock = this.stock.getOrDefault(ingredientName, 0.0);

                if (currentStock < totalNeeded) {
                    double missing = totalNeeded - currentStock;
                    if (this.reactions.containsKey(ingredientName)) {
                        makeRecursive(ingredientName, missing, visiting);
                    }
                }
            }

            double actualQuantity = calculateMaxProducible(recipe, requestedQuantity);
            consumeIngredients(recipe, actualQuantity);
            produceResult(productName, actualQuantity);

            return actualQuantity;

        } finally {
            visiting.remove(productName);
        }
    }

    private double calculateMaxProducible(Map<String, Double> recipe, double requestedQuantity) {
        double maxProducible = requestedQuantity;
        for (Map.Entry<String, Double> ingredient : recipe.entrySet()) {
            double available = this.stock.get(ingredient.getKey());
            double neededPerUnit = ingredient.getValue();
            double maxForIngredient = available / neededPerUnit;

            if (maxForIngredient < maxProducible) {
                maxProducible = maxForIngredient;
            }
        }
        return maxProducible;
    }

    private void consumeIngredients(Map<String, Double> recipe, double quantity) {
        for (Map.Entry<String, Double> ingredient : recipe.entrySet()) {
            String name = ingredient.getKey();
            double needed = ingredient.getValue() * quantity;
            this.stock.put(name, this.stock.get(name) - needed);
        }
    }

    private void produceResult(String productName, double quantity) {
        this.stock.put(productName, this.stock.get(productName) + quantity);
    }
}
