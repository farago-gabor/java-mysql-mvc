package hu.adatbazis.egyenisport.repository;

import hu.adatbazis.egyenisport.model.Merkozesek;
import hu.adatbazis.egyenisport.model.Resztvetel;
import hu.adatbazis.egyenisport.model.Versenyzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResztvetelRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addResztvetel(Resztvetel resztvetel) {
        String sql = "INSERT INTO resztvetel (vId, mId) VALUES (?, ?)";
        return jdbcTemplate.update(sql, resztvetel.getVId(), resztvetel.getMId());
    }
    public List<Resztvetel> getAllResztvetel() {
        String sql = "SELECT * FROM resztvetel";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resztvetel.class));
    }

    public List<Versenyzo> getVersenyzokByMId(int mId) {
        String sql = "SELECT * FROM versenyzo v " +
                "JOIN resztvetel r ON v.vId = r.vId " +
                "JOIN merkozesek m ON r.mId = m.mId " +
                "WHERE m.mId = ?;";
        try {
            return jdbcTemplate.query(sql, new Object[]{mId}, (ResultSet rs, int rowNum) ->
                    new Versenyzo(
                            rs.getInt("vId"),
                            rs.getString("nev"),
                            rs.getDate("szuletesi_datum"),
                            rs.getString("szuletesi_hely"),
                            rs.getString("allampolgarsag"),
                            rs.getFloat("gyozelmi_arany"),
                            rs.getBoolean("aktiv")
                    )
            );
        } catch (Exception e) {
            return null;
        }
    }

    public List<Merkozesek> getMerkozesekByVId(int vId) {
        String sql = "SELECT * FROM merkozesek m " +
                "JOIN resztvetel r ON m.mId = r.mId " +
                "JOIN versenyzo v ON r.vId = v.vId " +
                "WHERE m.mId = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Merkozesek.class), vId);
    }
}
