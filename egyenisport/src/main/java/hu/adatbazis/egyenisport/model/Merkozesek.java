package hu.adatbazis.egyenisport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merkozesek {
    private int mId;
    private String eredmeny;
    private Date datum;
    private String helyszin;
}
