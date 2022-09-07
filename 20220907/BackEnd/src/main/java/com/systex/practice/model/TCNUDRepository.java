package com.systex.practice.model;

import com.systex.practice.model.entity.HCMIOAndTCNUDCompositeKey;
import com.systex.practice.model.entity.TCNUD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCNUDRepository extends JpaRepository<TCNUD, HCMIOAndTCNUDCompositeKey> {

    List<TCNUD> findByCustSeqAndStock(String custSeq, String stock);

    List<TCNUD> findByCustSeq(String custSeq);

}
