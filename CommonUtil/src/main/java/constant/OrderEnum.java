package constant;

public enum OrderEnum {
   wait_accept("0"),wait_send("1"),end_over("2"),alread_send("3"),wait_payment("4"),no_accept("5"),wait_accept_inventory("6");
   private final String order;
   private OrderEnum(String order){
       this.order = order;
   }

    public String getOrder() {
        return order;
    }
}
