package org.lesson.fotoportfolio.repository;

import org.lesson.fotoportfolio.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByTitle(String title);
}