package com.chrisgrimble.checkoutkata;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
    private Map<SKU, Amount> catalogue = new HashMap<>();

    public Catalogue(Item... items) {
        for (Item item : items) {
            catalogue.put(item.getSku(), item.getPrice());
        }
    }

    public Amount getPrice(SKU sku) {
        if (catalogue.containsKey(sku)) {
            return catalogue.get(sku);
        }
        throw new SKUNotFoundException(sku);
    }
}
