package hu.adatbazis.egyenisport.repository;

import hu.adatbazis.egyenisport.model.Versenyzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersenyzoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //hozzáadás
    public int addVersenyzo(Versenyzo versenyzo) {
        String sql = "INSERT INTO versenyzo (nev, szuletesi_datum, szuletesi_hely, allampolgarsag, gyozelmi_arany, aktiv) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                versenyzo.getNev(),
                versenyzo.getSzuletesiDatum(),
                versenyzo.getSzuletesiHely(),
                versenyzo.getAllampolgarsag(),
                versenyzo.getGyozelmiArany(),
                versenyzo.isAktiv());
    }

    public Versenyzo getVersenyzoById(int vId) {
        String sql = "SELECT * FROM versenyzo WHERE vId = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[]{vId}, new BeanPropertyRowMapper<>(Versenyzo.class));
        } catch (Exception e){ return null; }
    }

    public List<Versenyzo> getAllVersenyzo(){
        String sql = "SELECT * FROM versenyzo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Versenyzo.class));
    }

    public int updateVersenyzo(Versenyzo versenyzo) {
        String sql = "UPDATE versenyzo SET nev = ?, szuletesi_datum = ?, szuletesi_hely = ?, allampolgarsag = ?, gyozelmi_arany = ?, aktiv = ? WHERE vId = ?";
        return jdbcTemplate.update(sql,
                versenyzo.getNev(),
                versenyzo.getSzuletesiDatum(),
                versenyzo.getSzuletesiHely(),
                versenyzo.getAllampolgarsag(),
                versenyzo.getGyozelmiArany(),
                versenyzo.isAktiv(),
                versenyzo.getVId());
    }

    public int deleteVersenyzo(int vId) {
        String sql = "DELETE FROM versenyzo WHERE vId = ?";
        return jdbcTemplate.update(sql, vId);
    }

    public List<Versenyzo> listVersenyzoByMerkozesId(int mId) {
        String sql = "SELECT * FROM versenyzo " +
                "FROM Versenyzo v " +
                "JOIN Resztvetel r ON v.vId = r.vId " +
                "JOIN Merkozesek m ON r.mId = m.mId " +
                "WHERE m.mId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Versenyzo.class));
    }
}
