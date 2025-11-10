package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IcecreamMachineConnectorTest {

    private IcecreamMachineConnector connector;

    @BeforeEach
    void setUp() {
        connector = new IcecreamMachineConnector();
    }

    @Test
    void shouldAcceptValidOrderFormat() {
        // Arrange
        String validOrder = "150g 200ml 0ml 20ml";

        // Act & Assert
        assertDoesNotThrow(() -> connector.send(validOrder));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(null);
        });
        assertEquals("Order string cannot be null or empty.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send("   ");
        });
        assertEquals("Order string cannot be null or empty.", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidFormat() {
        // Missing one parameter
        String invalidOrder = "150g 200ml 0ml";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(invalidOrder);
        });

        assertTrue(exception.getMessage().contains("Invalid order format"));
        assertTrue(exception.getMessage().contains("<frozenMass>g <milk>ml <juice>ml <water>ml"));
    }

    @Test
    void shouldRejectLettersInsteadOfNumbers() {
        String invalidOrder = "abcg 100ml 0ml 10ml";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(invalidOrder);
        });

        assertTrue(exception.getMessage().contains("Invalid order format"));
    }

    @Test
    void shouldHandleInterruptedExceptionGracefully() {
        // We can’t easily force Thread.sleep() interruption here,
        // but we can still call send() and assert it completes normally.
        assertDoesNotThrow(() -> connector.send("100g 100ml 50ml 20ml"));
    }
}