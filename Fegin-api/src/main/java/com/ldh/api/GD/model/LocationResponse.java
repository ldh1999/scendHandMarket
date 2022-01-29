package com.ldh.api.GD.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//反序列化有问题
@Data
public class LocationResponse implements Serializable {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<Geocodes> geocodes = new ArrayList<>();
    @Data
    private static class Geocodes implements Serializable{
        private String formatted_address;
        private String country;
        private String province;
        private String citycode;
        private String city;
        private String[] district;
        private String adcode;
        private List<Number> number;
        private String location;
        private String level;
    }
}
