package article;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleUserController {
@RequestMapping(value = "/user/articlebyid")
String articleById(@RequestParam(value = "articleId") String articleId, Model model){
	ApplicationContext context = 
			new ClassPathXmlApplicationContext("All-Modules.xml");
	ArticleDAO articleDAO = (ArticleDAO)context.getBean("ArticleDAO");
	Article article = articleDAO.getArticle(Integer.parseInt(articleId));
	model.addAttribute("article", article);
	return "article";
}

}
