package com.chrisgrimble.checkoutkata;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CheckoutIntegrationTest {

    private Checkout checkout;
    private SKU skuA;
    private Amount costA;
    private SKU skuB;

    @Before
    public void setUp() throws Exception {
        skuA = new SKU("A");
        costA = new Amount(50);

        skuB = new SKU("B");
        Amount costB = new Amount(30);

        Catalogue catalogue = new Catalogue(new Item(skuA, costA), new Item(skuB, costB));
        List<SpecialPriceRule> specialPriceRules = asList(
                new MultiBuyRule(skuA, 3, new Amount(20)),
                new MultiBuyRule(skuB, 2, new Amount(15))
        );

        checkout = new Checkout(catalogue, specialPriceRules);
    }

    @Test
    public void calculatesCostOfEmptyBasket() {
        Amount cost = checkout.calculateTotal();

        assertThat(cost, is(new Amount(0)));
    }

    @Test
    public void calculatesCostOfSingleItem() {
        Amount cost = checkout.scan(skuA).calculateTotal();

        assertThat(cost, is(costA));
    }

    @Test
    public void calculateCostOfMultipleOfTheSameItemNoDiscount() {
        Amount cost = checkout.scan(skuA).scan(skuA).calculateTotal();

        assertThat(cost, is(new Amount(100)));
    }

    @Test
    public void calculateCostOfMultipleDifferentItemsNoDiscount() {
        Amount cost = checkout.scan(skuA).scan(skuB).calculateTotal();

        assertThat(cost, is(new Amount(80)));
    }

    @Test
    public void calculatesCostSingleDiscount() {
        Amount cost = checkout.scan(skuA).scan(skuA).scan(skuA).calculateTotal();

        assertThat(cost, is(new Amount(130)));
    }

    @Test
    public void calculatesCostMultipleDiscounts() {
        Amount cost = checkout
                .scan(skuA).scan(skuA).scan(skuA)
                .scan(skuB).scan(skuB)
                .calculateTotal();

        assertThat(cost, is(new Amount(175)));
    }

}