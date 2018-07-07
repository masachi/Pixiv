package config;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebBrowser {
    private static final WebBrowser webBrowser = new WebBrowser();
    private static WebClient webClient;
    public static WebBrowser getInstance(){
        return webBrowser;
    }

    public static WebClient getWebClient() {
        webClient = new WebClient(BrowserVersion.CHROME);
//        webClient = new WebClient(BrowserVersion.CHROME, "127.0.0.1", 1087);
        webClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false); //禁用css支持
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待

        webClient.waitForBackgroundJavaScript(60*1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        return webClient;
    }

    public Document getPage(String url){
        try {
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            return Jsoup.parse(page.asXml());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public HtmlPage getOriginPage(String url){
        try {
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            return page;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
