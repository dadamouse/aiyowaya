package com.ai.hackathon.aiyowaya.service.clova;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionVariableName {
    List<String> args;

    String variableName;
}
