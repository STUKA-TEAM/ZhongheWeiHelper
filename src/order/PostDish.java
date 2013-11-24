package order;

import java.math.BigDecimal;

/**
 * @Title: PostDish
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月23日
 */
public class PostDish {
    private String operate;
    private String dishName;
    private BigDecimal price;
    private String decribe;
    private String picture;

    /**
     * @return the operate
     */
    public String getOperate() {
        return operate;
    }

    /**
     * @param operate
     *            the operate to set
     */
    public void setOperate(String operate) {
        this.operate = operate;
    }

    /**
     * @return the dishName
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * @param dishName
     *            the dishName to set
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the decribe
     */
    public String getDecribe() {
        return decribe;
    }

    /**
     * @param decribe
     *            the decribe to set
     */
    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture
     *            the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
