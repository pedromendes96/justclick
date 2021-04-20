package org.justclick.project.repository;

import org.justclick.project.document.LogRegistry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRegistryRepository  extends ElasticsearchRepository<LogRegistry, String> {}
