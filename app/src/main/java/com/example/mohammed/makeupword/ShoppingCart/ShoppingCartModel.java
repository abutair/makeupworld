package com.example.mohammed.makeupword.ShoppingCart;

public class ShoppingCartModel {
    String user_Id,Price,Title,Image;

    boolean isSelected ;


    public ShoppingCartModel() {


    }

    public ShoppingCartModel(String user_Id, String price, String title, String image) {
        this.user_Id = user_Id;
        Price = price;
        Title = title;
        Image = image;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isSelected()
    {
            return isSelected;
    }

    public  void setSelected(boolean selected)
    {
        isSelected= selected;
    }
} // end Class
