package org.lesson.fotoportfolio.api;

import jakarta.validation.Valid;
import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/photos")
public class PhotoRestController {
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public List<Photo> index() {
        return photoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Photo get(@PathVariable Integer id) {
        Optional<Photo> photoById = photoRepository.findById(id);
        if (photoById.isPresent()) {
            return photoById.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Photo create(@Valid @RequestBody Photo photo) {
        try {
            return photoRepository.save(photo);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        photo.setId(id);
        return photoRepository.save(photo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        photoRepository.deleteById(id);
    }

    @GetMapping("/page")
    public Page<Photo> page(
            Pageable pageable) {
        return photoRepository.findAll(pageable);
    }


}