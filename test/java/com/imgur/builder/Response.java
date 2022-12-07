package com.imgur.builder;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import lombok.Data;

import javax.sql.RowSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisle",
        "id",
        "name",
        "pantryItem",
        "cost",
        "ingredientId",
        "amount",
        "unit",
        "startDate",
        "endDate"
})
@Data
public class Response {

    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pantryItem")
    private Boolean pantryItem;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("ingredientId")
    private Integer ingredientId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("startDate")
    private Integer startDate;
    @JsonProperty("endDate")
    private Integer endDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


