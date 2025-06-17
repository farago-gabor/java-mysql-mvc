package hu.adatbazis.egyenisport.service;

import hu.adatbazis.egyenisport.model.Merkozesek;
import hu.adatbazis.egyenisport.model.Versenyzo;
import hu.adatbazis.egyenisport.repository.MerkozesekRepository;
import hu.adatbazis.egyenisport.repository.ResztvetelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerkozesekService {

    @Autowired
    private final MerkozesekRepository merkozesekRepository;

    public MerkozesekService(MerkozesekRepository merkozesekRepository) {
        this.merkozesekRepository = merkozesekRepository;
    }

    public int addMerkozes(Merkozesek merkozes) {
        return merkozesekRepository.addMerkozes(merkozes);
    }

    public List<Merkozesek> getAllMerkozesek() {
        return merkozesekRepository.getAllMerkozesek();
    }

    public Merkozesek getMerkozesByMId(int mId) {
        return merkozesekRepository.getMerkozesByMId(mId);
    }

    public List<Merkozesek> getMerkozesekByHelyszin(String helyszin) {
        return merkozesekRepository.getMerkozesekByHelyszin(helyszin);
    }

    public String getGyoztesByMId(int mId) {
        return merkozesekRepository.getGyoztesByMId(mId);
    }

    public String getVesztesByMId(int mId) {
        return merkozesekRepository.getVesztesByMId(mId);
    }
}
