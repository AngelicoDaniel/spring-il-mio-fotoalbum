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


        @Autowired
        private PhotoRepository photoRepository;



    @GetMapping
    public List<Photo> index(){
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


        @PutMapping("/{id}")
        public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo pizza) {
            pizza.setId(id);
            return photoRepository.save(pizza);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Integer id) {
            photoRepository.deleteById(id);
        }
    }
}