package tools;

import java.util.Random;

/**
 * @Title: RandomUtil
 * @Description: 提供常见的随机字符串生成方法
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月22日
 */
public class RandomUtil {
	
	/**
	 * @Title: generateMixedString
	 * @Description: 返回一个定长的随机字符串(包含大小写字母、数字)
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static final String generateMixedString(int seed, int length){
		StringBuffer sb = new StringBuffer();
		Random rand = new Random(seed);
		for (int i = 0; i < length; i++) {
			sb.append(Constant.ALL_CHAR.charAt(rand.nextInt(62)));
		}
		
		return sb.toString();
	}
}
