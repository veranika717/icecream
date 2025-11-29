package lt.esdc.designpatterns.machine;

import lt.esdc.designpatterns.controller.IcecreamMachineController;
import lt.esdc.designpatterns.controller.IcecreamMachineControllerImpl;
import lt.esdc.designpatterns.factory.FactoryProvider;
import lt.esdc.designpatterns.model.DessertRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IcecreamMachineControllerImplTest {

    private List<String> sentCommands;
    private IcecreamMachineV16 mockMachine;
    private IcecreamMachineController controller;

    @BeforeEach
    void setUp() {
        sentCommands = new ArrayList<>();

        mockMachine = order -> sentCommands.add(order);

        controller = new IcecreamMachineControllerImpl(mockMachine);
    }

    @Test
    void testProcessOrderWithExplicitRegions() {
        String[] orders = {"USA:icecream", "JP:milkshake", "US:smoothie"};
        controller.processOrder(orders);

        assertEquals(3, sentCommands.size());
        for (String cmd : sentCommands) {
            assertTrue(cmd.matches("\\d+g \\d+ml \\d+ml \\d+ml"));
        }
    }

    @Test
    void testProcessOrderWithInteractiveRegion() {
        String input = "JP\nUSA\ncancel\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String[] orders = {"icecream", "milkshake", "smoothie"};
        controller.processOrder(orders);

        assertEquals(2, sentCommands.size());
        assertTrue(sentCommands.get(0).contains("g"));
        assertTrue(sentCommands.get(1).contains("g"));

        System.setIn(stdin);
    }

    @Test
    void testUnknownDessertIsSkipped() {
        String[] orders = {"USA:unknownDessert"};
        controller.processOrder(orders);

        assertEquals(0, sentCommands.size());
    }

    @Test
    void testUnknownRegionIsSkipped() {
        String[] orders = {"XYZ:icecream"};
        controller.processOrder(orders);

        assertEquals(0, sentCommands.size());
    }

    @Test
    void testEmptyOrderArrayThrowsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> controller.processOrder(new String[]{}));
        assertTrue(ex.getMessage().contains("Order cannot be null or empty"));
    }

    @Test
    void testNullOrderArrayThrowsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> controller.processOrder(null));
        assertTrue(ex.getMessage().contains("Order cannot be null or empty"));
    }

    @Test
    void testMachineSendFailsHandledGracefully() {
        IcecreamMachineV16 failingMachine = order -> { throw new RuntimeException("fail"); };
        IcecreamMachineController ctrl = new IcecreamMachineControllerImpl(failingMachine);
        String[] orders = {"USA:icecream"};

        assertDoesNotThrow(() -> ctrl.processOrder(orders));
    }

    @Test
    void testDessertRecipeFactoryMethods() {
        var usaFactory = FactoryProvider.getInstance().getFactoryForRegion("USA");
        DessertRecipe ice = usaFactory.createIceCream();
        DessertRecipe milk = usaFactory.createMilkshake();
        DessertRecipe smoothie = usaFactory.createSmoothie();

        assertNotNull(ice.toMachineCommand());
        assertNotNull(milk.toMachineCommand());
        assertNotNull(smoothie.toMachineCommand());
    }
}