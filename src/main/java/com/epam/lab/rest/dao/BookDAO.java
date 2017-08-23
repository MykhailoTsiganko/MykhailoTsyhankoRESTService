package com.epam.lab.rest.dao;

import org.apache.log4j.Logger;
import com.epam.lab.rest.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BookDAO {
    public static List<Book> bookList;

    private static Logger LOGGER = Logger.getLogger(BookDAO.class);

    static{
        LOGGER.info("filling DB");
        bookList = new ArrayList<>();

        bookList.add(new Book("Чорний ворон", "Василь Шкляр" ,"Пригоди"));
        bookList.add(new Book("Воно", "Стівен кінг" ,"Жахи"));
        bookList.add(new Book("Маруся", "Василь Шкляр" ,"Пригоди"));
        bookList.add(new Book("Чорна рада", "Пантелеймон Куліш" ,"Пригоди"));
        bookList.add(new Book("Жовтий князь", "Василь Барка" ,"Історичний роман"));
        bookList.add(new Book("Гра престолів", "Джордж Мартін" ,"Фентезі"));
        bookList.add(new Book("Волода перстнів", "Толкінг" ,"фентезі"));
        bookList.add(new Book("Битва П'яти королів", "Джордж Мартін" ,"Фентезі"));
        bookList.add(new Book("Сім", "Стівен Кінг" ,"Детектив"));
        bookList.add(new Book("Інферно", "Ден Браун" ,"детектив"));
        bookList.add(new Book("Ангели та демони", "Ден Браун" ,"Детектив"));
        bookList.add(new Book("Код Давінчі", "Ден Браун" ,"Детектив"));
        bookList.add(new Book("Маренький принц", "Антуан де Сент Екзюпері" ,"Фантастика"));
        bookList.add(new Book("Алхімік", "Пауло Коельйо" ,"Містика"));
        bookList.add(new Book("Інтермецо", "Микола Хвильовий" ,"Пригоди"));

    }

    public static List<Book> findAll() {
        return bookList;
    }

    public static Book findByName(String name) {
        LOGGER.info("findByName method");
        Book myBook = null;
        for(Book book :bookList) {
            if(book.getName().equals(name)) {
                myBook=  book;
                break;
            }
        }

        return myBook;
    }


    public static boolean insertBook(Book book) {
        LOGGER.info("insertBook method");

        if(bookList.contains(book)) {
            return false;
        } else {
            bookList.add(book);
            return true;
        }
    }

    public static boolean deleteBook(String name) {
        LOGGER.info("deleteBook method");

        ListIterator<Book> it = findAll().listIterator();
        boolean swapped = false;

        while(it.hasNext()) {
            Book book = it.next();
            if(book.getName().equals(name)) {
                it.remove();
                swapped = true;
                break;
            }
        }
        return swapped;
    }
}