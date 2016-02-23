package tanknetbeansproject.clientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import tanknetbeansproject.Game.GameEngine;

import tanknetbeansproject.view.ClientUI;
import tanknetbeansproject.view.ClientUI;

public class ClientServer extends Thread {

    // Start Server in Client Side and Receive Messages.
    private Socket socketInput = null;
    private ServerSocket serverSocket = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader input = null;
    private ServerConnector serverConnector = null;

    // Map Variable
    private GameEngine gameEngine = new GameEngine();

    public ClientServer(ServerConnector serverConnector) {
        try {
            serverSocket = new ServerSocket(7000);
            gameEngine = new GameEngine();
            this.serverConnector = serverConnector;
        } catch (IOException e) {
            System.out.println("Client Server Not Started. " + e.getMessage());

        }
    }

    public void run() {
        serverConnector.sendMessage("JOIN#");
        //new MainFrame().setVisible(true);
        ClientUI clientUI = new ClientUI();
        clientUI.setVisible(true);

        gameEngine.setClientUI(clientUI);
        while (true) {
            try {
                socketInput = serverSocket.accept();
                inputStream = socketInput.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                input = new BufferedReader(inputStreamReader);
                String update = input.readLine();
//                System.out.println(update);

                gameEngine.updateMap(update);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
