package hu.adatbazis.egyenisport.repository;

import hu.adatbazis.egyenisport.model.Bajnoksag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BajnoksagRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Új bajnokság hozzáadása
    public int addBajnoksag(Bajnoksag bajnoksag) {
        String sql = "INSERT INTO bajnoksag (nev, kezdo_datum, befejezo_datum, helyszin, meghivasos) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                bajnoksag.getNev(),
                java.sql.Date.valueOf(String.valueOf(bajnoksag.getKezdoDatum())),
                java.sql.Date.valueOf(String.valueOf(bajnoksag.getBefejezoDatum())),
                bajnoksag.getHelyszin(),
                bajnoksag.isMeghivasos());
    }

    // Bajnokság keresése ID alapján
    public Bajnoksag getBajnoksagById(int bId) {
        String sql = "SELECT * FROM Bajnoksag WHERE bId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{bId}, new BeanPropertyRowMapper<>(Bajnoksag.class));
        } catch (Exception e) {
            return null;  // Ha nem találunk rekordot, null-t adunk vissza
        }
    }

    // Minden bajnokság lekérdezése
    public List<Bajnoksag> getAllBajnoksagok() {
        String sql = "SELECT * FROM Bajnoksag";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bajnoksag.class));
    }

    // Bajnokság frissítése
    public int updateBajnoksag(Bajnoksag bajnoksag) {
        String sql = "UPDATE Bajnoksag SET nev = ?, kezdo_datum = ?, befejezo_datum = ?, helyszin = ?, meghivasos = ? WHERE bId = ?";
        return jdbcTemplate.update(sql, bajnoksag.getNev(), bajnoksag.getKezdoDatum(),
                bajnoksag.getBefejezoDatum(), bajnoksag.getHelyszin(),
                bajnoksag.isMeghivasos(), bajnoksag.getBId());
    }

    // Bajnokság törlése ID alapján
    public int deleteBajnoksag(int bId) {
        String sql = "DELETE FROM Bajnoksag WHERE bId = ?";
        return jdbcTemplate.update(sql, bId);
    }
}
