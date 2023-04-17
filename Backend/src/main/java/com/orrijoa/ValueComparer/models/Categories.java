package com.orrijoa.ValueComparer.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Categories {

    private Map<String, List<String>> categories;
}
