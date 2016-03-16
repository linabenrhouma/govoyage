/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author user
 */
public class Recherche extends MIDlet implements CommandListener, Runnable, ItemCommandListener {

   
    
    
  Display disp = Display.getDisplay(this);
    private StringItem okButton;
    private Command selectCommand;
    Form f1 = new Form("Rechercher");
    Form loadingDialog = new Form("Please Wait");
    Form f3 = new Form("Detailles medicament");
    ReservationH[] personnes;
    String[] tab2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    ChoiceGroup Ch2 = new ChoiceGroup("Nom du client", ChoiceGroup.POPUP, tab2, null);
    String[] tab4 = {"0000-00-00","2016-02-13", "0000-00-25","2001-07-25","25/07/2001","12/12/2012","1212","aa"};
    ChoiceGroup Ch4 = new ChoiceGroup("date", ChoiceGroup.EXCLUSIVE, tab4, null);
    Command cmdCh = new Command("Chercher", Command.OK, 0);
    //Connection
//    HttpConnection hc;
//    DataInputStream dis;
    StringBuffer sb;
    //int ch;
    List lst = new List("réservation trouvée", List.IMPLICIT);
    Command cmdBack = new Command("Back", Command.BACK, 0);

    /*  public Recherche(){
     }*/
    public Recherche() {
        selectCommand = new Command("Select", Command.OK, 0);
        okButton = new StringItem("", "Video", StringItem.BUTTON);
        okButton.setDefaultCommand(selectCommand);
        okButton.setItemCommandListener(this);
   
    }

    
    
    
    public void startApp() {
         f1.append("Choisissez vos conditions de recherche");
        f1.append(Ch2);
        f1.append(Ch4);
        f1.addCommand(cmdCh);
        f1.setCommandListener(this);
        //f1.setItemStateListener(this);
        lst.setCommandListener(this);

        f3.addCommand(cmdBack);
        //f3.append(okButton);
        f3.setCommandListener(this);

        disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
         if (c == cmdCh) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        if (c == List.SELECT_COMMAND) {
            f3.append("Informations du réservation: \n");
           
            f3.append(showPersonne(lst.getSelectedIndex()));
            f3.append(okButton);
            
            disp.setCurrent(f3);
        }
        if (c == cmdBack) {
            f3.deleteAll();
            disp.setCurrent(lst);
        }
        
    }

    public void run() {
        try {
            // this will handle our XML
            HotelHandler personnesHandler = new HotelHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/GoVoyageHotel/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
            personnes = personnesHandler.getPersonne();
            if (personnes.length > 0) {
                for (int i = 0; i < personnes.length; i++) {
                    //Conditions:-----------------
                    int id1 = Ch2.getSelectedIndex();
                    String s = Ch2.getString(id1);
                    
                    if ( 
                            (personnes[i].getClient().toUpperCase()).startsWith(s)
                           
                            || Ch4.getString(Ch4.getSelectedIndex()).equals(personnes[i].getDate())) {

//                   System.out.println(Ch1.getString(Ch1.getSelectedIndex()));
                       
                        System.out.println(personnes[i].getDate());
                        
                        lst.append(personnes[i].getClient(), null);
                      
                      
                        
                    }
                    
                }


               

            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
        
    }
    
      private String showPersonne(int i) {
        String res = "";
        if (personnes.length > 0) {
            sb.append("*Id maison : ");
            sb.append(personnes[i].getId());
            sb.append("\n");
            sb.append("*maison : ");
            sb.append(personnes[i].getHotel());
            sb.append("\n");
            sb.append("*client : ");
            sb.append(personnes[i].getClient());
            sb.append("\n");
            sb.append("*date : ");
            sb.append(personnes[i].getDate());
            sb.append("\n");
             sb.append("*nbre_jrs : ");
            sb.append(personnes[i].getNbre_jours());
             sb.append("*total : ");
            sb.append(personnes[i].getTotal());
             
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public void commandAction(Command c, Item item) {
         if (c == selectCommand) {
//--------------------------------7ot win béch yhizz------------ :p
        }
    }
}
