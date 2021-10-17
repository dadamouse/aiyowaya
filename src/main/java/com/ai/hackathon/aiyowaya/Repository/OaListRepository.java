package com.ai.hackathon.aiyowaya.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.hackathon.aiyowaya.model.OaListEntity;

public interface OaListRepository extends JpaRepository<OaListEntity, Long> {

    public List<OaListEntity> findAllByIntention(String intention);
}
