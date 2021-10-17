package com.ai.hackathon.aiyowaya.service.oalist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.ai.hackathon.aiyowaya.Repository.OaListRepository;
import com.ai.hackathon.aiyowaya.model.OaListEntity;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotData;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotDto;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotRequest;
import com.ai.hackathon.aiyowaya.service.clova.UserVariableResponse;
import com.ai.hackathon.aiyowaya.service.clova.UserVariablesName;

@Service
public class OaListService {

    private final OaListRepository oaListRepository;

    public OaListService(OaListRepository oaListRepository) {
        this.oaListRepository = oaListRepository;
    }

    public ChatBotDto findIntentOa(ChatBotRequest request) {

        final List<OaListEntity> oaListEntities = oaListRepository.findAllByIntention(
                request.getActionMethod().getName());

        int i = 1;
        List<ChatBotData> chatBotDataList = new ArrayList<>();
        for (OaListEntity entity : oaListEntities) {
            if (i > 3) {
                break;
            }

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("oa_id" + i)
                               .value(entity.getOaId())
                               .build()
            );
            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("icon" + i)
                               .value(entity.getIcon())
                               .build()
            );
            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("name" + i)
                               .value(entity.getName())
                               .build()
            );
            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("friends" + i)
                               .value(entity.getFriends().toString())
                               .build()
            );
            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("title" + i)
                               .value(entity.getTitle())
                               .build()
            );
            i++;
        }

        String definedUserName;
        String definedUserValue;
        String definedUserType;
        final Map<String, UserVariablesName> userVariablesName =
                request.getUserInfo().getUserVariables().getUserVariablesName();

        if (userVariablesName.containsKey("intent")) {
            if (userVariablesName.get("intent").getValue().equals("bank")) {
                definedUserName = "intent";
                definedUserValue = "bank";
                definedUserType = userVariablesName.get(definedUserName).getTyp();
            } else {
                definedUserName = "沒有對應的名字";
                definedUserValue = "沒有對應的值";
                definedUserType = "沒有對應的型態";
            }
        } else {
            definedUserName = "沒有對應的名字";
            definedUserValue = "沒有對應的值";
            definedUserType = "沒有對應的型態";
        }

        return ChatBotDto.builder()
                         .data(chatBotDataList)
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
