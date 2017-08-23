package com.epam.lab.rest.web;

import org.apache.log4j.Logger;
import com.epam.lab.rest.model.Book;

import com.epam.lab.rest.bo.BookBO;


import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LibraryServiceImpl implements LibraryService {
    private Logger LOGGER = Logger.getLogger(LibraryServiceImpl.class);

    @Override
    public Response getAllBooks() {
        BookBO bookBO = new BookBO();

        return Response.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).entity(bookBO.getAllBooks()).build();
    }

    @Override
    public Response getBook(String name) {
        Response response;
        BookBO bookBO = new BookBO();
        Book book = bookBO.getBook(name);

        if (Objects.isNull(book)) {
            Map<String,String> map = new HashMap<>();
            map.put("Code","404");
            map.put("Message","no mapa with name:"+ name);
            response = Response.status(Response.Status.NOT_FOUND).entity(map).build();
        } else {
            response = Response.ok().entity(book).build();
        }

        return response;
    }

    @Override
    public Response turnBackBook(Book book) {
        BookBO bookBO = new BookBO();

        return Response.ok().entity(bookBO.addBook(book)).build();
    }

    @Override
    public Response exchangeBook(Book book, String requiredBookName){
        Response response;
        BookBO bookBO = new BookBO();
        Book requiredBook = bookBO.getBook(requiredBookName);

        if (Objects.isNull(requiredBook)) {
           response = Response.status(Response.Status.NOT_FOUND).build();

        } else {
            bookBO.addBook(book);
            response = Response.ok().entity(requiredBook).build();
        }


        return response;
    }

    @Override
    public Response getAuthorBooks(String authorName, int number) {
        Response response;
        BookBO bookBO = new BookBO();
        List<Book> authorBookList = bookBO.getBooksByAuthorName(authorName);

        if (authorBookList.size() < number) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok().entity(authorBookList.subList(0, number)).build();
        }

        return response;
    }
}
