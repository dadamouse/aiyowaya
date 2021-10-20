package com.ai.hackathon.aiyowaya.service.oalist;

import org.springframework.context.annotation.Bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OaDto {
    public String icon;
    public String name;
    public Long friends;
    public String title;

}
