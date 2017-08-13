package com.chrisgrimble.checkoutkata;

public class SKU {
    private String value;

    public SKU(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SKU sku = (SKU) o;

        return !(value != null ? !value.equals(sku.value) : sku.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public String toString() {
        return "SKU [" + value + "]";
    }
}
