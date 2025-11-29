package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.model.DessertRecipe;

public interface DessertFactory {
    DessertRecipe createIceCream();
    DessertRecipe createMilkshake();
    DessertRecipe createSmoothie();
}