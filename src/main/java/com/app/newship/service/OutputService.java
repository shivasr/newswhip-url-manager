package com.app.newship.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface OutputService {
    void outputToConsole(List<String> urlDataList);

    void outputToFile(String filename, List<String> urlDataList) throws IOException;
}
