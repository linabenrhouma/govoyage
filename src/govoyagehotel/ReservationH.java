/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govoyagehotel;

/**
 *
 * @author user
 */
public class ReservationH {
    
    
    private int id;
    private int hotel;
    private String client;
    private String date;
    private int nbre_jours;
    private int Total;
    private int nombrep;

    
    
    public ReservationH() {
    }

    public ReservationH(int id, int hotel, String client, String date, int nbre_jours, int Total, int nombrep) {
        this.id = id;
        this.hotel = hotel;
        this.client = client;
        this.date = date;
        this.nbre_jours = nbre_jours;
        this.Total = Total;
        this.nombrep = nombrep;
    }

    public ReservationH(int hotel, String client, String date, int nbre_jours, int Total, int nombrep) {
        this.hotel = hotel;
        this.client = client;
        this.date = date;
        this.nbre_jours = nbre_jours;
        this.Total = Total;
        this.nombrep = nombrep;
    }

    public ReservationH(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbre_jours() {
        return nbre_jours;
    }

    public void setNbre_jours(int nbre_jours) {
        this.nbre_jours = nbre_jours;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        this.Total = total;
    }

    public int getNombrep() {
        return nombrep;
    }

    public void setNombrep(int nombrep) {
        this.nombrep = nombrep;
    }

    public String toString() {
        return "ReservationH{" + "id=" + id + ", hotel=" + hotel + ", client=" + client + ", date=" + date + ", nbre_jours=" + nbre_jours + ", total=" + Total + ", nombrep=" + nombrep + '}';
    }
    
    
  void setHotel(String hotel) {
        this.hotel = Integer.parseInt(hotel);
    }
     void setId(String id) {
        this.id = Integer.parseInt(id);
    }
      void setTotal(String total) {
        this.Total = Integer.parseInt(total);
    }
     
     void setNbre_jours(String nbre_jours) {
        this.nbre_jours = Integer.parseInt(nbre_jours);
    }
    

     void setNombrep(String nombrep) {
        this.nombrep = Integer.parseInt(nombrep);
    }
    
    
    
    
    
    
    
    
    
    
    
}
