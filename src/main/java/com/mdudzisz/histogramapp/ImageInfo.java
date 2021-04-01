package com.mdudzisz.histogramapp;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
@Component
@Entity(name = "images")
public class ImageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Blob image;
    private String author;
    private Timestamp uploaded;
    private String filename;
    private String description;

    public static ImageInfo fromImageInfoOnly(ImageInfoOnly info) {
        ImageInfo image = new ImageInfo();
        image.setId(info.getId());
        image.setAuthor(info.getAuthor());
        image.setUploaded(info.getUploaded());
        image.setFilename(info.getFilename());
        image.setDescription(info.getDescription());
        return image;
    }

    public boolean compareNewestFirst(ImageInfo other) {
        return this.getUploaded().getTime() > other.getUploaded().getTime();
    }

    public static ImageInfo fromFormParams(MultipartFile file, String author, String description)
            throws IOException, SQLException {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setFilename(file.getOriginalFilename());
        imageInfo.setImage(new SerialBlob(file.getBytes()));
        imageInfo.setAuthor(author);
        imageInfo.setDescription(description);
        return imageInfo;
    }

    public interface ImageInfoOnly {
        Integer getId();

        String getAuthor();

        Timestamp getUploaded();

        String getFilename();

        String getDescription();

    }
}
