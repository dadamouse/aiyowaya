package com.ai.hackathon.aiyowaya.service.clova;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVariables {

    @JsonProperty("User variable name")
    UserVariablesName userVariablesName;
}
