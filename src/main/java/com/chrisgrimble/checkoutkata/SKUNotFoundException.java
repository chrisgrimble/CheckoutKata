package com.chrisgrimble.checkoutkata;

public class SKUNotFoundException extends RuntimeException {
    public SKUNotFoundException(SKU sku) {
        super(sku + " does not exist");
    }
}
