package com.app.newship.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OutputServiceImpl implements OutputService {
    @Override
    public void outputToConsole(List<String> urls) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        urls.forEach(url -> {
            System.out.println(atomicInteger.addAndGet(1) + ". " + url);
        });
    }

    @Override
    public void outputToFile(String filename, List<String> urlDataList) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {

            urlDataList.stream().forEach(record -> {
                writer.println(record);
            });
        }

    }
}
