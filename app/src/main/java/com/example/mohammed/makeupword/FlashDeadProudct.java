package com.example.mohammed.makeupword;

public class FlashDeadProudct {

    private int id;
    private String Discount;
    private String price;
    private int image ;

    public FlashDeadProudct(int id, String discount, String price, int image) {
        this.id = id;
        Discount = discount;
        this.price = price;
        this.image = image;
    } // end Constructor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


} // end Class
