package com.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.*;

// обьявление класса, он реализовывает интерфейс Serializable
@XmlRootElement(name = "rental")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rental implements Serializable {

    // обявление скрытых полей класса
    @XmlElement(name = "rentalID")
    private int id; // id
    @XmlElement(name = "rentalPoint")
    private String point; // точка проката
    @XmlElement(name = "carList")
    private ArrayList<Car> list; // массив обьектов
    private static final long serialVersionUID = 6529685098267757690L;

    public Rental() {

        list = new ArrayList<>();
    }

    // конструктор с параметрами
    public Rental(String point) {
        this.point = point;
        list = new ArrayList<>();
    }

    // метод для получения автомобиля по индексу
    public Car getCar(int i) {
        return list.get(i);
    }

    public ArrayList<Car> getList() {
        return list;
    }

    // метод для получения id
    public int getId() {
        return id;
    }

    // метод для установки значения id
    public void setId(int id) {
        this.id = id;
    }

    // методы для получения значений полей
    public String getPoint() {
        return point;
    }

    // метод добавления обьекта в конец массива
    public void add(Car car) {
        list.add(car);
    }

    // метод удаления обекта из массива по индексу
    public void delete(int index) {
        list.remove(index);
    }

    // метод сортировки по стоимости автомобилей
    public void sort() {
        Collections.sort(list, (a, b) -> {
            return (int) (a.getPrice() - b.getPrice());

        });
    }

    // метод для записи списка обьектов в файл с помощью сериализации
    public void write(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Car list1 : list) {
                oos.writeObject(list1);
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    // метод для чтения списка обьектов из файла с помощью сериализации
    public void read(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            int index = 0;
            list.add((Car) ois.readObject());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // метод для вывода данных о списке обьектов
    public void print() {
        System.out.println("Точка проката: " + point);
        System.out.println("Количество авто: " + list.size());
        System.out.println("Проиводитель\tМодель\tСтоимость\tКоличество");
        for (Car list1 : list) {
            System.out.println(list1.toString());
        }
        System.out.println();
    }

    public void deleteDuplicates() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    j--;
                }
            }
        }
    }
}
