package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class RequestConsumer implements Runnable {
        BlockingQueue<Socket> requestQueue = null;
    Socket clientSocket = null;
    boolean stopThread = false;
    private PrintWriter out;
    private BufferedReader in;
    private TrieNode trieNode;

    public RequestConsumer(BlockingQueue<Socket> queue, TrieNode trieNode) {
        this.requestQueue = queue;
        this.trieNode = trieNode;
    }

    @Override
    public void run() {
        while (!stopThread) {
            try {
                // Handle incoming socket request
                clientSocket = this.requestQueue.take();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                // Use trie data structure to perform operation accordingly
                while ((inputLine = in.readLine()) != null) {
                    DictRequest request = DictRequest.fromJSON(inputLine);
                    String response = "";
                    if (request.getRequest().equals("insert")) {
                        trieNode.insert(request.getPayload());
                        response = "Insert successful";
                    }
                    else if (request.getRequest().equals("remove")) {
                        trieNode.remove(request.getPayload());
                        response = "Remove successful";
                    }
                    else if (request.getRequest().equals("search")) {
                        response = trieNode.search(request.getPayload()) ? "Found" : "Not Found";
                    }
                    DictResponse dictResponse = new DictResponse(response);
                    out.println(DictResponse.toJSON(dictResponse));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() throws IOException {
        stopThread = true;
        in.close();
        out.close();
    }
}
