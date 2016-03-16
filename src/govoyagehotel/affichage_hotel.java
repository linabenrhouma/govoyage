/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author user
 */
public class affichage_hotel extends Form implements CommandListener,Runnable {
    
     int update=0;
    int delete=0;
    int Index;
    int ch;
    
    
     TextField tfid  =  new TextField("id" , null, 100, TextField.ANY);
    TextField tfhotel  =  new TextField("hotel" , null, 100, TextField.ANY);
    TextField tfclient    =  new TextField("client"      , null, 100, TextField.ANY);
    TextField tfdate =  new TextField("date", null, 100, TextField.ANY);
     TextField tfnbre_jours =  new TextField("nbre_jours" , null, 100, TextField.ANY);
    TextField tftotal =  new TextField("total", null, 100, TextField.ANY);
    TextField tfnombrep =  new TextField("nombrep" , null, 100, TextField.ANY);
    
    
    Command cmdParse = new Command("hotel", Command.SCREEN, 0);
    Command cmdBack =  new Command("Back"    , Command.BACK  , 0);
    Command cmdAjout =  new Command("Retour à l'ajout"  , Command.OK  , 0);
    Command cmdSupp = new Command("supprimer", Command.SCREEN, 0);
    Command cmdList = new Command("Liste des hotels", Command.SCREEN, 0);
    Command cmdUpdate = new Command("Modifier", Command.SCREEN, 0);
    Command cmdConfModif = new Command("confirmer Modification", Command.SCREEN, 0);
     Command cmdRetour = new Command("Retour au menu", Command.SCREEN, 0);
    
    
    ReservationH[] personnes;
    List lst = new List("hotels", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form info = new Form("Infos hotels");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    StringBuffer sb1 = new StringBuffer();
    Form f3 = new Form("Erreur");
    Form f2 = new Form("supprimé");
    Form f4 = new Form("modifié");
    Form modif = new Form("Modifier hotel");
    
    
    
    

    public affichage_hotel(String title) {
        super(title);
           this.append("appuyer sur hotels pour afficher la liste");
        this.addCommand(cmdParse);
        this.setCommandListener(this);
        lst.addCommand(cmdAjout);
        lst.setCommandListener(this);
        
        info.addCommand(cmdList);
        info.addCommand(cmdSupp);
        info.addCommand(cmdUpdate);
        info.setCommandListener(this);

        f2.addCommand(cmdList);
        f4.addCommand(cmdRetour);
        f2.setCommandListener(this);
        f4.setCommandListener(this);
        
       // modif.append(tfid);
       // modif.append(tfhotel);
       // modif.append(tfclient);
        modif.append(tfdate);
        //modif.append(tfnbre_jours);
        //modif.append(tftotal);
        //modif.append(tfnombrep);
        
        modif.addCommand(cmdConfModif);
        modif.setCommandListener(this);
        
        
    }

    public void commandAction(Command c, Displayable d) {
       if (c == cmdParse) {
            Midlet.getDisplay().setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
                             }
        
           
         if(c==cmdSupp)
        {   Index=lst.getSelectedIndex();            
            delete=1;
            Thread th = new Thread(this);
            th.start();
        }
        
        if (c == List.SELECT_COMMAND) {
            info.append("Informations Hotels: \n");
            info.append(showPersonne(lst.getSelectedIndex()));
            Midlet.getDisplay().setCurrent(info);
        }

        if (c == cmdBack) {
            info.deleteAll();
            Midlet.getDisplay().setCurrent(lst);
        }   
         if (c == cmdList) {
            Midlet.getDisplay().setCurrent(lst);        
                           }
         if (c== cmdAjout)
         {
             Midlet.getDisplay().setCurrent(new ajout_hotel("Ajout"));
         }
         if(c== cmdUpdate){
           
         
           tfdate.setString(personnes[lst.getSelectedIndex()].getDate());
         
           Index=lst.getSelectedIndex();
                      Midlet.getDisplay().setCurrent(modif);


           
            
        }
         if (c== cmdConfModif){
             update=1;
            Thread th = new Thread(this);
            th.start();
         }
         if (c== cmdRetour){
             
              Midlet.menu.disp.setCurrent(Midlet.menu.f);
            
         }
        
    }

    public void run() {
        if(delete==0){  
              if(update==0){
    
         try {
            // this will handle our XML
            // c'est une classe qui utilise le parseur pour desassembler le flus recue
            //sax qui est importer est un parseur xml
           HotelHandler personnesHandler = new HotelHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/GoVoyageHotel/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
            personnes = personnesHandler.getPersonne();

            if (personnes.length > 0) {
               for (int i = 0; i < personnes.length; i++) {
                    lst.append(personnes[i].getId()+" "
                             +personnes[i].getHotel()+" "+personnes[i].getClient()+" "
                             +personnes[i].getDate() +personnes[i].getNbre_jours()+" "+personnes[i].getTotal()+" " +personnes[i].getNombrep(), null);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        Midlet.getDisplay().setCurrent(lst);
    }
     if(update==1){
            try {
            
           
                HttpConnection hc = (HttpConnection)Connector.open("http://localhost/GoVoyageHotel/modifier.php?date="+tfdate.getString().trim()+"&id="+personnes[Index].getId());
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                
                while ((ch = dis.read()) != -1) {
                    sb1.append((char)ch);
                }
                
                if ("OK".equals(sb1.toString().trim())) {
                    
                    Midlet.getDisplay().setCurrent(f4);
                }else{
                    Midlet.getDisplay().setCurrent(f3);
              
                }
                sb1 = new StringBuffer();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
     }
           
            if(delete==1){
                try {
            //System.out.println("http://localhost/taxij2me/supprimer.php"+"?id="+personnes[Index].getId());
            HttpConnection    hc = (HttpConnection)Connector.open("http://localhost/GoVoyageHotel/supprimer.php"+"?id="+personnes[Index].getId());
            DataInputStream   dis = new DataInputStream(hc.openDataInputStream());
                
                while ((ch = dis.read()) != -1) {
                    sb1.append((char)ch);
                }
                
                if ("OK".equals(sb1.toString().trim())) {
                    Midlet.getDisplay().setCurrent(f2);
                    //Midlet.getDisplay().setCurrent(lst);//redirection pour affichage liste
                    System.out.println("hotel supprimé");
                }else{
                    Midlet.getDisplay().setCurrent(f3);
                    System.out.println("erreur");
                }
                sb1 = new StringBuffer();
                delete=0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }
        
    }
    
     private String showPersonne(int i) {
        String res = "";
        if (personnes.length > 0) {
            sb.append("*Id  : ");
            sb.append(personnes[i].getId());
            sb.append("\n");
            sb.append("*hotel : ");
            sb.append(personnes[i].getHotel());
            sb.append("\n");
            sb.append("*client : ");
            sb.append(personnes[i].getClient());
            sb.append("\n");
            sb.append("*date : ");
            sb.append(personnes[i].getDate());
            sb.append("\n");
             sb.append("*nbre_jours : ");
            sb.append(personnes[i].getNbre_jours());
             sb.append("\n");
             sb.append("*total : ");
            sb.append(personnes[i].getTotal());
            sb.append("\n");
             sb.append("*nombrep : ");
            sb.append(personnes[i].getNombrep());
             
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
  
  
          public String supphotel(int i) {
        String id_supp = "";
        if (personnes.length > 0) {
            sb.append("*Id : ");
            sb.append(personnes[i].getId());
            
        }
        id_supp = sb.toString();
        sb = new StringBuffer("");
        return id_supp;
    }
    
    
    
}
