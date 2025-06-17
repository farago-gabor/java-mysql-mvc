package hu.adatbazis.egyenisport.controller;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import hu.adatbazis.egyenisport.model.Merkozesek;
import hu.adatbazis.egyenisport.service.MerkozesekService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MerkozesekController {

    @Autowired
    private MerkozesekService merkozesekService;

    @GetMapping("/merkozesek")
    public String getMerkozesek(Model model) {
        List<Merkozesek> merkozesek = merkozesekService.getAllMerkozesek();
        List<String> gyoztesek = new ArrayList<>();
        List<String> vesztesek = new ArrayList<>();
        for (int i = 0; i < merkozesek.size(); i++) {
            gyoztesek.add(merkozesekService.getGyoztesByMId(merkozesek.get(i).getMId()));
            vesztesek.add(merkozesekService.getVesztesByMId(merkozesek.get(i).getMId()));
        }
        model.addAttribute("merkozesek", merkozesek);
        model.addAttribute("gyoztesek", gyoztesek);
        model.addAttribute("vesztesek", vesztesek);
        return "merkozesek";
    }




    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        Felhasznalo felhasznalo = (Felhasznalo) session.getAttribute("user");
        model.addAttribute("user", felhasznalo);
    }
}
