package org.justclick.project.service;

import java.util.ArrayList;
import java.util.List;

import org.justclick.project.document.LogRegistry;
import org.justclick.project.dto.LogRegistryDto;
import org.justclick.project.repository.LogRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LogRegistryService {
    private LogRegistryRepository logRegistryRepository;

    @Autowired
    public LogRegistryService(LogRegistryRepository logRegistryRepository) {
        this.logRegistryRepository = logRegistryRepository;
    }
    
    public Iterable<LogRegistry> saveAll(Iterable<LogRegistry> registries){
        return this.logRegistryRepository.saveAll(registries);
    }

    public List<LogRegistryDto> getLatest() {
        List<LogRegistryDto> logRegistryDtos = new ArrayList<>();
        for (LogRegistry logRegistry : this.logRegistryRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"))) {
            logRegistryDtos.add(new LogRegistryDto(logRegistry));
        }
        return logRegistryDtos;
    }
}
