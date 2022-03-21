package com.app.newship.service;

import com.app.newship.cli.model.URLData;

import java.util.List;

public interface ReportService {
    List<String> generateReport(List<URLData> data);
}
