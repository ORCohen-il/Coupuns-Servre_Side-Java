package com.orcohen.coupons.beans;

import com.orcohen.coupons.enums.ClientType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "TokenInfo")
public class TokenInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;
    private Date dateCreated;
    private int clientType;

    public static TokenInfo generateNewToken(ClientType clientType) {
        return TokenInfo.builder()
                .token(UUID.randomUUID().toString())
                .dateCreated(new Date(System.currentTimeMillis()))
                .clientType(clientType.getid())
                .build();

    }


}
