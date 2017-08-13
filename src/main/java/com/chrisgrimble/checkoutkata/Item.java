package com.chrisgrimble.checkoutkata;

public class Item {
    private SKU sku;
    private Amount price;

    public Item(SKU sku, Amount price) {
        this.sku = sku;
        this.price = price;
    }

    public SKU getSku() {
        return sku;
    }

    public Amount getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (sku != null ? !sku.equals(item.sku) : item.sku != null) return false;
        return !(price != null ? !price.equals(item.price) : item.price != null);

    }

    @Override
    public int hashCode() {
        int result = sku != null ? sku.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
