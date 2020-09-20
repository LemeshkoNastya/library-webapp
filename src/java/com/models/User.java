package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "login", "name","list"})
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    // обявление скрытых полей класса
    @XmlElement(name = "userID")
    private int id; // id
    @XmlElement(name = "userName")
    private String name; // имя
    @XmlElement(name = "userLogin")
    private String login; // логин
    @XmlTransient
    private String password; // пароль
    @XmlElement(name = "libraryList")
    private ArrayList<Library> list; // массив списков обьектов
       private static final long serialVersionUID = 1129685098267757690L;


    // конструтор класса с одним параметром
    public User(){
        
        list = new ArrayList<>();
    }
    // конструтор класса с несколькими параметрами
    public User(String name, String login, String password, int maxSize)
    {
        this.name =  name;
        this.login = login;
        this.password = password;
        list = new ArrayList<>();
    }
    public ArrayList<Library> getList(){
        return this.list;
    }
   public void setList(ArrayList<Library> list){
        this.list = list;
    }

    public void setLibraryId(int i, int id)
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
    public void addListItem(Library library)
    {
        list.add(library);
    }

    // метод для удаления точки проката по индексу i с массива
    public void deleteListItem(int i) {
        list.remove(i);
    }

    // метод для получения обьекта точки проката по индексу в массиве
    public Library getListItem(int i) {
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
