package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Dan on 25.10.2015.
 */

// enum цветков
enum Flowers{Sunflower("Sunflower",5,0.5,0.33),Pion("Pion",2,0.20,0.5),Rose("Rose",3,0.3,1);
    private String name;
    private int fresh;
    private double length;
    private double price;
    // Конструктор ;)
    Flowers(String name, int fresh, double length, double price) {
        this.name = name;
        this.fresh = fresh;
        this.length = length;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getFresh() {
        return fresh;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  name+"("+fresh+") ";
    }

}
public class Bouquet {

    private double bouquetPrice;
    // // Для хранения цветов, используем обычный массив
    private Flowers[] bouquet;
    // // Конструктор;)
    Bouquet(int count){
        this.bouquet = new Flowers[count];
        Random r = new Random();
        bouquetPrice = 0;
        for (int i = 0; i < count; i++) {
            // // добавляем рандомный цветок из enum Flowers[]
            this.bouquet[i] =Flowers.values()[r.nextInt(Flowers.values().length)];
            // // Сразу считаем цену букета
            this.bouquetPrice = this.bouquetPrice + this.bouquet[i].getPrice();
        }
    }

    public Flowers[] getBouquet() {
        return bouquet;
    }

    public void setBouquet(Flowers[] bouquet) {
        this.bouquet = bouquet;
    }

    public double getBouquetPrice() {
        return bouquetPrice;
    }

    public void setBouquetPrice(double bouquetPrice) {
        this.bouquetPrice = bouquetPrice;
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "bouquetPrice=" + bouquetPrice +
                ", bouquet=" + Arrays.toString(this.getBouquet()) +
                '}';
    }

   // // Внутренний класс нужен для создания Компаратора(нужен переопределённый метод compare) для вызова Arrays.sort(T[] a, Comparator<T>)
    static private class BouquetSortByFresh implements Comparator<Flowers> {

        @Override
        public final int compare(Flowers o1, Flowers o2) {
            int f1,f2;
            f1 = o1.getFresh();
            f2 = o2.getFresh();

            if(f1 > f2) {
                return 1;
            } else if(f1 < f2){
                return -1;
            } else
                return 0;
        }
    }
    
    // Тут Генерик;) на след уроке расскажут о нём
    static Comparator<Flowers> getFreshComparator(){
        return new BouquetSortByFresh();
    }

    public static void main(String[] args) {

        // // Создаём букет из 15 цветков
        Bouquet bk1 = new Bouquet(15);
        System.out.println(bk1);
        // // Для наглядности, передаем в один метод 2 параметра, один из элементов Экземпляра класса,
        // // второй - статический метод inner класса, принадлежащий к типу Bouquet, а не к экземплярам.
        Arrays.sort(bk1.getBouquet(),Bouquet.getFreshComparator());
        System.out.println(bk1);

    }
}
