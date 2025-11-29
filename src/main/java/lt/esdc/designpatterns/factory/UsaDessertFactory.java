package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.model.DessertRecipe;

public class UsaDessertFactory implements DessertFactory {

    @Override
    public DessertRecipe createIceCream() {
        return new DessertRecipe.Builder()
                .frozenMassG(250)
                .milkMl(30)
                .juiceMl(0)
                .waterMl(15)
                .build();
    }

    @Override
    public DessertRecipe createMilkshake() {
        return new DessertRecipe.Builder()
                .frozenMassG(150)
                .milkMl(250)
                .juiceMl(0)
                .waterMl(20)
                .build();
    }

    @Override
    public DessertRecipe createSmoothie() {
        return new DessertRecipe.Builder()
                .frozenMassG(30)
                .milkMl(50)
                .juiceMl(250)
                .waterMl(60)
                .build();
    }
}