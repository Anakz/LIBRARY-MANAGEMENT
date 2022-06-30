package com.project.library.dao;

import com.project.library.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
