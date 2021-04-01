package com.mdudzisz.histogramapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/histogram-app")
public class RequestHandler {

    @Autowired
    ImageService imageService;

    @GetMapping
    public String homePage(Model model) {
        renderHomePage(model);
        return "home-page";
    }

    @PostMapping
    public String submitImage(
            @RequestParam MultipartFile file,
            @RequestParam String author,
            @RequestParam String description,
            Model model) {

        if (file.isEmpty()) {
            renderHomePage(model, "Unable to save - file is empty!");
            return "home-page";
        }

        try {
            imageService.saveFromFormParams(file, author, description);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            renderHomePage(model, "Cannot save file due to server error.");
            return "home-page";
        }

        renderHomePage(model, "File Submitted!");
        return "home-page";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@PathVariable int id) {
        try {
            Blob image = imageService.getImageById(id).getImage();
            return image.getBytes(1, (int) image.length());
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    @GetMapping("/calc/{id}")
    public String calcHistogram(@PathVariable int id, Model model) {
        Blob image;
        int[][] histogram;
        try {
            image = imageService.getImageById(id).getImage();
            histogram = new ImageProcessor().getHistogram(image.getBytes(1, (int) image.length()));
            model.addAttribute("histogram", histogram);
            renderHomePage(model);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            renderHomePage(model, "Image with id=" + id + " not found!");
        } catch (Exception e) {
            e.printStackTrace();
            renderHomePage(model, "Internal server error occurred.");
        }

        return "home-page";
    }

    @DeleteMapping("/images/{id}")
    public String deleteImage(@PathVariable int id, Model model) {
        imageService.delete(id);
        renderHomePage(model);
        return "home-page";
    }

    private void renderHomePage(Model model) {
        int infosToDisplay = 15;
        List<ImageInfo> images = imageService.getLastUploadedInfosOnly(infosToDisplay);
        images.sort(Comparator.comparing(ImageInfo::getUploaded).reversed());
        model.addAttribute("uploadedImages", images);
    }

    private void renderHomePage(Model model, String message) {
        renderHomePage(model);
        model.addAttribute("message", message);
    }
}
