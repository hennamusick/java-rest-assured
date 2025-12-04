package com.api.automation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * POJO for Object entity from restful-api.dev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiObject {
    private String id;
    private String name;
    private Map<String, Object> data;
}
