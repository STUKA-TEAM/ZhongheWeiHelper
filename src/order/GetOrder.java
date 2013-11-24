package order;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Title: GetOrder
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月23日
 */
public class GetOrder {
    private int orderId;
    private Date date;
    private String userName;
    private String address;
    private String phone;
    private String jsonDishes;
    private BigDecimal price;
    private int state;

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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the jsonDishes
     */
    public String getJsonDishes() {
        return jsonDishes;
    }

    /**
     * @param jsonDishes
     *            the jsonDishes to set
     */
    public void setJsonDishes(String jsonDishes) {
        this.jsonDishes = jsonDishes;
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
