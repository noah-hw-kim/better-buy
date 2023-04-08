package com.orrijoa.ValueComparer;

public interface Item {
    /*
     * convert unit1 and unit2 to the metric unit
     * */
    void standardizeAmount();

    public static int compareTo(Item item1, Item item2) {
        double item1PricePerAmount = item1.getPrice() / item1.getStandardAmount();
        double item2PricePerAmount = item2.getPrice() / item2.getStandardAmount();

        return Double.compare(item1PricePerAmount, item2PricePerAmount);
    }

    String getUnit();
    void setUnit(String unit);

    String getName();
    void setName(String name);

    double getPrice();
    void setPrice(double price);

    double getAmount();
    void setAmount(double amount);

    double getStandardAmount();
    void setStandardAmount(double standardAmount);

}
