package constant;

public enum AfterSalesInventoryMode {
    exchange("exchange"),back("back");
    private final String sts;
    private AfterSalesInventoryMode(String sts){
        this.sts = sts;
    }
    public String getSts() {
        return sts;
    }
}
