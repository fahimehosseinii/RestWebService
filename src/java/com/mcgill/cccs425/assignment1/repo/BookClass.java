/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcgill.cccs425.assignment1.repo;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.validation.constraints.Null;
import org.json.simple.JSONObject; 
import org.json.simple.JSONArray; 

/**
 *
 * @author Himeh
 */
public class BookClass {
    private int id;
    private ConcurrentHashMap<Integer, BookEntity> bookLibrary;

    public BookClass() {
        bookLibrary = new ConcurrentHashMap();
        id=0;
    }

    public int addBook(BookEntity book) {
        id += 1;
        book.setId(id); 
        bookLibrary.put(id, book);
        return id;
    }

    public BookEntity getBook(int id) {
        return bookLibrary.get(id);}

    

    public boolean checkId(int id) {
        boolean AvailableId = false;
        if (bookLibrary.containsKey(id))
            AvailableId = true;
        
        return AvailableId;
        }

    
    public int removeBook(int id) {
        BookEntity bookIdRemoved = bookLibrary.remove(id);
        return bookIdRemoved.getId();
    }
    
    public void removeAll() {
        bookLibrary.clear();

    }
    
    public int getSize() {
        return bookLibrary.size();

    }

    public String getBooks() {
        
        return  bookLibrary.toString() ;
    }

    public BookEntity updateBook(int id, BookEntity book) {
        book.setId(id); 
        return bookLibrary.replace(id, book);

    }
    }
