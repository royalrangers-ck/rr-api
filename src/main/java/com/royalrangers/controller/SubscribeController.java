package com.royalrangers.controller;

import com.royalrangers.model.Subscribe;
import com.royalrangers.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {
    @Autowired
    private SubscribeRepository subscribeRepository;

    @RequestMapping("/create")
    public Subscribe create(String email) {

        Subscribe subscribe = null;

            subscribe = new Subscribe(email);
            subscribeRepository.save(subscribe);

        return subscribe;
    }

}
