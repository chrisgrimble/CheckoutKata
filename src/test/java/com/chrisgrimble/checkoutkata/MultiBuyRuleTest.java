package com.chrisgrimble.checkoutkata;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MultiBuyRuleTest {

    private MultiBuyRule multiBuyRule;
    private SKU skuA;
    private SKU skuB;

    @Before
    public void setUp() throws Exception {
        skuA = new SKU("A");
        skuB = new SKU("B");

        multiBuyRule = new MultiBuyRule(skuA, 3, new Amount(20));
    }

    @Test
    public void noDiscountIfNoItems() {
        final Amount discount = multiBuyRule.calculateDiscount(Collections.<SKU>emptyList());

        assertThat(discount, is(new Amount(0)));
    }

    @Test
    public void noDiscountsWrongItems() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuB, skuB, skuB));

        assertThat(discount, is(new Amount(0)));
    }

    @Test
    public void noDiscountsIfNotEnoughItems() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuA, skuA, skuB));

        assertThat(discount, is(new Amount(0)));
    }

    @Test
    public void singleDiscountIfEnoughItems() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuA, skuA, skuA));

        assertThat(discount, is(new Amount(20)));
    }

    @Test
    public void singleDiscountIfMoreThanEnoughItems() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuA, skuA, skuA, skuA));

        assertThat(discount, is(new Amount(20)));
    }

    @Test
    public void multipleDiscountsIfEnoughItems() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuA, skuA, skuA, skuA, skuA, skuA));

        assertThat(discount, is(new Amount(40)));
    }

    @Test
    public void singleDiscountEvenIfItemsNotOneAfterTheOther() {
        final Amount discount = multiBuyRule.calculateDiscount(asList(skuA, skuB, skuA, skuA));

        assertThat(discount, is(new Amount(20)));
    }
}