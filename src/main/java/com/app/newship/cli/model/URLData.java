package com.app.newship.cli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class URLData {
    private String UUID;

    private String url;

    private int score;


    @Override
    public String toString() {
        return new StringBuffer("")
                .append(url)
                .append(" (")
                .append(score)
                .append(")")
                .toString();
    }
}
