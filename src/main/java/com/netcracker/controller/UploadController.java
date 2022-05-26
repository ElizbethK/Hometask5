package com.netcracker.controller;

import com.netcracker.util.CSVHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {

    private static final String UPLOAD_DIR = "./";
    private static final String CSV_FILE_NAME = "csv.csv";

    @GetMapping("/uploadperson")
    public String homepage() {
        return "uploadperson";
    }

    @PostMapping("/uploadperson")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/uploadperson";
        }

        // check if file extension is not csv
        if (!CSVHandler.getExtension(file.getOriginalFilename().toLowerCase()).equals("csv")) {
            attributes.addFlashAttribute("message", "File's extension must be a csv. Current extension is: " + CSVHandler.getExtension(file.getOriginalFilename().toLowerCase()) + ".");
            return "redirect:/uploadperson";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + CSV_FILE_NAME);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "Successfully uploaded " + fileName + '!');

        return "redirect:/uploadperson";
    }
}
