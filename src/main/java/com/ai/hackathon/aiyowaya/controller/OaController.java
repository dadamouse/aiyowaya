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

@RestController
public class OaController {

    /**
     * @Link https://guide.ncloud-docs.com/docs/en/chatbot-chatbot-3-4
     */
    @PostMapping("/issue")
    public ChatBotDto IssueOa(
            @RequestBody ChatBotRequest request) {

        System.out.println(request);

        final List<ChatBotData> chatBotData = request.getActionMethod()
                                                     .getMethods()
                                                     .stream()
                                                     .map(method -> ChatBotData.builder()
                                                                               .variableName(method.getVariableName())
                                                                               .value(method.getVariableName() + "value")
                                                                               .build())
                                                     .collect(Collectors.toList());

        String definedUserName;
        String definedUserValue;
        String definedUserType;
        final Map<String, UserVariablesName> userVariablesName =
                request.getUserInfo().getUserVariables().getUserVariablesName();
        if (userVariablesName.containsKey("bank")) {
            definedUserName = "bank";
            definedUserValue = userVariablesName.get(definedUserName).getValue();
            definedUserType = userVariablesName.get(definedUserName).getTyp();
        } else {
            definedUserName = "沒有對應的名字";
            definedUserValue = "沒有對應的值";
            definedUserType = "沒有對應的型態";
        }

        return ChatBotDto.builder()
                         .data(chatBotData)
                         .userVariable(
                                 List.of(UserVariableResponse.builder()
                                                             .name(definedUserName)
                                                             .value(definedUserValue)
                                                             .type(definedUserType)
                                                             .action("Specifies action")
                                                             .valueType("TEXT")
                                                             .build()))
                         .build();
    }
}
