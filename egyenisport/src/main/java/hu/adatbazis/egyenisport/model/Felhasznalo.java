package hu.adatbazis.egyenisport.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Felhasznalo {

    private String felhasznalonev;
    private String jelszo;
    private String nev;
    private String jogosultsag;
}
