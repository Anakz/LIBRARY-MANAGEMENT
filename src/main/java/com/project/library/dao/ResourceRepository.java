package com.project.library.dao;

import com.project.library.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Long countById(Long id);

    List<Resource> findTop3ByOrderById();
}
