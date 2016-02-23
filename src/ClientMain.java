import tanknetbeansproject.clientConnection.ClientServer;
import tanknetbeansproject.clientConnection.ServerConnector;




public class ClientMain {
	public static void main(String args[]){
            ServerConnector serverConnector = new ServerConnector();
            ClientServer clientServer = new ClientServer(serverConnector);
            clientServer.start();
        }
}
