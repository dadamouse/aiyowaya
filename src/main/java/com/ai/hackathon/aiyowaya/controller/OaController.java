package com.ai.hackathon.aiyowaya.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.hackathon.aiyowaya.service.clova.ChatBotData;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotDto;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotRequest;
import com.ai.hackathon.aiyowaya.service.clova.UserVariableResponse;
import com.ai.hackathon.aiyowaya.service.clova.UserVariablesName;
import com.ai.hackathon.aiyowaya.service.oalist.OaListService;

@RestController
public class OaController {

    final private OaListService oaListService;

    public OaController(OaListService oaListService) {
        this.oaListService = oaListService;
    }

    /**
     * @Link https://guide.ncloud-docs.com/docs/en/chatbot-chatbot-3-4
     */
    @PostMapping("/issue")
    public ChatBotDto IssueOa(
            @RequestBody ChatBotRequest request) {

        System.out.println(request);

        return this.oaListService.findIntentOa(request);
    }
}
