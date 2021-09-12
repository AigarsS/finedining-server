package com.school.finediningserver.repositories;

import com.school.finediningserver.model.ERole;
import com.school.finediningserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByName(ERole name);

}
