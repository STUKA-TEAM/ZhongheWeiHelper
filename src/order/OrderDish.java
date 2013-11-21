package order;

import java.math.BigDecimal;

public class OrderDish {
    private int dishId;
    private String dishName;
    private BigDecimal price;
    private String discribe;
    private String picture;

    public OrderDish() {
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
