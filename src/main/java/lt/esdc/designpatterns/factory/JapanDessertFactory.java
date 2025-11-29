package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.model.DessertRecipe;

public class JapanDessertFactory implements DessertFactory {

    @Override
    public DessertRecipe createIceCream() {
        return new DessertRecipe.Builder()
                .frozenMassG(120)
                .milkMl(15)
                .juiceMl(0)
                .waterMl(5)
                .build();
    }

    @Override
    public DessertRecipe createMilkshake() {
        return new DessertRecipe.Builder()
                .frozenMassG(70)
                .milkMl(120)
                .juiceMl(0)
                .waterMl(10)
                .build();
    }

    @Override
    public DessertRecipe createSmoothie() {
        return new DessertRecipe.Builder()
                .frozenMassG(15)
                .milkMl(20)
                .juiceMl(150)
                .waterMl(30)
                .build();
    }
}