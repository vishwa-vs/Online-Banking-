package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DealerInfoEntity;
import com.example.demo.model.PriceListEntity;

public interface DealerInfoRepository extends JpaRepository<DealerInfoEntity, Integer> {




}
