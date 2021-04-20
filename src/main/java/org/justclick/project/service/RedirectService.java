package org.justclick.project.service;

import org.justclick.project.model.Redirect;
import org.justclick.project.repository.RedirectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }
    
    public Redirect getValidKey(String key){
        Redirect redirect = this.redirectRepository.findByKeyAndCounterGreaterThan(key, Integer.valueOf(0));
        if(redirect != null){
            this.redirectRepository.decreaseCounter(redirect.key);
        }
        return redirect;
    }
}
