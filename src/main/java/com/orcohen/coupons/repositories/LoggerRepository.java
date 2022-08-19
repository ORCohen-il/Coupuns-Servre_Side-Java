package com.orcohen.coupons.repositories;

import com.orcohen.coupons.beans.Logger_b;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger_b, Integer> {
}
