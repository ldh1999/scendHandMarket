package com.ldh.api.GD.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

//反序列化有问题
@Data
public class PositionNameResponse implements Serializable {

    private String status;

    private Regeocode regeocode;

    private String info;

    private String infocode;

    @Data
    private static class StreetNumber  implements Serializable{
        private String number;

        private String location;

        private String direction;

        private String distance;

        private String street;

    }

    @Data
    private static class Building implements Serializable{
        private List<String> name;

        private List<String> type;
    }

    @Data
    private static class Neighborhood implements Serializable{
        private List<String> name;

        private List<String> type;


    }

    @Data
    private static class BusinessAreas implements Serializable{
        private String location;

        private String name;

        private String id;

    }

    @Data
    private static class AddressComponent implements Serializable{

        private String province;

        private String adcode;

        private String district;

        private String towncode;

        private StreetNumber streetNumber;

        private List<String> country;

        private String township;

        private List<BusinessAreas> businessAreas ;

        private Building building;

//        private Neighborhood neighborhood;

        private String citycode;


    }

    @Data
    private static class Regeocode implements Serializable {
        private AddressComponent addressComponent;

        private List<String> formatted_address;

    }

}
