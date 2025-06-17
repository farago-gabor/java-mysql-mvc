package hu.adatbazis.egyenisport.repository;

import hu.adatbazis.egyenisport.model.Merkozesek;
import hu.adatbazis.egyenisport.model.Versenyzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MerkozesekRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ResztvetelRepository resztvetelRepository;

    // Új mérkőzés hozzáadása
    public int addMerkozes(Merkozesek merkozes) {
        String sql = "INSERT INTO merkozesek (eredmeny, datum, helyszin) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, merkozes.getEredmeny(), java.sql.Date.valueOf(String.valueOf(merkozes.getDatum())), merkozes.getHelyszin());
    }

    // Összes mérkőzés lekérdezése
    public List<Merkozesek> getAllMerkozesek() {
        String sql = "SELECT * FROM merkozesek";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Merkozesek.class));
    }

    // Mérkőzés lekérdezése mId alapján
    public Merkozesek getMerkozesByMId(int mId) {
        String sql = "SELECT * FROM merkozesek WHERE mId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Merkozesek.class), mId);
    }

    // Mérkőzések lekérdezése helyszín alapján
    public List<Merkozesek> getMerkozesekByHelyszin(String helyszin) {
        String sql = "SELECT * FROM merkozesek WHERE helyszin = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Merkozesek.class), helyszin);
    }

    public String getGyoztesByMId(int mId) {
        String sql = "SELECT eredmeny FROM merkozesek WHERE mId = ?";
        String gyoztesId = jdbcTemplate.queryForObject(sql, String.class, mId);
        String sql2 = "SELECT nev FROM versenyzo WHERE vId = ?";
        return jdbcTemplate.queryForObject(sql2, String.class, Integer.valueOf(gyoztesId));
    }

    public String getVesztesByMId(int mId) {
        List<Versenyzo> resztvevok = resztvetelRepository.getVersenyzokByMId(mId);
        String sql = "SELECT eredmeny FROM merkozesek WHERE mId = ?";
        int gyoztesId = Integer.valueOf(jdbcTemplate.queryForObject(sql, String.class, mId));
        for (int i = 0; i < resztvevok.size(); i++) {
            if(resztvevok.get(i).getVId() == gyoztesId) {
                resztvevok.remove(i);
            }
        }
        return resztvevok.get(0).getNev();
    }
}
