package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.AdonisMasterModel;

@Repository
public interface AdonisMasterDao extends JpaRepository<AdonisMasterModel, Long> {

}
