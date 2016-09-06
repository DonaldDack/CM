package com.example.dmitry.cashmonitor;

/**
 * Created by Dmitry on 25.08.2016.
 */
public class Bought extends Note {

    private double priceForOne;
    private double priceForAll;

    public Bought(String name, int success, double count, String typeCount, double priceForOne, Long id) {
        super(name, success, count, typeCount, id);
        this.priceForOne = priceForOne;
        this.priceForAll = priceForOne * count;
    }

    public Bought(String name, int success, double count, String typeCount, double priceForOne, Long id, String year, String month, String day) {
        super(name, success, count, typeCount, id, year, month, day);
        this.priceForOne = priceForOne;
        this.priceForAll = priceForOne * count;
    }

    public Bought(String name, boolean success, double count, String typeCount, double priceForOne, Long id, String year, String month, String day) {
        super(name, success? 1: 0, count, typeCount, id, year, month, day);
        this.priceForOne = priceForOne;
        this.priceForAll = priceForOne * count;
    }

    public Bought(String name, double priceForAll) {
        super(name,  0, 0.0, " ", new Long("0"));
        this.priceForOne = 0.0;
        this.priceForAll = priceForAll;
    }

    public double getPriceForOne(){
        return priceForOne;
    }

    public double getPriceForAll(){
        return priceForAll;
    }
}
