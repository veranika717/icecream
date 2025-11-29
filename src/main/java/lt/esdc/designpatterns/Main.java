package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.IcecreamMachineController;
import lt.esdc.designpatterns.controller.IcecreamMachineControllerImpl;
import lt.esdc.designpatterns.machine.IcecreamMachineConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger  logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        IcecreamMachineConnector machine = new IcecreamMachineConnector();
        IcecreamMachineController controller = new IcecreamMachineControllerImpl(machine);

        String[] order = {"icecream", "milkshake", "smoothie"};
        controller.processOrder(order);

        logger.info("All orders processed!");
    }
}