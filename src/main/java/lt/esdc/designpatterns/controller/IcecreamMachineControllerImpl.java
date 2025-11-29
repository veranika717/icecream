package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.factory.DessertFactory;
import lt.esdc.designpatterns.factory.FactoryProvider;
import lt.esdc.designpatterns.machine.IcecreamMachineV16;
import lt.esdc.designpatterns.model.DessertRecipe;
import lt.esdc.designpatterns.types.DessertType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class IcecreamMachineControllerImpl implements IcecreamMachineController {

    private static final String CANCEL = "cancel";
    private final IcecreamMachineV16 machine;

    public IcecreamMachineControllerImpl(IcecreamMachineV16 machine) {
        if (machine == null) throw new IllegalArgumentException("machine must not be null");
        this.machine = machine;
    }

    @Override
    public void processOrder(String[] orders) {
        if (orders == null || orders.length == 0) {
            throw new IllegalArgumentException("Order cannot be null or empty");
        }

        Scanner scanner = new Scanner(System.in);

        for (String orderItem : orders) {
            if (orderItem == null || orderItem.isBlank()) continue;

            String region;
            String dessertToken;

            if (orderItem.contains(":")) {
                String[] parts = orderItem.split(":", 2);
                region = parts[0].trim();
                dessertToken = parts[1].trim();
            } else {
                dessertToken = orderItem.trim();
                region = askRegionInteractive(scanner, dessertToken);
                if (region == null) {
                    System.out.println("Skipped: " + dessertToken);
                    continue;
                }
            }

            DessertFactory factory;
            try {
                factory = FactoryProvider.getInstance().getFactoryForRegion(region);
            } catch (IllegalArgumentException ex) {
                System.out.println("Unknown region '" + region + "'. Skipping " + dessertToken);
                continue;
            }

            DessertType dessertType;
            try {
                dessertType = DessertType.fromString(dessertToken);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid dessert type: " + dessertToken + ". Skipping.");
                continue;
            }

            Map<DessertType, Supplier<DessertRecipe>> creators = new HashMap<>();
            creators.put(DessertType.ICE_CREAM, factory::createIceCream);
            creators.put(DessertType.MILKSHAKE, factory::createMilkshake);
            creators.put(DessertType.SMOOTHIE, factory::createSmoothie);

            Supplier<DessertRecipe> supplier = creators.get(dessertType);
            if (supplier == null) {
                System.out.println("No factory method for dessert: " + dessertType + ". Skipping.");
                continue;
            }

            DessertRecipe recipe = supplier.get();

            try {
                machine.send(recipe.toMachineCommand());
            } catch (RuntimeException ex) {
                System.out.println("Failed to send command for '" + dessertToken + "': " + ex.getMessage());
            }
        }
    }

    private String askRegionInteractive(Scanner scanner, String dessertName) {
        while (true) {
            System.out.print("Enter region for '" + dessertName + "' (e.g., USA, JP) or '" + CANCEL + "' to skip: ");
            String input = scanner.nextLine();
            if (input == null) return null;

            input = input.trim();
            if (input.isEmpty()) {
                System.out.println("Empty input — try again or type '" + CANCEL + "'.");
                continue;
            }

            if (CANCEL.equalsIgnoreCase(input)) return null;

            try {
                FactoryProvider.getInstance().getFactoryForRegion(input);
                return input;
            } catch (IllegalArgumentException ex) {
                System.out.println("Unknown region: " + input + ". Try again or type '" + CANCEL + "'.");
            }
        }
    }
}