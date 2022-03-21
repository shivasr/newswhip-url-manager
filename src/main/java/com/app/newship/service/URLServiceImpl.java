package com.app.newship.service;

import com.app.newship.cli.model.URLData;
import com.app.newship.entities.URLEntity;
import com.app.newship.repositories.URLRepository;
import com.app.newship.utils.URLDataMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class URLServiceImpl implements URLService {

    private final URLRepository urlRepository;

    public URLServiceImpl(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public List<URLData> listAllURLs() {
        return urlRepository.findAll().stream()
                .map(URLDataMapper::toURLData)
                .collect(Collectors.toList());
    }

    @Override
    public URLData addNewURL(String URL, int score) {
        URLEntity urlEntity =  urlRepository.save(URLEntity.builder()
                        .URL(URL)
                        .score(score)
                .build());

        return URLDataMapper.toURLData(urlEntity);
    }

    @Override
    public boolean removeURL(String url) {
        URLEntity entity = urlRepository.findByURL(url);

        if(Optional.ofNullable(entity).isPresent()) {
            urlRepository.deleteByUuid(entity.getUuid());
            return true;
        }
        return  false;
    }
}
