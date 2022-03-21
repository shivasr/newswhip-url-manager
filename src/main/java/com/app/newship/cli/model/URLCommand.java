package com.app.newship.cli.model;

import com.app.newship.service.OutputService;
import com.app.newship.service.ReportService;
import com.app.newship.service.URLService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * URLCommand to handle shell commands to add, delete, list and generate report for URL data.
 */
@ShellComponent
public class URLCommand {
    Logger log = Logger.getLogger(URLCommand.class.getName());

    @Value("${file.export.name}")
    private String filename;

    private final URLService urlService;

    private final OutputService outputService;

    private final ReportService reportService;



    public URLCommand(URLService urlService, OutputService outputService, ReportService reportService) {
        this.urlService = urlService;
        this.outputService = outputService;
        this.reportService = reportService;
    }


    /**
     * Command to add URL into the database.
     * @param url URL string value
     * @param score score of the URL
     */
    @ShellMethod(value = "Add new URL and its score. " +
            "\nUsage: add --url <URL> --score <score>")
    public void add(@ShellOption String url,
                    @ShellOption String score)
    {
        System.out.println(String.format("Adding the URL: %s with score: %s", url, score));

        int scoreValue = -1;

        try {
            scoreValue = Integer.parseInt(score);
        } catch (NumberFormatException nex) {
            System.out.println("Score with invalid format please check again.");
            return;
        }

        URLData urlData = urlService.addNewURL(url, scoreValue);
        System.out.println("Done");
    }

    /**
     * Command to delete URL from the database.
     * @param url URL string value
     */
    @ShellMethod(value = "Delete an URL and its score. " +
            "\nUsage: delete --url <URL>")
    public void delete(@ShellOption String url)
    {
        System.out.println(String.format("Delete the URL: %s.", url));
        if(!urlService.removeURL(url)) {
            System.err.println(String.format("Unable to delete the URL: %s.", url));
        }
        System.out.println("Done.");
    }

    /**
     * Display all existing URLs
     */
    @ShellMethod(value = "Lists all URLs with their scores." +
            "\nUsage: list")
    public void list()
    {
        System.out.println("Listing all urls.");

        List<URLData> urls = urlService.listAllURLs();

        outputService.outputToConsole(urls.stream()
                .map(urlData -> urlData.toString())
                .collect(Collectors.toList())
        );
    }

    /**
     * Generate report of statistics containing domain, count and sum of scores
     *
     * @param file path to the file where the statistics has to be written,
     */
    @ShellMethod(value = "Exports all URLs to a file (configurable)" +
            "\nUsage: export [--file <filename>]")
    public void export(@ShellOption(defaultValue = "NA") String file)
    {
        if("NA".equalsIgnoreCase(file))
           file = filename;

        System.out.println(String.format(String.format("Exporting to the file: %s", file)));

        List<URLData> urls = urlService.listAllURLs();

        List<String> statistics = reportService.generateReport(urls);

        try{
            outputService.outputToFile(file, statistics);
        } catch (Exception e) {
            System.err.println("Unable to generate report. Error: " + e.getMessage());
        }

        System.out.println("Done.");

    }
}
