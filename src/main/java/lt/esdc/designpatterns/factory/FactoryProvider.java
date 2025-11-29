package lt.esdc.designpatterns.factory;

import java.util.HashMap;
import java.util.Map;

public final class FactoryProvider {

    private static final FactoryProvider INSTANCE = new FactoryProvider();
    private final Map<String, DessertFactory> factoryCache = new HashMap<>();

    private FactoryProvider() {
        factoryCache.put("USA", new UsaDessertFactory());
        factoryCache.put("US", factoryCache.get("USA"));
        factoryCache.put("JAPAN", new JapanDessertFactory());
        factoryCache.put("JP", factoryCache.get("JAPAN"));
    }

    public static FactoryProvider getInstance() {
        return INSTANCE;
    }

    public DessertFactory getFactoryForRegion(String regionCode) {
        if (regionCode == null || regionCode.isBlank()) {
            throw new IllegalArgumentException("regionCode must not be null or empty");
        }

        DessertFactory factory = factoryCache.get(regionCode.trim().toUpperCase());
        if (factory != null) return factory;

        throw new IllegalArgumentException("Unknown region: " + regionCode);
    }
}