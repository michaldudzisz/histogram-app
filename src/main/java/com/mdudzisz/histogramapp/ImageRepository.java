package com.mdudzisz.histogramapp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ImageRepository extends CrudRepository<ImageInfo, Integer> {

    String FIND_INFO_ONLY = "SELECT "
            + "i.id, "
            + "i.filename, "
            + "i.author, "
            + "i.uploaded, "
            + "i.description "
            + "FROM images i";

    @Query(value = FIND_INFO_ONLY, nativeQuery = true)
    Page<ImageInfo.ImageInfoOnly> findLastUploadedInfosOnly(Pageable pageable);

}
