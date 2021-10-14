package com.ai.hackathon.aiyowaya.service.clova;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    String id;
    String key;
    String query;
    Entities entities;
    TaskEntities taskEntities;
    UserVariables userVariables;
}
