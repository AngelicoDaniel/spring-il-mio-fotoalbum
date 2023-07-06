package org.lesson.fotoportfolio.controller;

import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired //Photo controller dipende dall'interfaccia PhotoRepository che tramite jpa comunica con il DB
    private PhotoRepository photoRepository;

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
        return "photos/show";
    }

    //CREATE GET PER MOSTRARE IL FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        return "photos/form";
    }


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