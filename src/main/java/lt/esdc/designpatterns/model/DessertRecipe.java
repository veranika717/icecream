package lt.esdc.designpatterns.model;

public final class DessertRecipe {

    private final int frozenMassG;
    private final int milkMl;
    private final int juiceMl;
    private final int waterMl;

    private DessertRecipe(Builder b) {
        this.frozenMassG = b.frozenMassG;
        this.milkMl = b.milkMl;
        this.juiceMl = b.juiceMl;
        this.waterMl = b.waterMl;
    }

    public String toMachineCommand() {
        return String.format("%dg %dml %dml %dml", frozenMassG, milkMl, juiceMl, waterMl);
    }

    public static class Builder {
        private int frozenMassG;
        private int milkMl;
        private int juiceMl;
        private int waterMl;

        public Builder frozenMassG(int g) { this.frozenMassG = g; return this; }
        public Builder milkMl(int ml) { this.milkMl = ml; return this; }
        public Builder juiceMl(int ml) { this.juiceMl = ml; return this; }
        public Builder waterMl(int ml) { this.waterMl = ml; return this; }

        public DessertRecipe build() {
            if (frozenMassG < 0 || milkMl < 0 || juiceMl < 0 || waterMl < 0) {
                throw new IllegalArgumentException("Ingredients must be non-negative");
            }
            return new DessertRecipe(this);
        }
    }
}