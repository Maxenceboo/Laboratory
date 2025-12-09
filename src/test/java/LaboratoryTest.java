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

    // --- Helper Methods ---

    private Laboratory createSimpleLab(String... substances) {
        return new Laboratory(Arrays.asList(substances), new HashMap<>());
    }
}
