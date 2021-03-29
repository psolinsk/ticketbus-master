package pl.edu.wszib.ticketbus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "busTable")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String miastoPoczatkowe;
    private String miastoKoncowe;
    private Integer numerBiletu;
    private Integer iloscMiejsc;
    private Double cena;

    public Bus() {
    }

    public Bus(Integer id, String miastoPoczatkowe, String miastoKoncowe, Integer numerBiletu, Integer iloscMiejsc, Double cena) {
        Id = id;
        this.miastoPoczatkowe = miastoPoczatkowe;
        this.miastoKoncowe = miastoKoncowe;
        this.numerBiletu = numerBiletu;
        this.iloscMiejsc = iloscMiejsc;
        this.cena = cena;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getMiastoPoczatkowe() {
        return miastoPoczatkowe;
    }

    public void setMiastoPoczatkowe(String miastoPoczatkowe) {
        this.miastoPoczatkowe = miastoPoczatkowe;
    }

    public String getMiastoKoncowe() {
        return miastoKoncowe;
    }

    public void setMiastoKoncowe(String miastoKoncowe) {
        this.miastoKoncowe = miastoKoncowe;
    }

    public Integer getNumerBiletu() {
        return numerBiletu;
    }

    public void setNumerBiletu(Integer numerBiletu) {
        this.numerBiletu = numerBiletu;
    }

    public Integer getIloscMiejsc() {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(Integer iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "Id=" + Id +
                ", miastoPoczatkowe='" + miastoPoczatkowe + '\'' +
                ", miastoKoncowe='" + miastoKoncowe + '\'' +
                ", numerBiletu=" + numerBiletu +
                ", iloscMiejsc=" + iloscMiejsc +
                ", cena=" + cena +
                '}';
    }

    public Bus clone (){
        return new Bus(this.Id, this.miastoPoczatkowe, this.miastoKoncowe, this.numerBiletu, this.iloscMiejsc, this.cena);
    }
}
