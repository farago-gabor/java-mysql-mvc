package hu.adatbazis.egyenisport.service;

import hu.adatbazis.egyenisport.model.Bajnoksag;
import hu.adatbazis.egyenisport.model.Versenyzo;
import hu.adatbazis.egyenisport.repository.VersenyzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersenyzoService {

    @Autowired
    private VersenyzoRepository versenyzoRepository;

    public int addVersenyzo(Versenyzo v) {
        return versenyzoRepository.addVersenyzo(v);
    }

    public Versenyzo getVersenyzo(int vId) {
        return versenyzoRepository.getVersenyzoById(vId);
    }

    public List<Versenyzo> getAllVersenyzo() {
        return versenyzoRepository.getAllVersenyzo();
    }

    public int updateVersenyzo(Versenyzo versenyzo) {
        return versenyzoRepository.updateVersenyzo(versenyzo);
    }

    public int deleteVersenyzo(int bId) {
        return versenyzoRepository.deleteVersenyzo(bId);
    }

    public List<Versenyzo> getVersenyzoByMerkozesId(int mId) {
        return versenyzoRepository.listVersenyzoByMerkozesId(mId);
    }
}
