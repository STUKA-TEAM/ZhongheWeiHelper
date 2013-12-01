package lottery;

import java.math.BigDecimal;

/**
 * @Title: LotteryPrize
 * @Description: 奖品信息配置model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
public class LotteryPrize {
	private int prizeId;
	private int lotteryId;
	private String prizeName;
	private String prizeContent;
	private int luckyNum;
	private BigDecimal luckyPercent;
	
	/**
	 * @category constructor()
	 */
	public LotteryPrize(){
		
	}
	
	public LotteryPrize(int lotteryId, String prizeName, String prizeContent, int luckyNum, 
			BigDecimal luckyPercent){
		this.setLotteryId(lotteryId);
		this.prizeName = prizeName;
		this.prizeContent = prizeContent;
		this.luckyNum = luckyNum;
		this.luckyPercent = luckyPercent;
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
	 * @return the prizeName
	 */
	public String getPrizeName() {
		return prizeName;
	}
	/**
	 * @param prizeName the prizeName to set
	 */
	public void setPrizeName(String prizeNameString) {
		this.prizeName = prizeNameString;
	}
	/**
	 * @return the prizeContent
	 */
	public String getPrizeContent() {
		return prizeContent;
	}
	/**
	 * @param prizeContent the prizeContent to set
	 */
	public void setPrizeContent(String prizeContent) {
		this.prizeContent = prizeContent;
	}
	/**
	 * @return the luckyNum
	 */
	public int getLuckyNum() {
		return luckyNum;
	}
	/**
	 * @param luckyNum the luckyNum to set
	 */
	public void setLuckyNum(int luckyNum) {
		this.luckyNum = luckyNum;
	}
	/**
	 * @return the luckyPercent
	 */
	public BigDecimal getLuckyPercent() {
		return luckyPercent;
	}
	/**
	 * @param luckyPercent the luckyPercent to set
	 */
	public void setLuckyPercent(BigDecimal luckyPercent) {
		this.luckyPercent = luckyPercent;
	}	
}
