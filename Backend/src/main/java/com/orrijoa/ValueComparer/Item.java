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

    @Override
    public boolean equals(Object other);

    @Override
    public int hashCode();

    public String getUnit();
    public void setUnit(String unit);

    public String getName();
    public void setName(String name);

    public double getPrice();
    public void setPrice(double price);

    public double getAmount();
    public void setAmount(double amount);

    public double getStandardAmount();
    public void setStandardAmount(double standardAmount);

}
