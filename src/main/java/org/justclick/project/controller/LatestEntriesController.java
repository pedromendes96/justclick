package org.justclick.project.controller;

import org.justclick.project.response.LatestEntriesResponse;
import org.justclick.project.service.LogRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LatestEntriesController {
    private LogRegistryService logRegistryService;

    @Autowired
    public LatestEntriesController(LogRegistryService logRegistryService) {
        this.logRegistryService = logRegistryService;
    }

    @GetMapping(value="/latestEntries/", consumes=MediaType.ALL_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LatestEntriesResponse> latestEntries() {
        return ResponseEntity.ok(new LatestEntriesResponse(logRegistryService.getLatest()));
    }
}
