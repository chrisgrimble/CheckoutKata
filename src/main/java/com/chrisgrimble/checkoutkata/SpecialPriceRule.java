package com.chrisgrimble.checkoutkata;

import java.util.List;

public interface SpecialPriceRule {
    Amount calculateDiscount(List<SKU> basket);
}
