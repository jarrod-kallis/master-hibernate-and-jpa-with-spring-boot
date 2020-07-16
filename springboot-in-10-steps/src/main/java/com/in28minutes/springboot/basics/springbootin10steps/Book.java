package com.in28minutes.springboot.basics.springbootin10steps;

public class Book {
    private long id;
    private String title;
    private String author;

    public Book(long id, String title, String author) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
    }

    public long getId() {
	return id;
    }

    public String getTitle() {
	return title;
    }

    public String getAuthor() {
	return author;
    }

    @Override
    public String toString() {
	return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }
}
