import config.Spider;
import login.PixivLogin;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import spider.PixivBookmarkSpider;
import spider.YuyuyuSpider;

public class PixivSpider {
    private static String outPath = null;
    public static void main(String[] args){
        PixivLogin login;

        try {
            login = processArguments(args);
            if(login == null) {
                System.out.println("参数错误");
            }
            else{
                //Spider bookmark = new PixivBookmarkSpider(outPath);
                //login.loginToPixiv();
                //bookmark.spider();
                Spider yuyuyu = new YuyuyuSpider(outPath);
                yuyuyu.spider();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static PixivLogin processArguments(String[] args){
        CommandLineValues values = new CommandLineValues();
        CmdLineParser parser = new CmdLineParser(values);

        PixivLogin login = null;
        try{
            parser.parseArgument(args);
            String username = values.username;
            String password = values.password;
            outPath = values.outPath;

            if(username == null || password == null || outPath == null){
                parser.printUsage(System.err);
                throw new IllegalArgumentException();
            }

            login = new PixivLogin(username, password);

        }
        catch (CmdLineException e){
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }

        return login;
    }

    public static class CommandLineValues {

        @Option(name = "-username", aliases = {"-u", "-user", "-username"}, required = true,  metaVar = "<string>",
                usage = "Specifies username for pixiv")
        public String username = null;


        @Option(name = "-password", aliases = {"-p", "-password"}, required = true, metaVar = "<string>",
                usage = "Specifies password for pixiv")
        public String password = null;

        @Option(name = "-output", aliases = {"-o", "-out", "-output"}, required = true, metaVar = "<string>",
                usage = "Specifies output path")
        public String outPath = null;

        @Option(name = "-proxy", aliases = {"-proxy"}, required = false, metaVar = "<string>",
                usage = "proxy")
        public String proxy = null;

        @Option(name = "-port", aliases = {"-port"}, required = false, metaVar = "<string>",
                usage = "proxy port")
        public String port = null;

    }
}
