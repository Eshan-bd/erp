package com.brainstation23.erp.persistence.repository;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findAllByRole(Pageable pageable, ROLE role);
    Optional<UserEntity> findByUserName(String userName);

}
