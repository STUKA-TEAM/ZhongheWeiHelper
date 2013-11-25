package order;

/**
 * @Title: PostMenu
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月23日
 */
public class PostMenu {
    private String operate;
    private String menuId;
    private int date;
    private String dishId;

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
     * @return the menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     *            the menuId to set
     */
    public void setMenuId(String menuId) {
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
     * @return the dishId
     */
    public String getDishId() {
        return dishId;
    }

    /**
     * @param dishId
     *            the dishId to set
     */
    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

}
