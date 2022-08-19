package com.orcohen.coupons.beans;

import com.orcohen.coupons.enums.LogType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LoggerSave")
@Data
public class Logger_b {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LogType logAction;
    private String LogMessage;
    private Date LogTime;

    public Logger_b(LogType logAction, String logMessage, Date logTime) {
        this.logAction = logAction;
        this.LogMessage = logMessage;
        this.LogTime = logTime;
    }

}
