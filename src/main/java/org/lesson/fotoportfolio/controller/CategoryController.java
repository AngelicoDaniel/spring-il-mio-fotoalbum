package org.lesson.fotoportfolio.controller;

import jakarta.validation.Valid;
import org.lesson.fotoportfolio.model.Category;
import org.lesson.fotoportfolio.model.Photo;
import org.lesson.fotoportfolio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model, @RequestParam("edit") Optional<Integer> categoryId) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        Category categoryObj;
        if (categoryId.isPresent()) {
            Optional<Category> categoryDb = categoryRepository.findById(categoryId.get());
            if (categoryDb.isPresent()) {
                categoryObj = categoryDb.get();
            } else {
                categoryObj = new Category();
            }
        } else {
            categoryObj = new Category();
        }
        model.addAttribute("categoryObj", categoryObj);
        return "/categories/index";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("categoryObj") Category formCategory,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "/categories/index";
        }
        // salvare la categoria
        categoryRepository.save(formCategory);
        // fa la redirect alla index
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Category categoryToDelete = result.get();
        for (Photo photo : categoryToDelete.getPhotos()) {
            photo.getCategories().remove(categoryToDelete);
        }

        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
