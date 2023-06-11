package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserInfoEntity;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {

}
