package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.*;

class DictResponseTest {
    private DictResponse dictResponse;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dictResponse = new DictResponse("Found");
    }

    @org.junit.jupiter.api.Test
    void getResponse() {
        assertEquals(dictResponse.getResponse(), "Found");
    }

    @org.junit.jupiter.api.Test
    void setResponse() {
        dictResponse.setResponse("Not Found");
        assertEquals(dictResponse.getResponse(), "Not Found");
    }
}
