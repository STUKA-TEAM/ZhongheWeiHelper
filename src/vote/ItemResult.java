package vote;

/**
 * @Title: ItemResult
 * @Description: 投票单项结果统计
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
public class ItemResult {
	private VoteItem voteItem;
	private int count;
	
	/**
	 * @category constructor()
	 * @param voteItem
	 * @param count
	 */
	public ItemResult() {
		super();
	}
	
	public ItemResult(VoteItem voteItem, int count) {
		super();
		this.voteItem = voteItem;
		this.count = count;
	}
	
	/**
	 * @return the voteItem
	 */
	public VoteItem getVoteItem() {
		return voteItem;
	}
	/**
	 * @param voteItem the voteItem to set
	 */
	public void setVoteItem(VoteItem voteItem) {
		this.voteItem = voteItem;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
}
