package config;

public abstract class Spider {
    protected String outPath;

    public Spider(String outPath) {
        this.outPath = outPath;
    }

    public abstract void spider() throws Exception;
}
