package lt.esdc.designpatterns.types;

public enum DessertType {
    ICE_CREAM,
    MILKSHAKE,
    SMOOTHIE;

    public static DessertType fromString(String s) {
        if (s == null) throw new IllegalArgumentException("Dessert type cannot be null");

        String normalized = s.trim().toLowerCase();
        if (normalized.equals("icecream") || normalized.equals("ice_cream") || normalized.equals("ice-cream") || normalized.equals("ice cream"))
            return ICE_CREAM;
        else if (normalized.equals("milkshake") || normalized.equals("milk_shake") || normalized.equals("milk-shake"))
            return MILKSHAKE;
        else if (normalized.equals("smoothie"))
            return SMOOTHIE;
        else
            throw new IllegalArgumentException("Unknown dessert type: " + s);
    }
}