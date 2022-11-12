/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mcgill.cccs425.assignment1.repo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;  
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Himeh
 */
@XmlRootElement
public class BookEntity implements Serializable {
    
    private String title;   	
    private Date date;  	
    private int    id;    // identifier used as lookup key
    
    public BookEntity() {}
    public BookEntity(int id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    public void setDate(Date date) { this.date = date; }
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    public Date getDate() { return this.date; }
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return this.title; }
    public void setId(int id) { this.id = id; }    
    public int getId() { return this.id; }
   @Override
    public String toString() {
        return "id: " + this.id + " - title: " + this.title + " - date: " + this.date;
    }
    
} 
