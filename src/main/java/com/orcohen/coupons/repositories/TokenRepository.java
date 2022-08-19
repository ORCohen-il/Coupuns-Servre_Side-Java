package com.orcohen.coupons.repositories;

import com.orcohen.coupons.beans.Company;
import com.orcohen.coupons.beans.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenInfo, Integer> {

    TokenInfo findBytoken(String token);

}
