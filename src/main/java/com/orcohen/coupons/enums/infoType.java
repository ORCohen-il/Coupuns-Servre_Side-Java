package com.orcohen.coupons.enums;

public enum infoType {

    email(1), username(2), password(3) , image(4)
    ,name(5) ;

    private final int id;

    private infoType(int id) {
        this.id = id;
    }

    public int getid() {
        return id;
    }



}
