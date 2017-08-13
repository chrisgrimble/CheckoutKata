package com.chrisgrimble.checkoutkata;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutTest {

    private SKU skuA;
    private Catalogue catalogue;
    private SpecialPriceRule specialPriceRule1;
    private SpecialPriceRule specialPriceRule2;
    private Checkout checkout;

    @Before
    public void setUp() throws Exception {
        skuA = new SKU("A");
        catalogue = mock(Catalogue.class);
        specialPriceRule1 = mock(SpecialPriceRule.class);
        specialPriceRule2 = mock(SpecialPriceRule.class);
        checkout = new Checkout(catalogue, asList(specialPriceRule1, specialPriceRule2));

        when(catalogue.getPrice(skuA)).thenReturn(new Amount(10));
        when(specialPriceRule1.calculateDiscount(any())).thenReturn(new Amount(1));
        when(specialPriceRule2.calculateDiscount(any())).thenReturn(new Amount(2));
    }

    @Test
    public void getsPriceFromCatalogue() {
        checkout.scan(skuA).calculateTotal();
        verify(catalogue).getPrice(skuA);
    }

    @Test
    public void allRulesAreApplied() {
        when(catalogue.getPrice(skuA)).thenReturn(new Amount(10));
        when(specialPriceRule1.calculateDiscount(any())).thenReturn(new Amount(0));

        checkout.scan(skuA).scan(skuA).calculateTotal();
        verify(specialPriceRule1).calculateDiscount(asList(skuA, skuA));
        verify(specialPriceRule2).calculateDiscount(asList(skuA, skuA));
    }

    @Test
    public void totalIsPriceMinusDiscounts() {
        Amount cost = checkout.scan(skuA).calculateTotal();
        assertThat(cost, is(new Amount(7)));
    }
}
