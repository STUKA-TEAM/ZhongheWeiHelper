package lottery;

/**
 * @Title: LotteryResult
 * @Description: 实际中奖情况单项统计
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月24日
 */
public class LotteryResult {
	private LotteryPrize lotteryPrize;
	private int actualNum;
	
	/**
	 * @return the lotteryPrize
	 */
	public LotteryPrize getLotteryPrize() {
		return lotteryPrize;
	}
	/**
	 * @param lotteryPrize the lotteryPrize to set
	 */
	public void setLotteryPrize(LotteryPrize lotteryPrize) {
		this.lotteryPrize = lotteryPrize;
	}
	/**
	 * @return the actualNum
	 */
	public int getActualNum() {
		return actualNum;
	}
	/**
	 * @param actualNum the actualNum to set
	 */
	public void setActualNum(int actualNum) {
		this.actualNum = actualNum;
	}
	
}
