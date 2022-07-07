package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;


class GUITest {
    @Test
    void testInitial() {
        assert(GUI.array[0][0] == "1");
        assert(GUI.array[0][1] == "2");
        assert(GUI.array[0][2] == "3");
        assert(GUI.array[1][0] == "4");
        assert(GUI.array[1][1] == "5");
        assert(GUI.array[1][2] == "6");
        assert(GUI.array[2][0] == "7");
        assert(GUI.array[2][1] == "8");
        assert(GUI.array[2][2] == "9");
    }
}
