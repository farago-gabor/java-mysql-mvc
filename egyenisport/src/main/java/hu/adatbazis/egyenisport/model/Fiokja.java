package hu.adatbazis.egyenisport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fiokja {
    private int vId;             // Versenyző ID
    private String felhasznalonev; // Felhasználónév
}
