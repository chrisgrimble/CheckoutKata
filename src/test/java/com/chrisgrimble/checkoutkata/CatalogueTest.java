package com.chrisgrimble.checkoutkata;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CatalogueTest {

    private SKU skuA;
    private Amount costA;
    private Catalogue catalogue;

    @Before
    public void setUp() throws Exception {
        skuA = new SKU("A");
        costA = new Amount(1);
        catalogue = new Catalogue(new Item(skuA, costA));
    }

    @Test
    public void canGetPriceForItem() throws Exception {
        Amount price = catalogue.getPrice(skuA);

        assertThat(price, is(costA));
    }

    @Test(expected = SKUNotFoundException.class)
    public void throwAnSKUNotFoundExceptionIfSKUDoesNotExist() {
        new Catalogue().getPrice(new SKU("B"));
    }
}