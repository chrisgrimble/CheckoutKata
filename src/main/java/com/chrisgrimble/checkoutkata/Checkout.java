package com.chrisgrimble.checkoutkata;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final Catalogue catalogue;
    private final List<SpecialPriceRule> specialPriceRules;
    private final List<SKU> basket = new ArrayList<>();

    public Checkout(Catalogue catalogue, List<SpecialPriceRule> specialPriceRules) {
        this.catalogue = catalogue;
        this.specialPriceRules = specialPriceRules;
    }

    public Checkout scan(SKU sku){
        basket.add(sku);
        return this;
    }

    public Amount calculateTotal() {
        int costOfItemsInBasket = basket.stream().mapToInt(sku -> catalogue.getPrice(sku).inPence()).sum();
        int discount = specialPriceRules.stream().mapToInt(rule -> rule.calculateDiscount(basket).inPence()).sum();

        return new Amount(costOfItemsInBasket - discount);
    }
}
