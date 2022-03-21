package com.app.newship.utils;

import com.app.newship.cli.model.URLData;
import com.app.newship.entities.URLEntity;

import java.util.UUID;

/**
 * Mappers to convert from Entity to DTO and vice versa.
 */
public class URLDataMapper {

    /**
     * Method to convert urlEntity to DTO urlData.
     *
     * @param urlEntity database record to be converted
     *
     * @return the converted DTO urlData
     */
    public static URLData toURLData(URLEntity urlEntity) {
        return URLData.builder()
                .UUID(urlEntity.getUuid().toString())
                .url(urlEntity.getURL())
                .score(urlEntity.getScore())
                .build();
    }


    /**
     * Method to convert DTO  to url db reocrd.
     *
     * @param urlData DTO from the command line
     * @return database record that can be saved.
     */
    public static URLEntity toEntity(URLData urlData) {
        return URLEntity.builder()
                .uuid(UUID.fromString(urlData.getUUID()))
                .URL(urlData.getUrl())
                .score(urlData.getScore())
                .build();
    }

}
