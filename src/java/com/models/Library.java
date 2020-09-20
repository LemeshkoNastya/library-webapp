package com.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.*;

// обьявление класса, он реализовывает интерфейс Serializable
@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library implements Serializable {

    // обявление скрытых полей класса
    @XmlElement(name = "libraryID")
    private int id; // id
    @XmlElement(name = "libraryAuthor")
    private String Author; // автор
    @XmlElement(name = "libraryList")
    private ArrayList<Book> list; // массив обьектов
    private static final long serialVersionUID = 6529685098267757690L;

    public Library() {
        list = new ArrayList<>();
    }

    // конструктор с параметрами
    public Library(String author) {
        this.Author = author;
        list = new ArrayList<>();
    }

    // метод для получения книги по индексу
    public Book getBook(int i) {
        return list.get(i);
    }

    public ArrayList<Book> getList() {
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
    public String getAuthor() {
        return Author;
    }

    // метод добавления обьекта в конец массива
    public void add(Book book) {
        list.add(book);
    }

    // метод удаления обекта из массива по индексу
    public void delete(int index) {
        list.remove(index);
    }

    //метод сортировки по названию
    public void sort(){
        Collections.sort(list);
    }


    // метод для записи списка обьектов в файл с помощью сериализации
    public void write(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Book list1 : list) {
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
            list.add((Book) ois.readObject());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // метод для вывода данных о списке обьектов
    public void print()
    {
        System.out.println("Автор: " + Author);
        System.out.println("Количество книг: " + list.size());
        System.out.println("Название\t\t\t\tГод издания\t\t\tКоличество страниц\t\t\tЦена");
        for(Book list_pr : list)
        {
            System.out.println(list_pr.toString());
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
