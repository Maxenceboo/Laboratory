import org.example.Laboratory;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class LaboratoryTest {

    @Test
    void should_initialize_laboratory_with_known_substances() {
        List<String> substances = Arrays.asList("Water", "Carbon");
        Laboratory lab = new Laboratory(substances);
        assertNotNull(lab);
    }

    @Test
    void should_return_zero_quantity_for_existing_substance_initially() {
        Laboratory lab = new Laboratory(Arrays.asList("Water"));
        double quantity = lab.getQuantity("Water");
        assertEquals(0.0, quantity);
    }

    @Test
    void should_throw_exception_when_getting_quantity_of_unknown_substance() {
        Laboratory lab = new Laboratory(Arrays.asList("Water"));

        assertThrows(IllegalArgumentException.class, () -> {
            lab.getQuantity("Gold"); // "Gold" n'a pas été initialisé
        });
    }

    @Test
    void should_update_quantity_when_adding_stock() {
        Laboratory lab = new Laboratory(Arrays.asList("Water"));

        lab.add("Water", 10.5);

        assertEquals(10.5, lab.getQuantity("Water"));
    }

    @Test
    void should_throw_exception_when_adding_unknown_substance() {
        Laboratory lab = new Laboratory(Arrays.asList("Water"));

        assertThrows(IllegalArgumentException.class, () -> {
            lab.add("Gold", 5.0);
        });
    }

    @Test
    void should_throw_exception_when_adding_negative_quantity() {
        Laboratory lab = new Laboratory(Arrays.asList("Water"));

        assertThrows(IllegalArgumentException.class, () -> {
            lab.add("Water", -5.0);
        });
    }

}
