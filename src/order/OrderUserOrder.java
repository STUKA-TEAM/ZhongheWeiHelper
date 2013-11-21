package order;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Title: OrderUserOrder
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderUserOrder {
    private int orderId;
    private int userId;
    private Date date;
    private int dishId;
    private int dishNum;
    private BigDecimal price;
    private int state;

    /**
     * @Title:
     * @Description:
     * @date 2013年11月18日
     */
    public OrderUserOrder() {
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the dishId
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * @param dishId
     *            the dishId to set
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * @return the dishNum
     */
    public int getDishNum() {
        return dishNum;
    }

    /**
     * @param dishNum
     *            the dishNum to set
     */
    public void setDishNum(int dishNum) {
        this.dishNum = dishNum;
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
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

}
