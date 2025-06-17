package hu.adatbazis.egyenisport.service;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import hu.adatbazis.egyenisport.repository.FelhasznaloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FelhasznaloService {

    private final FelhasznaloRepository felhasznaloRepository;

    public FelhasznaloService(FelhasznaloRepository felhasznaloRepository) {
        this.felhasznaloRepository = felhasznaloRepository;
    }

    public void addFelhasznalo(Felhasznalo felhasznalo) {
        felhasznaloRepository.addFelhasznalo(felhasznalo);
    }

    public Felhasznalo getFelhasznalo(String felhasznalonev) {
        return felhasznaloRepository.getFelhasznalo(felhasznalonev);
    }

    public void updateFelhasznalo(Felhasznalo felhasznalo) {
        felhasznaloRepository.updateFelhasznalo(felhasznalo);
    }

    public void deleteFelhasznalo(String felhasznalonev) {
        felhasznaloRepository.deleteFelhasznalo(felhasznalonev);
    }
}
