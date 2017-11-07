package com.minor2k17.nimble;

/**
 * Created by shaur on 04-11-2017.
 */

public class Books_getter_function {
    private String book_name,book_price,email,description;
    private String contact_number;
    private int imageId;

    public Books_getter_function(String book_name, String book_price, String email, String description, String contact_number, int imageId) {
        this.book_name = book_name;
        this.book_price = book_price;
        this.email = email;
        this.description = description;
        this.contact_number = contact_number;
        this.imageId = imageId;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_price() {
        return book_price;
    }

    public String getemail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getContact_number() {
        return contact_number;
    }

    public int getImageId() {
        return imageId;
    }
}
