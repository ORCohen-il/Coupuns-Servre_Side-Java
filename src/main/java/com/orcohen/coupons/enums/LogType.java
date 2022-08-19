package com.orcohen.coupons.enums;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum LogType {

    Nan(0), Accept(1), Eror(2), In_proces(3);

    private int id;

    private LogType(int id) {
        this.id = id;
    }

    public int getid() {
        return id;
    }

}
