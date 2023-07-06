package org.lesson.fotoportfolio.repository;

import org.lesson.fotoportfolio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}