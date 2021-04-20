package org.justclick.project.repository;

import org.justclick.project.model.Redirect;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RedirectRepository extends CrudRepository<Redirect, Long>{
    Redirect findByKeyAndCounterGreaterThan(String key, Integer counter);

    @Modifying
    @Transactional
    @Query("update Redirect redirect set counter=(redirect.counter - 1) where key = ?1 and counter >= 0")
    void decreaseCounter(String key);
}
