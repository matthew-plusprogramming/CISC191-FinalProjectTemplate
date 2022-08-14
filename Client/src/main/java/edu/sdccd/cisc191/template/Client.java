package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public DictResponse sendRequest(String request, String payload) throws Exception {
        out.println(DictRequest.toJSON(new DictRequest(request, payload)));
        return DictResponse.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            Scanner scanner = new Scanner(System.in);
            String input;
            do {
                String request = "";
                String payload = "";

                System.out.println(
                        "Choose from the following:\n(e)xit\n(i)nsert\n(r)emove\n(s)earch");
                input = scanner.nextLine().trim().toLowerCase();
                switch (input) {
                    case "i":
                        request = "insert";
                        break;
                    case "r":
                        request = "remove";
                        break;
                    case "s":
                        request = "search";
                        break;
                    default:
                        break;
                }
                if (!input.equals("e")) {
                    System.out.println("What would you like to " + request + "?");
                    input = scanner.nextLine().trim().toLowerCase();
                    payload = input;

                    System.out.println("\n" + client.sendRequest(request, payload).toString());
                    System.out.println("Press enter to continue...");
                    System.in.read();
                }
            } while(!input.equals("e"));

            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Client

