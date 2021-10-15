package com.ai.hackathon.aiyowaya.service.clova;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVariables {

    Map<String, UserVariablesName> userVariablesName = new LinkedHashMap<>();

    @JsonAnySetter
    void setVariable(String key, UserVariablesName value) {
        userVariablesName.put(key, value);
    }
}
