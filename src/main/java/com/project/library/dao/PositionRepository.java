package com.project.library.dao;

import com.project.library.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
