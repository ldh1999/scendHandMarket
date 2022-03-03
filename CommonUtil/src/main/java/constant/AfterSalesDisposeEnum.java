package constant;

public enum AfterSalesDisposeEnum {
    reject("reject"),end("end"),wait_merchant("wait_merchant");
    private final String sts;
    private AfterSalesDisposeEnum(String sts){
        this.sts = sts;
    }
    public String getSts() {
        return sts;
    }
}
