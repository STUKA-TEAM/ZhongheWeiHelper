package article;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tools.Constant;
import tools.GetSpecificImage;
import tools.ImageUtil;

@Controller
public class ArticleUserController {
@RequestMapping(value = "/user/articlebyid")
String articleById(@RequestParam(value = "articleId") String articleId, Model model){
	ApplicationContext context = 
			new ClassPathXmlApplicationContext("All-Modules.xml");
	ArticleDAO articleDAO = (ArticleDAO)context.getBean("ArticleDAO");
	Article article = articleDAO.getArticle(Integer.parseInt(articleId));
	GetSpecificImage getSpecificImage = new GetSpecificImage();
	article.setImagePath(getSpecificImage.getStandardImagePath(article.getImagePath()));
	model.addAttribute("article", article);
	return "article";
}


@RequestMapping(value = "/user/articles")
String articleListByClass(@RequestParam(value = "articleClassId") String articleClassId,
		Model model){
	ApplicationContext context = 
			new ClassPathXmlApplicationContext("All-Modules.xml");
	ArticleDAO articleDAO = (ArticleDAO)context.getBean("ArticleDAO");
	List<Article> aList= articleDAO.getArticleList(Integer.parseInt(articleClassId));
	
	ArticleClassDAO articleClassDAO = (ArticleClassDAO)context.getBean("ArticleClassDAO");
	ArticleClass articleClass = articleClassDAO.getArticleClass(Integer.parseInt(articleClassId));
	GetSpecificImage getSpecificImage = new GetSpecificImage();
	for (Article article : aList) {
		article.setImagePath(getSpecificImage.getStandardImagePath(article.getImagePath()));
	}
	model.addAttribute("articles", aList);
	if (articleClass.getArticleClassShowType() == Constant.SMALL_ITEM_LIST) {
		return "smallItemList";
	} else {
		return "largeItemList";
	}
}
}
