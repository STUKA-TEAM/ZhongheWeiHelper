package lottery;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("registerValidator")
public class ActivityValidation implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return LotteryActivity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LotteryActivity lActivity = (LotteryActivity) target;
		
		if (lActivity.getLotteryName() == null) {
			errors.rejectValue("LotteryName", "标题不能为空");
		}
		if (lActivity.getLotterySummary() == null) {
			errors.rejectValue("LotterySummary", "描述不能为空");
		}

	}

}
