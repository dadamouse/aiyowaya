package com.ai.hackathon.aiyowaya.service.oalist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

        final List<OaListEntity> oaListEntities = oaListRepository.findAllByIntention("銀行 - 換匯資訊");

        Collections.shuffle(oaListEntities);

        int i = 1;
        List<ChatBotData> chatBotDataList = new ArrayList<>();
        for (OaListEntity entity : oaListEntities) {
            if (i > 3) {
                break;
            }

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("OA.id" + i)
                               .value("https://line.me/R/ti/p/" + entity.getOaId())
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

            String titleImage = "";
            if (entity.getTitle().equals("ico_premium")) {
                titleImage = "https://clovachatbot.ncloud.com/i35b518732a5bd-a6b2-4eec-8bb3-f5617f0addf6";
            } else if (entity.getTitle().equals("ico_certified")) {
                titleImage = "https://clovachatbot.ncloud.com/i659518339d685-8670-40a8-8c80-b8cd15263b81";
            }

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("title" + i)
                               .value(titleImage)
                               .build()
            );

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("bg" + i)
                               .value(entity.getBg())
                               .build()
            );
            i++;
        }

        String definedUserName;
        String definedUserValue;
        String definedUserType;
        final Map<String, UserVariablesName> userVariablesName =
                request.getUserInfo().getUserVariables().getUserVariablesName();

        List<UserVariableResponse> userVariable = new ArrayList();

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

            userVariable = List.of(UserVariableResponse.builder()
                                                       .name(definedUserName)
                                                       .value(definedUserValue)
                                                       .type(definedUserType)
                                                       .action("Specifies action")
                                                       .valueType("TEXT")
                                                       .build());
        }

        return ChatBotDto.builder()
                         .data(chatBotDataList)
                         .build();
    }
}
