
package com.models;

import java.io.*;
import java.util.Scanner;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// обьявление класса, он реализовывает интерфейс Serializable и Comparable
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable ,Comparable {

    // объявление скрытых полей класса
    @XmlElement(name = "bookID")
    private int id; // id
    @XmlElement(name = "bookName")
    private String Name; // название
    @XmlElement(name = "bookYear")
    private int Year; // год издания
    @XmlElement(name = "bookPrice")
    private int Price; // стоимость
    @XmlElement(name = "bookPageCount")
    private int PageCount; // количество страниц
    private static final long serialVersionUID = 8180497438563284099L;

    // конструктор по умолчанию
    public Book(){}

    // конструктор с параметрами
    public Book(String Name,int Year,int PageCount,int Price) throws Exception {
        if(Name.length() ==0 || PageCount <=0 || Year <=0 || Price <0) {
            throw new Exception("Неверные значения книги"); //исключение
        }
        else
        {
            // копируем значение полей из параметоров
            this.Name = Name;
            this.Year = Year;
            this.PageCount=PageCount;
            this.Price=Price;
        }
    }

    // методы для изменения и получения значений полей класса
    public int getPrice() {
        return Price;
    }

    public int getPageCount() {
        return PageCount;
    }

    public int getYear() {
        return Year;
    }

    public String getName() {
        return Name;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // статический метод для записи обьекта в текстовый файл
    public static void write(Book Book, String filename)
    {
        try
        {
            // создание обьектов для записи
            FileOutputStream fos=new FileOutputStream(filename);
            PrintStream printStream = new PrintStream(fos);
            // запись полей в файл с новой строки каждый
            printStream.println(Book.getName());
            printStream.println(Book.getYear());
            printStream.println(Book.getPageCount());
            printStream.println(Book.getPrice());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    // статический метод для чтения обьекта из текстового файла
    public static Book read(String filename) throws FileNotFoundException {
        Book Book = new Book();
        try {
            // создание обьектов для чтения
            FileInputStream fis = new FileInputStream(filename);
            Scanner sc = new Scanner(fis);
            // чтение полей по одному из строки
            Book.setName(sc.nextLine());
            Book.setYear(Integer.valueOf(sc.nextLine()));
            Book.setPageCount(Integer.valueOf(sc.nextLine()));
            Book.setPrice(Integer.valueOf(sc.nextLine()));
            sc.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Book; // возврат обьекта
    }

    // перегруженный метод toString, возвращает строку со значениями полей обьекта класса, удобный для вывода данных
    @Override
    public String toString() {
        return String.format(Name + "\t\t\t" + Year + "\t\t\t\t\t" + PageCount + "\t\t\t\t\t" + Price);
    }
    @Override
    public boolean equals(Object obj) {
        Book Book = (Book) obj;
        if(Price != Book.Price || PageCount != Book.PageCount || !Name.equals(Book.Name)
                || Year != Book.Year)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int compareTo(Object t) {

        if (t instanceof Book){
            Book temp = (Book) t;
            int dif = this.Name.compareToIgnoreCase(temp.getName());//.compareTo(temp.getName());
            return ( dif < 0.0 ) ? -1 : ( dif > 0.0 ) ? 1 : 0;
        }
        else {
            System.out.println("Conversion is invalid");
            return 0;
        }
    }
}

