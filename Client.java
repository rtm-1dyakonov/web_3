package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "customer")
@XmlType(propOrder = {"id", "login", "name","list"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Client implements Serializable {
    // обявление скрытых полей класса
    @XmlElement(name = "customerID")
    private int id; // id
    @XmlElement(name = "customerName")
    private String name; // имя
    @XmlElement(name = "customerLogin")
    private String login; // логин
    @XmlTransient
    private String password; // пароль
    @XmlElement(name = "rentalList")
    private ArrayList<Rental> list; // массив списков обьектов
       private static final long serialVersionUID = 1129685098267757690L;


    // конструтор класса с одним параметром
    public Client(){
        
        list = new ArrayList<>();
    }
    // конструтор класса с несколькими параметрами
    public Client(String name, String login, String password, int maxSize)
    {
        this.name =  name;
        this.login = login;
        this.password = password;
        list = new ArrayList<>();
    }
    public ArrayList<Rental> getList(){
        return this.list;
    }
   public void setList(ArrayList<Rental> list){
        this.list = list;
    }

    public void setRentalId(int i, int id)
    {
        try {
            list.get(i).setId(id); // установка значения id
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // выброс исключения
            throw new ArrayIndexOutOfBoundsException("Индекс за пределами массива списков обьектов.");
        }
    }

    // метод добавления точки проката в массив
    public void addListItem(Rental rental)
    {
        list.add(rental);
    }

    // метод для удаления точки проката по индексу i с массива
    public void deleteListItem(int i) {
        list.remove(i);
    }

    // метод для получения обьекта точки проката по индексу в массиве
    public Rental getListItem(int i) {
        try {
            return list.get(i); // возврат обьекта по индексу
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // выброс исключения
            throw new ArrayIndexOutOfBoundsException("Индекс за пределами массива списков обьектов.");
        }
    }

    // методы для получения значений полей
    public int getLength() {
        return list.size();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // методы для установки значений полей
    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
