package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;


class CLITest {
    @Test
    void testInitial() {
      assert(CLI.array[0][0] == "1");
      assert(CLI.array[0][1] == "2");
      assert(CLI.array[0][2] == "3");
      assert(CLI.array[1][0] == "4");
      assert(CLI.array[1][1] == "5");
      assert(CLI.array[1][2] == "6");
      assert(CLI.array[2][0] == "7");
      assert(CLI.array[2][1] == "8");
      assert(CLI.array[2][2] == "9");
    }
}
