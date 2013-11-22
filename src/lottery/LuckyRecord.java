package lottery;

/**
 * @Title: LuckyRecord
 * @Description: 中奖信息model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
public class LuckyRecord {
	private int luckyId;
	private int prizeId;
	private String openId;
	
	/**
	 * @return the luckyId
	 */
	public int getLuckyId() {
		return luckyId;
	}
	/**
	 * @param luckyId the luckyId to set
	 */
	public void setLuckyId(int luckyId) {
		this.luckyId = luckyId;
	}
	/**
	 * @return the prizeId
	 */
	public int getPrizeId() {
		return prizeId;
	}
	/**
	 * @param prizeId the prizeId to set
	 */
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
