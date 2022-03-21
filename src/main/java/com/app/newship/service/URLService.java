package com.app.newship.service;

import com.app.newship.cli.model.URLData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface URLService {
    public List<URLData> listAllURLs();

    public URLData addNewURL(String URL, int score);

    public boolean removeURL(String url);
}
