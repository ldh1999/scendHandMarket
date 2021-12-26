package com.ldh.constant;

public enum UserStsEnum {

    normal("1"),freeze("2"),delete("-1");
    private final String userSts;

    UserStsEnum(String userSts) {
        this.userSts = userSts;
    }

    public String getUserSts() {
        return userSts;
    }
}
