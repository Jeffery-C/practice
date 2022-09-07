package com.systex.practice.model;

import com.systex.practice.model.entity.HCMIO;
import com.systex.practice.model.entity.HCMIOAndTCNUDCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HCMIORepository extends JpaRepository<HCMIO, HCMIOAndTCNUDCompositeKey> {
    List<HCMIO> findByCustSeqAndStock(String custSeq, String stock);
}
