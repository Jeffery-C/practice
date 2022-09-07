package com.systex.practice.model;

import com.systex.practice.model.entity.MSTMB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MSTMBRepository extends JpaRepository<MSTMB, String> {

    MSTMB findByStock(String stock);

}
