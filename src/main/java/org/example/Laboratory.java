package org.example;

import java.util.List;
import java.util.ArrayList;

public class Laboratory {
    private final List<String> knownSubstances;

    public Laboratory(List<String> substances) {
        this.knownSubstances = new ArrayList<>(substances);
    }

    public double getQuantity(String substance) {
        return 0.0;
    }
}