package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DictRequest {
    private String request;
    private String payload;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(DictRequest customer) throws Exception {
        return objectMapper.writeValueAsString(customer);
    }
    public static DictRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, DictRequest.class);
    }
    protected DictRequest() {}

    public DictRequest(String request, String payload) {
        this.request = request;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return String.format(
                "DictRequest[request='%s', payload='%s']",
                request,
                payload);
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}