package com.chrisgrimble.checkoutkata;

public class Amount {
    private int value;

    public Amount(int valueInPence) {
        this.value = valueInPence;
    }

    public int inPence() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount = (Amount) o;

        return value == amount.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "inPence=" + value +
                '}';
    }
}
