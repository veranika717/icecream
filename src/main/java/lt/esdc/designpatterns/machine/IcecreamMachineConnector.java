package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IcecreamMachineConnector implements IcecreamMachineV16 {

    // Regex for pattern: "<number>g <number>ml <number>ml <number>ml"
    private static final String PATTERN = "^\\d+g \\d+ml \\d+ml \\d+ml$";

    private static final Logger logger = LoggerFactory.getLogger(IcecreamMachineConnector.class);

    @Override
    public void send(String order) {

        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!order.matches(PATTERN)) {
            throw new IllegalArgumentException(
                    "Invalid order format: " + order +
                            ". Expected format: <frozenMass>g <milk>ml <juice>ml <water>ml " +
                            "(e.g. '150g 200ml 0ml 20ml')."
            );
        }

        logger.info("🍨 Preparing dessert: " + order);
        logger.info("Mixing ingredients...");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Machine interrupted while preparing dessert.", e);
        }
        logger.info("Dessert ready! Enjoy your treat!");
    }
}
