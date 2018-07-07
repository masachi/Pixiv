package spider;

import config.Spider;
import config.WebBrowser;
import constant.Constant;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.DownloadModule;


public class PixivBookmarkSpider extends Spider {

    public PixivBookmarkSpider(String outPath) {
        super(outPath);
    }

    @Override
    public void spider() throws Exception {
        Document bookmarkDocument = WebBrowser.getInstance().getPage(Constant.BOOKMARK_URL);

        Elements thumbnails = bookmarkDocument.select("div.display_editable_works").select("div._layout-thumbnail").select("img");

        if(thumbnails.size() > 0) {
            for (Element thumbnail : thumbnails) {
                String thumb = thumbnail.attr("data-src");
                String filename = outPath.endsWith("/") ? outPath + thumb.substring(thumb.lastIndexOf("/") + 1) : outPath + thumb.substring(thumb.lastIndexOf("/"));
                System.out.println(filename);
                DownloadModule.download(getOriginImage(thumb), filename);
            }
        }
        else{

        }
    }

    private String getOriginImage(String thumbnail) {
        return thumbnail.substring(0, thumbnail.lastIndexOf("_")).replace("c/150x150/img-master", "img-original") + ".png";
    }
}
