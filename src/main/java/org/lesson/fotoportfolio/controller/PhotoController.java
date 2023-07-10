package org.lesson.fotoportfolio.controller;

import jakarta.validation.Valid;
import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.CategoryRepository;
import org.lesson.fotoportfolio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchString, Model model) {
        List<Photo> photos;
        if (searchString == null || searchString.isBlank()) {
            photos = photoRepository.findAll();
        } else {
            photos = photoRepository.findByTitleContainingIgnoreCase(searchString);
        }

        model.addAttribute("photos", photos);
        return "/photos/list";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Photo photo = getPhotoById(id);//eccezione gia' gestita nel metodo
        model.addAttribute("photo", photo);
        return "/photos/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("categories", categoryRepository.findAll());
        return "/photos/form";
    }


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/photos/form";
        } else {
            formPhoto.setCreatedAt(LocalDateTime.now());
            photoRepository.save(formPhoto);
            redirectAttributes.addFlashAttribute("message", "Photo " + formPhoto.getTitle() + " created!");
            return "redirect:/photos";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo photoById = getPhotoById(id);
            model.addAttribute("photo", photoById);
            model.addAttribute("categories", categoryRepository.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "/photos/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Photo PhotoToEdit = getPhotoById(id); //fotografia della photo pre-modifica
        formPhoto.setId(PhotoToEdit.getId());
        formPhoto.setCreatedAt(PhotoToEdit.getCreatedAt());
        photoRepository.save(formPhoto);
        redirectAttributes.addFlashAttribute("message", "Photo " + formPhoto.getTitle() + " updated!");
        return "redirect:/photos";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Photo photoToDelete = getPhotoById(id);
        photoRepository.delete(photoToDelete);
        redirectAttributes.addFlashAttribute("message", "Photo " + photoToDelete.getTitle() + " deleted!");
        return "redirect:/photos";
    }


    //METODI CUSTOM
    private Photo getPhotoById(Integer id) {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la foto con Id " + id + " non e' stata trovata");

        }
        return result.get();
    }
}