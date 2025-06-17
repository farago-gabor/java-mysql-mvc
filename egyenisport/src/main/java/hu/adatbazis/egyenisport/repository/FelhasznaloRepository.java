package hu.adatbazis.egyenisport.repository;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class FelhasznaloRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Felhasználó hozzáadása
    public void addFelhasznalo(Felhasznalo felhasznalo) {
        String sql = "INSERT INTO felhasznalo (felhasznalonev, jelszo, nev, jogosultsag) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, felhasznalo.getFelhasznalonev(), felhasznalo.getJelszo(), felhasznalo.getNev(), felhasznalo.getJogosultsag());
    }

    // Felhasználó keresése felhasználónév alapján
    public Felhasznalo getFelhasznalo(String felhasznalonev) {
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalonev = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{felhasznalonev}, (ResultSet rs, int rowNum) ->
                    new Felhasznalo(
                            rs.getString("felhasznalonev"),
                            rs.getString("jelszo"),
                            rs.getString("nev"),
                            rs.getString("jogosultsag")
                    )
            );
        } catch (Exception e) {
            return null;
        }
    }

    // Felhasználó módosítása
    public void updateFelhasznalo(Felhasznalo felhasznalo) {
        String sql = "UPDATE felhasznalo SET jelszo = ?, nev = ?, jogosultsag = ? WHERE felhasznalonev = ?";
        jdbcTemplate.update(sql, felhasznalo.getJelszo(), felhasznalo.getNev(), felhasznalo.getJogosultsag(), felhasznalo.getFelhasznalonev());
    }

    // Felhasználó törlése
    public void deleteFelhasznalo(String felhasznalonev) {
        String sql = "DELETE FROM felhasznalo WHERE felhasznalonev = ?";
        jdbcTemplate.update(sql, felhasznalonev);
    }

    // Az összes felhasználó lekérdezése
    public List<Felhasznalo> getAllFelhasznalok() {
        String sql = "SELECT * FROM felhasznalo";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->
                new Felhasznalo(
                        rs.getString("felhasznalonev"),
                        rs.getString("jelszo"),
                        rs.getString("nev"),
                        rs.getString("jogosultsag")
                )
        );
    }
}

