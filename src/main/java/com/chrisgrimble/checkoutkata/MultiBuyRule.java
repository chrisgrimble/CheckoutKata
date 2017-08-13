package com.chrisgrimble.checkoutkata;

import java.util.List;

public class MultiBuyRule implements SpecialPriceRule {
    private final SKU item;
    private final int multiBuyNumber;
    private final Amount discount;

    public MultiBuyRule(SKU item, int multiBuyNumber, Amount discount) {
        this.item = item;
        this.multiBuyNumber = multiBuyNumber;
        this.discount = discount;
    }

    @Override
    public Amount calculateDiscount(List<SKU> basket) {
        final long numberOfItems = basket.stream().filter(basketItem -> basketItem.equals(item)).count();
        return new Amount((int)(numberOfItems / multiBuyNumber) * discount.inPence());
    }
}
