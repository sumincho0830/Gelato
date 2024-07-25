package org.techtown.gelato;

public class Item {
    int cartPosition;
    int marketPosition;
    String name;
    String price;
    int imageRes;
    String detail;
    boolean isAddedToCart;




    public void setMarketPosition(int marketPosition) {
        this.marketPosition = marketPosition;
    }

    public Item(String name, String price, int imageRes, String detail) {
        this.name = name;
        this.imageRes = imageRes;
        this.detail = detail;
        this.price = price;
        this.isAddedToCart = false; //맨 처음 false로 초기화
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = "₩ "+price;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public  void setCartPosition(int position){
        cartPosition = position;
    }

    public void setAddedToCart(boolean addedToCart) {
        isAddedToCart = addedToCart;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getDetail() {
        return detail;
    }
    public  int getCartPosition(){
        return cartPosition;
    }
    public boolean isAddedToCart() {
        return isAddedToCart;
    }
    public int getMarketPosition() {
        return marketPosition;
    }
}
