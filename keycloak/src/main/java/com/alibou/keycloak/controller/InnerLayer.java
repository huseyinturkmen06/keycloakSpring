package com.alibou.keycloak.controller;


import com.alibou.keycloak.dto.BelgenetDto;
import com.alibou.keycloak.service.BelgenetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/innerLayer")
@CrossOrigin
public class InnerLayer {

    private BelgenetRequest belgenetRequest;

    @Autowired
    public InnerLayer(BelgenetRequest belgenetRequest){
        this.belgenetRequest=belgenetRequest;
    }


    @PostMapping("/getTokenFromBelgenet")
    public String requestToBelgenet(@RequestBody BelgenetDto belgenetDto) throws IOException {
        return belgenetRequest.requestToBelgenet(belgenetDto);
    }

}
