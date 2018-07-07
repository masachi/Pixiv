package login;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import config.Pixiv;
import config.WebBrowser;
import constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Set;

public class PixivLogin extends Pixiv {

    public PixivLogin(String username, String password) {
        super(username, password);
    }

    public static HashMap<String, String> responseCookies = new HashMap<>();

    public void loginToPixiv() {
        WebClient webClient = WebBrowser.getWebClient();
        HtmlPage page = WebBrowser.getInstance().getOriginPage(Constant.LOGIN_URL);
        HtmlForm form = (HtmlForm) page.getElementById("LoginComponent").getFirstElementChild();
        HtmlTextInput userInput = (HtmlTextInput) form.getInputsByName("").get(0);
        HtmlPasswordInput passwordInput = (HtmlPasswordInput) form.getInputsByName("").get(1);
        userInput.setValueAttribute(username);
        passwordInput.setValueAttribute(password);
        try {
            HtmlPage loginPage = (HtmlPage) ((HtmlButton) form.getButtonsByName("").get(0)).click();
            webClient.waitForBackgroundJavaScript(10000);
            Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            System.out.println("login success");
            for(Cookie cookie : cookies){
                responseCookies.put(cookie.getName(), cookie.getValue());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
//        Document doc = WebBrowser.getInstance().getPage(webClient, loginUrl);
//        Elements forms = doc.getElementById("LoginComponent").select(".input-field");
//        Element userInput = forms.get(0).select("input").get(0).val(username);
//        Element passwordInput = forms.get(1).select("input").get(0).val(password);

    }
}
