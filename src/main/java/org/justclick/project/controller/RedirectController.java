package org.justclick.project.controller;

import org.justclick.project.exception.KeyNotFoundException;
import org.justclick.project.model.Redirect;
import org.justclick.project.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RedirectController {
    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping(value="/link/{key}")
    public ModelAndView getRedirectKey(@PathVariable String key) {
        Redirect redirect = this.redirectService.getValidKey(key);
        if(redirect != null){
            return new ModelAndView("redirect:" + redirect.redirectTo);
        }else{
            throw new KeyNotFoundException();
        }
    }
    
}
