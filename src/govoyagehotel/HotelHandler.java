/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author user
 */
public class HotelHandler extends DefaultHandler{
    
    
      private Vector personnes;
    String idTag = "close";
    String hotelTag = "close";
    String clientTag = "close";
    String dateTag = "close";
    String nbre_joursTag = "close";
    String totalTag = "close";
    String nombrepTag = "close";

    public HotelHandler() {
         personnes = new Vector();
    }
   
     public ReservationH[] getPersonne() {
        ReservationH[] personness = new ReservationH[personnes.size()];
        personnes.copyInto(personness);
        return personness;
    }
    
    private ReservationH currentHotel;
    
    
    
    
     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentHotel = new ReservationH();
            //2ème methode pour parser les attributs
         currentHotel.setId(attributes.getValue("id"));
       currentHotel.setHotel(attributes.getValue("hotel"));
            currentHotel.setClient(attributes.getValue("client"));
            currentHotel.setDate(attributes.getValue("date"));
             currentHotel.setNbre_jours(attributes.getValue("nbre_jours"));
             currentHotel.setTotal(attributes.getValue("Total"));
             currentHotel.setNombrep(attributes.getValue("nombrep"));
            
           
            /****/
            
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("hotel")) {
            hotelTag = "open";
        } else if (qName.equals("client")) {
            clientTag = "open";
            
          } else if (qName.equals("date")) {
            dateTag = "open";
        
        } else if (qName.equals("nbre_jours")) {
            nbre_joursTag = "open";
        
       } else if (qName.equals("Total")) {
            totalTag = "open";
            
             
        } else if (qName.equals("nombrep")) {
            nombrepTag = "open";
       
    }}

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            personnes.addElement(currentHotel);
            currentHotel = null;
             } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("hotel")) {
            hotelTag = "close";
        } else if (qName.equals("client")) {
            clientTag = "close";
        
        } else if (qName.equals("date")) {
           dateTag = "close";
        
        } else if (qName.equals("nbre_jours")) {
            nbre_joursTag = "close";
        
       } else if (qName.equals("Total")) {
            totalTag = "close";
            
             } else if (qName.equals("nombrep")) {
            nombrepTag = "close";
       
    }}
    
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentHotel != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentHotel.setId(id);
            } else
                if (hotelTag.equals("open")) {
                String hotel = new String(ch, start, length).trim();
                currentHotel.setHotel(hotel);
            } else
                    if (clientTag.equals("open")) {
                String Client = new String(ch, start, length).trim();
                currentHotel.setClient(Client);
            
            } else
                    if (dateTag.equals("open")) {
                String Date = new String(ch, start, length).trim();
               currentHotel.setDate(Date);
        }
         } else
                    if (nbre_joursTag.equals("open")) {
                String nbre_jours = new String(ch, start, length).trim();
                currentHotel.setNbre_jours(nbre_jours);
    
                     } else
                    if (totalTag.equals("open")) {
                String total = new String(ch, start, length).trim();
                currentHotel.setTotal(total);
 } 
        
          else
                    if (nombrepTag.equals("open")) {
                String nombrep = new String(ch, start, length).trim();
                currentHotel.setNombrep(nombrep);
 } 
        
          
    }
    
}
