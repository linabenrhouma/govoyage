/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.IOException;
 
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
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;
 
public class SMSSender extends MIDlet implements CommandListener {
   Image img ;
    ImageItem imgt ;
	private Form formSender = new Form("SMS Sender");
	private TextField tfDestination = new TextField("Destination", "", 20, TextField.PHONENUMBER);
	private TextField tfPort = new TextField("Port", "5000", 6, TextField.NUMERIC);
	private TextField tfMessage = new TextField("Message", "message", 150, TextField.ANY);
	private Command cmdSend = new Command("Send", Command.OK, 1);
	private Command cmdExit = new Command("Exit", Command.EXIT, 1);
	private Display display;
 
	public SMSSender() throws IOException {
            img =Image.createImage("/sms1.png");
        imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
        formSender.append(imgt);
            
		formSender.append(tfDestination);
		formSender.append(tfPort);
		formSender.append(tfMessage);
		formSender.addCommand(cmdSend);
		formSender.addCommand(cmdExit);
		formSender.setCommandListener(this);
 
		display = Display.getDisplay(this);
	}
 
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}
 
	protected void pauseApp() {
 
	}
 
	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(formSender);
	}
 
	public void commandAction(Command c, Displayable d) {
		if (c==cmdSend) {
			SendMessage.execute(tfDestination.getString(), tfPort.getString(), tfMessage.getString());
		} else if (c==cmdExit) {
			notifyDestroyed();
		}
	}
 
}
 
class SendMessage {
 
	public static void execute(final String destination, final String port, final String message) {
 
		Thread thread = new Thread(new Runnable() {
 
			public void run() {
				MessageConnection msgConnection;
				try {
					msgConnection = (MessageConnection)Connector.open("sms://"+destination+":" + port);
					TextMessage textMessage = (TextMessage)msgConnection.newMessage(
							MessageConnection.TEXT_MESSAGE);
					textMessage.setPayloadText(message);
					msgConnection.send(textMessage);
					msgConnection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
 
		thread.start();
	}
 
}
