package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DictResponse {
    private String response;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(DictResponse customer) throws Exception {
        return objectMapper.writeValueAsString(customer);
    }
    public static DictResponse fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, DictResponse.class);
    }
    protected DictResponse() {}

    public DictResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return String.format(
                "DictResponse[response='%s']",
                response);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}