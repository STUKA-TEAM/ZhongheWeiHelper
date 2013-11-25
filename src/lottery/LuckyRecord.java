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
	private String prizeKey;
	private int prizeStatus;
	
	/**
	 * @category constructor()
	 */
	public LuckyRecord() {
		super();
	}
	public LuckyRecord(int prizeId, String openId, String prizeKey,
			int prizeStatus) {
		super();
		this.prizeId = prizeId;
		this.openId = openId;
		this.prizeKey = prizeKey;
		this.prizeStatus = prizeStatus;
	}
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
	/**
	 * @return the prizeKey
	 */
	public String getPrizeKey() {
		return prizeKey;
	}
	/**
	 * @param prizeKey the prizeKey to set
	 */
	public void setPrizeKey(String prizeKey) {
		this.prizeKey = prizeKey;
	}
	/**
	 * @return the prizeStatus
	 */
	public int getPrizeStatus() {
		return prizeStatus;
	}
	/**
	 * @param prizeStatus the prizeStatus to set
	 */
	public void setPrizeStatus(int prizeStatus) {
		this.prizeStatus = prizeStatus;
	}
	
}
