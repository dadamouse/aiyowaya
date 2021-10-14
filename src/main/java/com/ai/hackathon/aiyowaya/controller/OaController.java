package com.ai.hackathon.aiyowaya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.hackathon.aiyowaya.service.clova.ChatBotData;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotDto;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotRequest;
import com.ai.hackathon.aiyowaya.service.clova.UserVariableResponse;

@RestController
public class OaController {

    /**
     * @Link https://guide.ncloud-docs.com/docs/en/chatbot-chatbot-3-4
     */
    @PostMapping("/issue")
    public ChatBotDto IssueOa(
            @RequestBody ChatBotRequest request) {

        System.out.println(request);

        return ChatBotDto.builder()
                .data(List.of(ChatBotData.builder()
                                         .variableName("Part that pertains to variable name")
                                         .value("Decides to which value the variable pertaining to variableName will be substituted")
                                         .build()))
                  .userVariable(
                          List.of(UserVariableResponse.builder()
                                                      .name("User variable name")
                                                      .value("Value to be saved in user variables")
                                                      .type("Type of user variable")
                                                      .action("Specifies action")
                                                      .valueType("Type of value to be saved")
                                                      .build()))
                         .build();
    }
}
