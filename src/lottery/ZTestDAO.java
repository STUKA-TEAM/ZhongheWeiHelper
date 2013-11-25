package lottery;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tools.Constant;

public class ZTestDAO {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		LuckyRecordDAO luckyRecordDAO = (LuckyRecordDAO) context.getBean("LuckyRecordDAO");		
		
		LotteryActivity lActivity = new LotteryActivity();
		List<LotteryPrize> lPrizes = new ArrayList<>();
		
		lActivity.setLotteryStatus(3);
		LotteryPrize lp1 = new LotteryPrize();  lp1.setPrizeName("一等奖"); lp1.setPrizeContent("iPhone5S一部"); lp1.setLuckyNum(3); lp1.setLuckyPercent(new BigDecimal(0.01));
		LotteryPrize lp2 = new LotteryPrize();  lp2.setPrizeName("二等奖"); lp2.setPrizeContent("香港3日游"); lp2.setLuckyNum(15); lp2.setLuckyPercent(new BigDecimal(0.1));
		lPrizes.add(lp1); lPrizes.add(lp2);
		lActivity.setLpList(lPrizes);
		lActivity.setChanceNum(3);
		lActivity.setLotteryId(3);
	//	lActivityDAO.insertActivity(lActivity);
	//	lActivityDAO.updateActivity(lActivity);
	//	lActivityDAO.deleteByLotteryId(3);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	//	lActivityDAO.updateStartDateAndStatus(1, timestamp, Constant.ACTIVITY_RELEASE_STATUS);
		int num = luckyRecordDAO.getActualNum(1);
		System.out.println(num);
	}

}
