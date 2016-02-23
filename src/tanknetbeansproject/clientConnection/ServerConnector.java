package tanknetbeansproject.clientConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnector {
	// Connect to the Server and Send Messages.
	Socket socketOutput = null;
	DataOutputStream dataOutputStream = null;

	
	public void connectToServer() {
		try {
			socketOutput = new Socket("127.0.0.1", 6000);
			dataOutputStream = new DataOutputStream(
					socketOutput.getOutputStream());
			//System.out.println("Connected to the server.");
		} catch (IOException e) {
			System.out.println("Not Conneted to the Server. " + e.getMessage());
		}
		

	}

	public void sendMessage(String message) {
		connectToServer();
		try {
			dataOutputStream.writeBytes(message);
			dataOutputStream.flush();
			dataOutputStream.close();
			//socketOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
