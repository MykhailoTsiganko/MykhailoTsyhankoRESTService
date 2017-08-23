package com.epam.lab.rest.bo;

import com.epam.lab.rest.model.Book;
import com.epam.lab.rest.dao.BookDAO;

import java.util.ArrayList;
import java.util.List;

public class BookBO {

    public List<Book> getAllBooks() {
        return BookDAO.findAll();
    }

    public Book getBook(String name) {
        return BookDAO.findByName(name);
    }

    public List<Book> getBooksByAuthorName(String authorName){
        List<Book> bookList = getAllBooks();
        List<Book> authorBookList = new ArrayList<>();
        for(int i = 0; i < bookList.size();i++) {
            if(bookList.get(i).getAuthorName().equals(authorName)){
                authorBookList.add(bookList.get(i));
            }
        }
        return  authorBookList;
    }

    public boolean addBook(Book book){
        return BookDAO.insertBook(book);
    }

}
