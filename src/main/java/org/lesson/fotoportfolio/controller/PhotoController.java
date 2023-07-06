package org.lesson.fotoportfolio.controller;

import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired //Photo controller dipende dall'interfaccia PhotoRepository che tramite jpa comunica con il DB
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model) {
        List<Photo> photos;
        photos = photoRepository.findAll();
        model.addAttribute("photos", photos);
        return "/photos/list";
    }
}