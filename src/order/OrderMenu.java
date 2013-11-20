package order;

/**
 * @Title: OrderMenu
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderMenu {
    private int menuId;
    private int date;
    private String dish;

    /**
     * @Title:
     * @Description:
     * @date 2013年11月18日
     */
    public OrderMenu() {
    }

    /**
     * @return the menuId
     */
    public int getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     *            the menuId to set
     */
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the date
     */
    public int getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * @return the dish
     */
    public String getDish() {
        return dish;
    }

    /**
     * @param dish
     *            the dish to set
     */
    public void setDish(String dish) {
        this.dish = dish;
    }

}
