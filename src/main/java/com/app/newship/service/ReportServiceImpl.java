package com.app.newship.service;

import com.app.newship.cli.model.URLData;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    static class ReportRecord {
        String domain;

        int count;

        int sumScore;

        public ReportRecord(String domain) {
            this.domain = domain;
            this.count = 0;
            this.sumScore = 0;
        }

        @Override
        public String toString() {
            return String.format("%s;%s;%s", domain,
                    Integer.toString(count),
                    Integer.toString(sumScore));
        }
    }
    @Override
    public List<String> generateReport(List<URLData> data) {

        Map<String, ReportRecord> mapDomainNameToRecord = new HashMap<>();

        for(URLData urlData : data) {
            URI uri = URI.create(urlData.getUrl());
            String domain = uri.getHost();

            if(Optional.ofNullable(domain).isEmpty())
                continue;

            if(!mapDomainNameToRecord.containsKey(domain))
                mapDomainNameToRecord.put(domain, new ReportRecord(domain));

            ReportRecord reportRecord = mapDomainNameToRecord.get(domain);
            reportRecord.count = reportRecord.count + 1;
            reportRecord.sumScore = reportRecord.sumScore + urlData.getScore();
        }

        return mapDomainNameToRecord.values().stream()
                .map(reportRecord -> reportRecord.toString())
                .collect(Collectors.toList());
    }
}
