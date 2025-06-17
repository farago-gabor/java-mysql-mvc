package hu.adatbazis.egyenisport.controller;

import hu.adatbazis.egyenisport.model.Bajnoksag;
import hu.adatbazis.egyenisport.model.Felhasznalo;
import hu.adatbazis.egyenisport.service.BajnoksagService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class BajnoksagController {

    @Autowired
    private BajnoksagService bajnoksagService;

    // Bajnokságok listázása
    @GetMapping("/bajnoksagok")
    public String getBajnoksagok(Model model) {
        List<Bajnoksag> bajnoksagok = bajnoksagService.getAllBajnoksagok();
        model.addAttribute("bajnoksagok", bajnoksagok);
        return "bajnoksagok";
    }

    // Bajnokság módosítása (POST)
    @PostMapping("/bajnoksagok/modosit")
    public String updateBajnoksag(@RequestParam("bId") int bId,
                                     @RequestParam("nev") String nev,
                                     @RequestParam("kezdo_datum") String kezdoDatum,
                                     @RequestParam("befejezo_datum") String befejezoDatum,
                                     @RequestParam("helyszin") String helyszin,
                                     @RequestParam("meghivasos") boolean meghivasos) {
        Bajnoksag bajnoksag = bajnoksagService.getBajnoksag(bId);
        if (bajnoksag != null) {
            bajnoksag.setNev(nev);
            bajnoksag.setKezdoDatum(java.sql.Date.valueOf(kezdoDatum));
            bajnoksag.setBefejezoDatum(java.sql.Date.valueOf(befejezoDatum));
            bajnoksag.setHelyszin(helyszin);
            bajnoksag.setMeghivasos(meghivasos);

            bajnoksagService.updateBajnoksag(bajnoksag);
        }
        return "redirect:/bajnoksagok";
    }

    // Bajnokság törlése (POST)
    @PostMapping("/bajnoksagok/torol")
    public String deleteBajnoksag(@RequestParam("bId") int bId) {
        bajnoksagService.deleteBajnoksag(bId);
        return "redirect:/bajnoksagok";
    }

    @PostMapping("/bajnoksagok")
    public String addBajnoksag(@RequestParam String nev,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date kezdo_datum,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date befejezo_datum,
                               @RequestParam String helyszin,
                               @RequestParam Boolean meghivasos,
                               Model model) {

        Bajnoksag bajnoksag = new Bajnoksag();
        bajnoksag.setNev(nev);
        bajnoksag.setKezdoDatum(new java.sql.Date(kezdo_datum.getTime())); // Konverzió
        bajnoksag.setBefejezoDatum(new java.sql.Date(befejezo_datum.getTime())); // Konverzió
        bajnoksag.setHelyszin(helyszin);
        bajnoksag.setMeghivasos(meghivasos);

        bajnoksagService.addBajnoksag(bajnoksag);

        return "redirect:/bajnoksagok";
    }


    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        Felhasznalo felhasznalo = (Felhasznalo) session.getAttribute("user");
        model.addAttribute("user", felhasznalo);
    }
}

