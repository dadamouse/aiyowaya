package com.ai.hackathon.aiyowaya.service.oalist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.hackathon.aiyowaya.Repository.OaListRepository;
import com.ai.hackathon.aiyowaya.model.OaListEntity;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotData;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotDto;
import com.ai.hackathon.aiyowaya.service.clova.ChatBotRequest;
import com.ai.hackathon.aiyowaya.service.clova.UserVariableResponse;

@Service
public class OaListService {

    private final OaListRepository oaListRepository;

    public OaListService(OaListRepository oaListRepository) {
        this.oaListRepository = oaListRepository;
    }

    public ChatBotDto findIntentOa(ChatBotRequest request) throws Exception {

        final String chooseName;
        if (null == request.getActionMethod().getName() || request.getActionMethod().getName().isEmpty()) {
            chooseName = "bank";
        } else {
            chooseName = request.getActionMethod().getName();
        }

        final String nameType;
        List<String> defaultImages = new ArrayList<>();
        final String adImage;
        switch (chooseName) {
            case "bank":
            default:
                nameType = "銀行 - 換匯資訊";
                defaultImages.add("https://clovachatbot.ncloud.com/ic5a568934x4ed-01b2-4f11-82c2-eec3be6a95da");
                defaultImages.add("https://clovachatbot.ncloud.com/i554558f3dlced-d5c3-4e10-bfee-de6c194efbb7");
                defaultImages.add("https://clovachatbot.ncloud.com/i15b5c8b31b316-aa50-493b-8e68-1750c41b7b98");
                adImage = "https://clovachatbot.ncloud.com/i45d578f3dj58f-b66f-4b7a-bc39-be3b71438269";
                break;
            case "gym":
                nameType = "預約健身課程";
                defaultImages.add("https://clovachatbot.ncloud.com/if595c8c3adbe4-05ce-47ae-9f71-e1c84be00db1");
                defaultImages.add("https://clovachatbot.ncloud.com/ic505b813cf2cd-be8b-401f-8bfc-9619adf9ccd1");
                defaultImages.add("https://clovachatbot.ncloud.com/ia5e588738a034-ef8a-4736-ada9-dd0bb3fdb95a");
                adImage = "https://clovachatbot.ncloud.com/i45e5c8f36ea2f-178a-4175-bbba-581a46bccc1e";
                break;
            case "food":
                nameType = "線上訂餐";
                defaultImages.add("https://clovachatbot.ncloud.com/i15e5d863ewa24-4a5a-468b-8f87-a113b0e44fc0");
                defaultImages.add("https://clovachatbot.ncloud.com/ic54598b3co2f8-d6eb-4786-a957-160e3cc0fae5");
                defaultImages.add("https://clovachatbot.ncloud.com/i255528938q84e-f28e-4256-b449-78b089640391");
                adImage = "https://clovachatbot.ncloud.com/id5e538739ga3f-a3c2-4970-b835-aacb2965348b";
                break;
            case "clinic":
                nameType = "診所 - 預約";
                defaultImages.add("https://clovachatbot.ncloud.com/id5f578134cae3-321a-4fd7-831e-bdcfa13be600");
                defaultImages.add("https://clovachatbot.ncloud.com/i056568d3fh9a5-ecf6-4c52-ac9c-b331c3841185");
                defaultImages.add("https://clovachatbot.ncloud.com/i956598c3cu17d-2b26-4767-8552-bc4f024ef24c");
                adImage = "https://clovachatbot.ncloud.com/i158578f39h97c-d0d1-406b-83e9-d0b38a68707f";
                break;
        }

        final List<OaListEntity> oaAdListEntities = oaListRepository.findAllByIntentionAndAd(nameType, 1L);
        final List<OaListEntity> oaListEntities = oaListRepository.findAllByIntentionAndAd(nameType, 0L);

        Collections.shuffle(oaAdListEntities);
        Collections.shuffle(oaListEntities);

        int i = 1;
        int max = 2;

        List<ChatBotData> chatBotDataList = new ArrayList<>();
        if (oaAdListEntities.isEmpty()) {
            max = 3;

            popOaEntity(oaListEntities, i, max, chatBotDataList, defaultImages, false);
        } else {
            popOaEntity(oaListEntities, i, max, chatBotDataList, defaultImages, false);

            i = 3;
            max = 3;
            popOaEntity(oaAdListEntities, i, max, chatBotDataList, List.of(adImage), true);
        }

        List<UserVariableResponse> userVariable = new ArrayList();

        return ChatBotDto.builder()
                         .data(chatBotDataList)
                         .userVariable(userVariable)
                         .build();
    }

    private void popOaEntity(List<OaListEntity> oaAdListEntities, int i, int max, List<ChatBotData> chatBotDataList, List<String> bgImages, boolean isAd) {

        for (OaListEntity entity : oaAdListEntities) {
            if (i > max) {
                break;
            }

            String image;
            if (isAd || entity.getBg().isEmpty()) {
                image = bgImages.get(0);
                bgImages.remove(0);
            } else {
                image = entity.getBg();
            }

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("bg" + i)
                               .value(image)
                               .build()
            );

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("icon" + i)
                               .value(entity.getIcon())
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
                               .variableName("name" + i)
                               .value(entity.getName())
                               .build()
            );

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("friend" + i)
                               .value(entity.getFriends().toString())
                               .build()
            );

            chatBotDataList.add(
                    ChatBotData.builder()
                               .variableName("id" + i)
                               .value("https://line.me/R/ti/p/" + entity.getOaId())
                               .build()
            );

            i++;
        }
    }
}
