package com.ai.hackathon.aiyowaya.service.clova;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVariableResponse {
    String name;
    String value;
    String type;
    String action;
    String valueType;
}
