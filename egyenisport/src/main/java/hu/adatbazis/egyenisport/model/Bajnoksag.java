package hu.adatbazis.egyenisport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bajnoksag {
    private int bId;
    private String nev;
    private Date kezdoDatum;
    private Date befejezoDatum;
    private String helyszin;
    private boolean meghivasos;
}
