package lottery;

/**
 * @Title: LotteryRecord
 * @Description: 抽奖记录信息model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
public class LotteryRecord {	
	private int recordId;
	private int lotteryId;
	private String openId;
	private int lotteryResult;
	
	/**
	 * @category constructor()
	 */
	public LotteryRecord(){
		super();
	}
	
	public LotteryRecord(int lotteryId, String openId, int lotteryResult) {
		super();
		this.lotteryId = lotteryId;
		this.openId = openId;
		this.lotteryResult = lotteryResult;
	}
	
	/**
	 * @return the recordId
	 */
	public int getRecordId() {
		return recordId;
	}
	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	/**
	 * @return the lotteryId
	 */
	public int getLotteryId() {
		return lotteryId;
	}
	/**
	 * @param lotteryId the lotteryId to set
	 */
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
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
	 * @return the lotteryResult
	 */
	public int getLotteryResult() {
		return lotteryResult;
	}
	/**
	 * @param lotteryResult the lotteryResult to set
	 */
	public void setLotteryResult(int lotteryResult) {
		this.lotteryResult = lotteryResult;
	}
}
