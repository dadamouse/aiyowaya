package com.ai.hackathon.aiyowaya.service.clova;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBotRequest {

    ActionMethod actionMethod;

    UserInfo userInfo;
}
