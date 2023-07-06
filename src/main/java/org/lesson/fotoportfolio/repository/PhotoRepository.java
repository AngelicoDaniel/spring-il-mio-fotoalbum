package org.lesson.fotoportfolio.repository;

import org.lesson.fotoportfolio.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    // metodo per cercare sul DB in base al titolo della foto
//    List<Photo> findByTitle(String title);
    List<Photo> findByTitleContainingIgnoreCase(String title);


}