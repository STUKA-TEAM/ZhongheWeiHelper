package lottery;

import tools.ResponseMessage;

/**
 * @Title: ValidationMessage
 * @Description: 验证消息返回
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月28日
 */
public class ValidationMessage extends ResponseMessage {
	private String link;

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
