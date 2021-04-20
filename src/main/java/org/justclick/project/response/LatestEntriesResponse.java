package org.justclick.project.response;

import java.util.List;

import org.justclick.project.dto.LogRegistryDto;

public class LatestEntriesResponse {
    private List<LogRegistryDto> results;

    public LatestEntriesResponse(List<LogRegistryDto> results) {
        this.results = results;
    }

    public List<LogRegistryDto> getResults() {
        return results;
    }

    public void setResults(List<LogRegistryDto> results) {
        this.results = results;
    }
}
