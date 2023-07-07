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
    @Autowired //Photo controller dipende dall'interfaccia PhotoRepository che tramite jpa comunica con il DB
    private PhotoRepository photoRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    //INDEX
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

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Photo photo = getPhotoById(id);//eccezione gia' gestita nel metodo
        model.addAttribute("photo", photo);
        return "/photos/show";
    }

    //CREATE GET PER MOSTRARE IL FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("categories", categoryRepository.findAll());
        // return "/books/create"; // template con form di creazione di un book
        return "/photos/form"; // template unico per create e edit
    }

    //CREATE POST PER RICEVERE I CAMPI COMPILATI
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // ci sono stati errori
            return "/photos/form"; // template unico per create e edit
        } else {
            formPhoto.setCreatedAt(LocalDateTime.now());
            photoRepository.save(formPhoto);
            redirectAttributes.addFlashAttribute("message", "Photo " + formPhoto.getTitle() + " created!");
            return "redirect:/photos";
        }
    }

    //UPDATE GET
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

    //UPDATE POST
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Photo PhotoToEdit = getPhotoById(id); //fotografia della photo pre-modifica
//        trasferisco su formPhoto tutti i dati che non sono presenti nel form(altrimenti li perdo)
        formPhoto.setId(PhotoToEdit.getId());
        formPhoto.setCreatedAt(PhotoToEdit.getCreatedAt());
//        lascio che photoRepository passi i dati al DB
        photoRepository.save(formPhoto);
        redirectAttributes.addFlashAttribute("message", "Photo " + formPhoto.getTitle() + " updated!");
        return "redirect:/photos";
    }

    //DELETE

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // verifichiamo che esiste il book con quell'id
        Photo photoToDelete = getPhotoById(id);
        // lo cancelliamo
        photoRepository.delete(photoToDelete);
        // aggiungo un messaggio di successo come flashAttribute
        redirectAttributes.addFlashAttribute("message", "Photo " + photoToDelete.getTitle() + " deleted!");
        // facciamo la redirect alla lista dei book
        return "redirect:/photos";
    }
    //METODI CUSTOM
    //Metodo per selezionare pizza da DB per ID o lancio di eccezione

    private Photo getPhotoById(Integer id) {
//    ver pizza con quell'id' uso findById di Repository per creare un Optional
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isEmpty()) {
//    se non esiste lancio eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la pizza con id" + id + "non e' stata trovata"); //eccezione che deve ridare una risposta http quindi uno status
//    aggiungo la foto al model(get restituisce la Photo che in Optional(che e' un contenitore di oggetti Photo))

        }
        return result.get();


    }
}