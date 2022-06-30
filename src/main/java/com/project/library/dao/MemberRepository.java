package com.project.library.dao;

import com.project.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countById(Long id);

    Member findByEmailAndCin(String email, String cin);

    Long countByEmail(String email);

    Long countByCin(String cin);
}
