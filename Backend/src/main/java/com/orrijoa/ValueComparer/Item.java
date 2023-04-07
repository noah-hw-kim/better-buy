package com.orrijoa.ValueComparer;

public interface Item extends Comparable<Item> {
    /*
     * convert unit1 and unit2 to the metric unit
     * */
    void standardizeAmount();

    @Override
    public int compareTo(Item other);

    @Override
    public boolean equals(Object other);

}
