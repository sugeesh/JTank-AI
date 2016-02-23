package tanknetbeansproject.GUIController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tanknetbeansproject.clientConnection.ServerConnector;

public class KeyController implements KeyListener {

    ServerConnector serverConnector = null;

    public KeyController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // TODO Auto-generated method stub

        switch (keyCode) {

            case KeyEvent.VK_UP:
                serverConnector.sendMessage("UP#");
                break;

            case KeyEvent.VK_DOWN:
                serverConnector.sendMessage("DOWN#");
                break;

            case KeyEvent.VK_LEFT:
                serverConnector.sendMessage("LEFT#");
                break;

            case KeyEvent.VK_RIGHT:
                serverConnector.sendMessage("RIGHT#");
                break;

            case KeyEvent.VK_SPACE:
                serverConnector.sendMessage("SHOOT#");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
