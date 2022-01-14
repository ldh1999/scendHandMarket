package constant;

public enum OrderPhysicalEnum {
    wait_courier("wait_courier"),
    signed("signed"),
    sending("sending"),
    sended("sended");

    private final String orderPhysical;

    private OrderPhysicalEnum(String orderPhysical) {
        this.orderPhysical = orderPhysical;
    }

    public String getOrderPhysical() {
        return orderPhysical;
    }
}
