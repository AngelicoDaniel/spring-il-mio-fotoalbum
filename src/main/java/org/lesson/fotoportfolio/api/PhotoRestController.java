package org.lesson.fotoportfolio.api;

import jakarta.validation.Valid;
import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoRestController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping
    public List<Photo> index() {
        return photoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Photo get(@PathVariable Integer id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            return photo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Photo create(@Valid @RequestBody Photo photo) {
        return photoRepository.save(photo);
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo updatedPhoto) {
        Optional<Photo> existingPhoto = photoRepository.findById(id);
        if (existingPhoto.isPresent()) {
            Photo photo = existingPhoto.get();
            photo.setTitle(updatedPhoto.getTitle());
            photo.setDescription(updatedPhoto.getDescription());
            photo.setUrl(updatedPhoto.getUrl());
            photo.setVisible(updatedPhoto.isVisible());
            return photoRepository.save(photo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            photoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
