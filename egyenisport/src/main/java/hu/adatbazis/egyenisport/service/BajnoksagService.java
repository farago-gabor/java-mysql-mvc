package hu.adatbazis.egyenisport.service;

import hu.adatbazis.egyenisport.model.Bajnoksag;
import hu.adatbazis.egyenisport.repository.BajnoksagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BajnoksagService {

    @Autowired
    private BajnoksagRepository bajnoksagRepository;

    public int addBajnoksag(Bajnoksag bajnoksag) {
        return bajnoksagRepository.addBajnoksag(bajnoksag);
    }

    public Bajnoksag getBajnoksag(int bId) {
        return bajnoksagRepository.getBajnoksagById(bId);
    }

    public List<Bajnoksag> getAllBajnoksagok() {
        return bajnoksagRepository.getAllBajnoksagok();
    }

    public int updateBajnoksag(Bajnoksag bajnoksag) {
        return bajnoksagRepository.updateBajnoksag(bajnoksag);
    }

    public int deleteBajnoksag(int bId) {
        return bajnoksagRepository.deleteBajnoksag(bId);
    }
}

