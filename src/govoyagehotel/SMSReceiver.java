/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.IOException;
import java.io.InterruptedIOException;
 
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;
 
public class SMSReceiver extends MIDlet implements CommandListener, MessageListener {
    Image img ;
    ImageItem imgt ;
	private Form formReceiver = new Form("SMS Receiver");
	private TextField tfPort = new TextField("Port", "5000", 6, TextField.NUMERIC);
	private Command cmdListen = new Command("Listen", Command.OK, 1);
	private Command cmdExit = new Command("Exit", Command.EXIT, 1);
	private Display display;
 
	public SMSReceiver() throws IOException { img =Image.createImage("/sms3.jpg");
        imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
        formReceiver.append(imgt);
            
		formReceiver.append(tfPort);
		formReceiver.addCommand(cmdListen);
		formReceiver.addCommand(cmdExit);
		formReceiver.setCommandListener(this);
 
		display = Display.getDisplay(this);
	}
 
	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
 
	}
 
	protected void pauseApp() {
 
	}
 
	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(formReceiver);
	}
 
	public void commandAction(Command c, Displayable d) {
		if (c==cmdListen) {
			ListenSMS sms = new ListenSMS(tfPort.getString(), this);
			sms.start();
			formReceiver.removeCommand(cmdListen);
		} else if (c==cmdExit) {
			notifyDestroyed();
		}
	}
 
	public void notifyIncomingMessage(MessageConnection conn) {
		Message message;
		try {
			message = conn.receive();
			if (message instanceof TextMessage) {
				TextMessage tMessage = (TextMessage)message;
				formReceiver.append("Message received : "+tMessage.getPayloadText()+"n");
			} else {
				formReceiver.append("Unknown Message receivedn");
			}
		} catch (InterruptedIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}
 
class ListenSMS extends Thread {
	private MessageConnection msgConnection;
	private MessageListener listener;
	private String port;
 
	public ListenSMS(String port, MessageListener listener) {
		this.port = port;
		this.listener = listener;
	}
 
	public void run() {
		try {
			msgConnection = (MessageConnection)Connector.open("sms://:" + port);
			msgConnection.setMessageListener(listener);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}