package spider;

import config.Spider;
import config.WebBrowser;
import org.jsoup.nodes.Document;

public class YuyuyuSpider extends Spider {

    public YuyuyuSpider(String outPath) {
        super(outPath);
        WebBrowser.getWebClient();
    }

    @Override
    public void spider() throws Exception {
        Document document = WebBrowser.getInstance().getPage("https://book.douban.com/tag/小说");
        System.out.println(document.select("li.subject-item").toString());
    }
}
