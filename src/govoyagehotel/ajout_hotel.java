/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author user
 */
public class ajout_hotel extends Form implements CommandListener, Runnable {
     
    
    TextField tfid  =  new TextField("id" , null, 100, TextField.ANY);
    TextField tfhotel =  new TextField("Hotel"      , null, 100, TextField.ANY);
    TextField tfclient =  new TextField("client ", null, 100, TextField.ANY);
   TextField tfdate =  new TextField("date  ", null, 100, TextField.ANY);
    Date dt = new Date();
    TextField tfnbrs_jours =  new TextField("Nombres de jours", null, 100, TextField.NUMERIC);
    TextField tftotal =  new TextField("Total", null, 100, TextField.NUMERIC);
    TextField tfnombrep =  new TextField("Nombres de personnes", null, 100, TextField.NUMERIC);
     
    Form f2 = new Form("Erreur");
    
    Command cmdValider = new Command("ajouter", Command.SCREEN, 0);
   
    Command cmdBack    = new Command("cmdBack", Command.BACK, 0);
   
     Command cmdRetour = new Command("Retour", Command.EXIT, 0);
     
        
     HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/GoVoyageHotel/ajout.php";
    StringBuffer sb = new StringBuffer();
    int ch;
  
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    public ajout_hotel(String title) {
        super(title);
         this.append(tfid);
        this.append(tfhotel);
        this.append(tfclient);
        this.append(tfdate);
        this.append(tfnbrs_jours);
        this.append(tftotal);
        this.append(tfnombrep);
         this.addCommand(cmdValider);
       
         this.addCommand(cmdRetour);
         
      
        this.setCommandListener(this);
    }
   

    public void commandAction(Command c, Displayable d) {
       if (c == cmdValider) {
             Thread th = new Thread(this);
            th.start();
        }
       
           if (c == cmdRetour) {
           Midlet.menu.disp.setCurrent(Midlet.menu.f);
        }
           
          
          
    }

    public void run() {
        try {
            
         
            
        
          hc = (HttpConnection) Connector.open(url+"?hotel="+tfhotel.getString().trim()+"&client="+tfclient.getString().trim()+"&date="+tfdate.getString().trim()+"&nbre_jours="+tfnbrs_jours.getString().trim()+"&Total="+tftotal.getString().trim()+"&nombrep="+tfnombrep.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream()); //recupaire le flus rentrant du serveur
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    Midlet.getDisplay().setCurrent(new affichage_hotel("aaaaaa"));
                }else{
                    Midlet.getDisplay().setCurrent(alerta);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
    }
   
    
}
