package com.mdudzisz.histogramapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<ImageInfo> getLastUploadedInfosOnly(int infosToDisplay) {
        List<ImageInfo> images = new ArrayList<>();
        imageRepository.findLastUploadedInfosOnly(PageRequest.of(0, infosToDisplay))
                .getContent().forEach(imageInfo -> {
            images.add(ImageInfo.fromImageInfoOnly(imageInfo));
        });

        return images;
    }

    public ImageInfo getImageById(int id) throws EmptyResultDataAccessException {
        Optional<ImageInfo> queryResult = imageRepository.findById(id);
        if (queryResult.isEmpty())
            throw new EmptyResultDataAccessException(1);
        return queryResult.get();
    }

    public void save(ImageInfo image) {
        image.setUploaded(new Timestamp(roundMilsToSeconds(System.currentTimeMillis())));
        imageRepository.save(image);
    }

    public void saveFromFormParams(MultipartFile file, String author, String description)
            throws IOException, SQLException {
        ImageInfo image = ImageInfo.fromFormParams(file, author, description);
        imageRepository.save(image);
    }

    public void delete(int id) {
        imageRepository.deleteById(id);
    }

    private long roundMilsToSeconds(long milis) {
        return (milis / (1000 * 60)) * 1000 * 60;
    }
}
