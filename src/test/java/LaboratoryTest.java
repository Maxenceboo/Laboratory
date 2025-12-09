import org.example.Laboratory;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LaboratoryTest {

    @Test
    void should_initialize_laboratory_with_known_substances() {
        Laboratory lab = createSimpleLab("Water", "Carbon");
        assertNotNull(lab);
    }

    @Test
    void should_return_zero_quantity_for_existing_substance_initially() {
        Laboratory lab = createSimpleLab("Water");
        double quantity = lab.getQuantity("Water");
        assertEquals(0.0, quantity);
    }

    @Test
    void should_throw_exception_when_getting_quantity_of_unknown_substance() {
        Laboratory lab = createSimpleLab("Water");

        assertThrows(IllegalArgumentException.class, () -> {
            lab.getQuantity("Gold");
        });
    }

    @Test
    void should_update_quantity_when_adding_stock() {
        Laboratory lab = createSimpleLab("Water");

        lab.add("Water", 10.5);

        assertEquals(10.5, lab.getQuantity("Water"));
    }

    @Test
    void should_throw_exception_when_adding_unknown_substance() {
        Laboratory lab = createSimpleLab("Water");

        assertThrows(IllegalArgumentException.class, () -> {
            lab.add("Gold", 5.0);
        });
    }

    @Test
    void should_throw_exception_when_adding_negative_quantity() {
        Laboratory lab = createSimpleLab("Water");

        assertThrows(IllegalArgumentException.class, () -> {
            lab.add("Water", -5.0);
        });
    }

    @Test
    void should_initialize_with_reactions_and_products() {
        List<String> substances = Arrays.asList("Hydrogen", "Oxygen");

        Map<String, Double> waterIngredients = new HashMap<>();
        waterIngredients.put("Hydrogen", 2.0);
        waterIngredients.put("Oxygen", 1.0);

        Map<String, Map<String, Double>> reactions = new HashMap<>();
        reactions.put("Water", waterIngredients);

        Laboratory lab = new Laboratory(substances, reactions);

        assertNotNull(lab);
    }

    @Test
    void should_allow_adding_product_to_stock() {
        List<String> substances = Arrays.asList("Hydrogen", "Oxygen");

        Map<String, Double> waterIngredients = new HashMap<>();
        waterIngredients.put("Hydrogen", 2.0);

        Map<String, Map<String, Double>> reactions = new HashMap<>();
        reactions.put("Water", waterIngredients);

        Laboratory lab = new Laboratory(substances, reactions);

        lab.add("Water", 10.0);

        assertEquals(10.0, lab.getQuantity("Water"));
    }

    @Test
    void should_produce_requested_quantity_when_stock_is_sufficient() {
        Map<String, Double> waterIngredients = new HashMap<>();
        waterIngredients.put("Hydrogen", 2.0);
        waterIngredients.put("Oxygen", 1.0);

        Map<String, Map<String, Double>> reactions = new HashMap<>();
        reactions.put("Water", waterIngredients);

        Laboratory lab = new Laboratory(Arrays.asList("Hydrogen", "Oxygen"), reactions);

        lab.add("Hydrogen", 10.0);
        lab.add("Oxygen", 5.0);

        double produced = lab.make("Water", 2.0);

        assertEquals(2.0, produced, "Should produce exactly what is requested");

        assertEquals(6.0, lab.getQuantity("Hydrogen"));
        assertEquals(3.0, lab.getQuantity("Oxygen"));
        assertEquals(2.0, lab.getQuantity("Water"));
    }

    @Test
    void should_produce_limited_quantity_when_stock_is_insufficient() {
        Map<String, Double> ingredients = new HashMap<>();
        ingredients.put("Hydrogen", 2.0);

        Map<String, Map<String, Double>> reactions = new HashMap<>();
        reactions.put("Water", ingredients);

        Laboratory lab = new Laboratory(Arrays.asList("Hydrogen"), reactions);

        lab.add("Hydrogen", 2.0);

        double produced = lab.make("Water", 5.0);

        assertEquals(1.0, produced, "Should be limited by Hydrogen stock");
        assertEquals(0.0, lab.getQuantity("Hydrogen"), "Stock should be empty");
        assertEquals(1.0, lab.getQuantity("Water"), "Only 1 unit produced");
    }

    // --- Helper Methods ---

    private Laboratory createSimpleLab(String... substances) {
        return new Laboratory(Arrays.asList(substances), new HashMap<>());
    }
}
