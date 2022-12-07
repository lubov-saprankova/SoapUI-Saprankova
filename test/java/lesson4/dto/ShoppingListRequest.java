package lesson4.dto;


import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
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
public class ShoppingListRequest {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "Bakings"
    })

    @Data
    private static class Aisle {
        @JsonProperty("Bakings")
        private String aisle;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"115388"})
    @Data
    private static class Id {
        @JsonProperty("115388")
        private String id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"baking powder"})
        @Data
        private static class Name {
            @JsonProperty("baking powder")
            private String name;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"false"})
        @Data
        private static class PantryItem {
            @JsonProperty("false")
            private String pantryItem;
        }


        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"0.71"})
        @Data
        private static class Cost {
            @JsonProperty("0.71")
            private String cost;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"18369"})
        @Data
        private static class IngredientId {
            @JsonProperty("18369")
            private String ingredientId;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"1.0"})
        @Data
        private static class Amount {
            @JsonProperty("1.0")
            private String amount;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"1.0"})
        @Data
        private static class Unit {
            @JsonProperty("1.0")
            private String unit;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"1588291200"})
        @Data
        private static class StartData {
            @JsonProperty("1588291200")
            private String startData;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({"1588896000"})
        @Data
        private static class EndData {
            @JsonProperty("1588896000")
            private String endData;
        }
    }
}

