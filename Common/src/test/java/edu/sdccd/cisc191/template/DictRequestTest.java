package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DictRequestTest {
    private DictRequest dictRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dictRequest = new DictRequest("insert", "hi");
    }

    @org.junit.jupiter.api.Test
    void getRequestAndPayload() {
        assertEquals(dictRequest.getRequest(), "insert");
        assertEquals(dictRequest.getPayload(), "hi");
    }

    @org.junit.jupiter.api.Test
    void setRequestAndPayload() {
        dictRequest.setRequest("remove");
        dictRequest.setPayload("no");
        assertEquals(dictRequest.getRequest(), "remove");
        assertEquals(dictRequest.getPayload(), "no");
    }
}
