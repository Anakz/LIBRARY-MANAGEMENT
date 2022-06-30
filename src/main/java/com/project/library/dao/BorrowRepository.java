package com.project.library.dao;

import com.project.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Long countById(Long id);
}
