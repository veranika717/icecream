package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.IcecreamMachineController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger  logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        IcecreamMachineController controller = null;

        String[] order = {"icecream", "milkshake", "smoothie"};
        controller.processOrder(order);

    }
}