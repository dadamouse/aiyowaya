package com.ai.hackathon.aiyowaya.service.clova;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatBotData {
    String variableName;
    String value;
}
