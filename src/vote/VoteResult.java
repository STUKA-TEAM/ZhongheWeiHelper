package vote;

import java.util.List;

/**
 * @Title: VoteResult
 * @Description: 投票结果模型
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
public class VoteResult {

	private List<ItemResult> itemResult;
	private List<Advice> adviceList;		

	/**
	 * @return the itemResult
	 */
	public List<ItemResult> getItemResult() {
		return itemResult;
	}

	/**
	 * @param itemResult the itemResult to set
	 */
	public void setItemResult(List<ItemResult> itemResult) {
		this.itemResult = itemResult;
	}

	/**
	 * @return the adviceList
	 */
	public List<Advice> getAdviceList() {
		return adviceList;
	}

	/**
	 * @param adviceList the adviceList to set
	 */
	public void setAdviceList(List<Advice> adviceList) {
		this.adviceList = adviceList;
	}
}
