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
}