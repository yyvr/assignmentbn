package com.basen.demoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Entity {
    String ID;
    Set<? extends BaseEntity> subEntities;
    Map<String,String> data;
}
