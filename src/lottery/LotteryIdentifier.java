package lottery;

/**
 * @Title: LotteryIdentifier
 * @Description: 定位某一用户参与某个活动
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月25日
 */
public class LotteryIdentifier {
	private int lotteryId;
	private String openId;
	
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
	
	
}
