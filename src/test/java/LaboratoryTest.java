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
}