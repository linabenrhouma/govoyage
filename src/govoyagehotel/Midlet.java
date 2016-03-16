/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author user
 */
public class Midlet extends MIDlet implements CommandListener{
    
    Ticker tk = new Ticker("GO VOYAGE ");
     public static Display display;
     public Form f=new Form("Menu Principal");
    public Display disp = Display.getDisplay(this);
    Command cmdNextAffich = new Command("Ajouter Reservation", Command.SCREEN, 1);
    Command cmdNextAjout = new Command("Afficher Reservation", Command.SCREEN, 1);
     Command cmdSms = new Command("SMS", Command.SCREEN, 1);
   
    Command cmdExit = new Command("Exit", Command.EXIT, 0);
     public static Midlet menu;
    
  public ajout_hotel ajout;
   public affichage_hotel affiche;
    Image img;
    ImageItem imgit;
    

    public void startApp() {
        try {
            img = Image.createImage("/image/Eurostars-Book-Hotel.png");
            imgit = new ImageItem(null, img, ImageItem.LAYOUT_LEFT, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         
        f.append(imgit);
         menu=this;
        display=Display.getDisplay(this);
        disp.setCurrent(f);
        f.addCommand(cmdExit);
        f.addCommand(cmdNextAffich);
        f.addCommand(cmdNextAjout);
        f.addCommand(cmdSms);
         f.setTicker(tk);
        
      //  f.addCommand(cmdNextAudio);
        f.setCommandListener(this);
        
        
    }
     public static Display getDisplay(){
         return display;
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmdExit){
          notifyDestroyed();
         
       }  
        
       if(c==cmdNextAffich){
           ajout= new ajout_hotel("hotel");
        display.setCurrent(ajout);
         
       }  
       if (c==cmdNextAjout){
            affiche= new affichage_hotel("hotel");
        display.setCurrent(affiche);
       }
        
    }
}
