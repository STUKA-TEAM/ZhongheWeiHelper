package order;

import java.util.List;

/**
 * @Title: PlaceOrder
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月22日
 */
public class PlaceOrder {
    private String openId;
    @SuppressWarnings("rawtypes")
    private List userOrder;

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     *            the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return the userOrder
     */
    @SuppressWarnings("rawtypes")
    public List getUserOrder() {
        return userOrder;
    }

    /**
     * @param userOrder
     *            the userOrder to set
     */
    public void setUserOrder(@SuppressWarnings("rawtypes") List userOrder) {
        this.userOrder = userOrder;
    }

}
