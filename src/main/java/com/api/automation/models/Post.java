package com.api.automation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO for Post entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
