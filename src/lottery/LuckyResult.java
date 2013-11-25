package lottery;

/**
 * @Title: LuckyResult
 * @Description: 用户单次中奖信息传输model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月25日
 */
public class LuckyResult {
	private String prizeName;
	private String prizeContent;
	private String prizeKey;
	private int chanceLeft;
	
	/**
	 * @category constructor()
	 */
	public LuckyResult() {
		super();
	}
    public LuckyResult(String prizeName, String prizeContent, String prizeKey,
			int chanceLeft) {
		super();
		this.setPrizeName(prizeName);
		this.setPrizeContent(prizeContent);
		this.setPrizeKey(prizeKey);
		this.setChanceLeft(chanceLeft);
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
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
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
	 * @return the chanceLeft
	 */
	public int getChanceLeft() {
		return chanceLeft;
	}
	/**
	 * @param chanceLeft the chanceLeft to set
	 */
	public void setChanceLeft(int chanceLeft) {
		this.chanceLeft = chanceLeft;
	}   
}
