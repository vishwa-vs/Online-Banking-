package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.AuditingEntity;

@Repository
public interface AuditingRepository extends JpaRepository<AuditingEntity,Long> {

}
