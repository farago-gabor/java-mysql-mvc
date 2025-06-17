package hu.adatbazis.egyenisport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resztvetel {
    private int vId; // Versenyző ID
    private int mId; // Mérkőzés ID
}