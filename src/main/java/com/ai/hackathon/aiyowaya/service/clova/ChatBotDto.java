package com.ai.hackathon.aiyowaya.service.clova;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatBotDto {
    List<ChatBotData> data;

    List<UserVariableResponse> userVariable;
}
