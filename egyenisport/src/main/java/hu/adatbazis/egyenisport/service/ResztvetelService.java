package hu.adatbazis.egyenisport.service;

import hu.adatbazis.egyenisport.model.Merkozesek;
import hu.adatbazis.egyenisport.model.Resztvetel;
import hu.adatbazis.egyenisport.model.Versenyzo;
import hu.adatbazis.egyenisport.repository.ResztvetelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResztvetelService {

    private final ResztvetelRepository resztvetelRepository;

    @Autowired
    public ResztvetelService(ResztvetelRepository resztvetelRepository) {
        this.resztvetelRepository = resztvetelRepository;
    }

    public int addResztvetel(Resztvetel resztvetel) {
        return resztvetelRepository.addResztvetel(resztvetel);
    }

    public List<Resztvetel> getAllResztvetel() {
        return resztvetelRepository.getAllResztvetel();
    }

    public List<Versenyzo> getVersenyzokByMId(int mId) {
        return resztvetelRepository.getVersenyzokByMId(mId);
    }

    public List<Merkozesek> getMerkozesekByVId(int vId) {
        return resztvetelRepository.getMerkozesekByVId(vId);
    }
}
