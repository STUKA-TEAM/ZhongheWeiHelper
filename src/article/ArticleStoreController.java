package article;

import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class ArticleStoreController {
@RequestMapping(value = "/store/initialadd", method = RequestMethod.GET)
public String initial(){
	return "initialAdd";
}

@RequestMapping(value = "/store/add", method = RequestMethod.GET)
@ResponseBody
public String addArticle(@RequestBody String json){
try {
	ApplicationContext context = 
			new ClassPathXmlApplicationContext("All-Modules.xml");
	ArticleDAO articleDAO = (ArticleDAO)context.getBean("ArticleDAO");
	Gson gson = new Gson();
	Article article = gson.fromJson(json, Article.class);
	Timestamp current = new Timestamp(System.currentTimeMillis());
	article.setDate(current);
	articleDAO.insertArticle(article);
	return article.getDate().toString();
} catch (Exception e) {
	return "n";
}
}
}
