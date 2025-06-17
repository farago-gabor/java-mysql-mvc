package hu.adatbazis.egyenisport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Versenyzo {

    private int vId;
    private String nev;
    private Date szuletesiDatum;
    private String szuletesiHely;
    private String allampolgarsag;
    private float gyozelmiArany;
    private boolean aktiv;
}
