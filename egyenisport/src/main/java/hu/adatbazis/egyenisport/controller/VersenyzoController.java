package hu.adatbazis.egyenisport.controller;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import hu.adatbazis.egyenisport.model.Versenyzo;
import hu.adatbazis.egyenisport.service.VersenyzoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class VersenyzoController {
    @Autowired
    private VersenyzoService versenyzoService;

    @GetMapping("/versenyzok")
    public String getVersenyzok(Model model){
        List<Versenyzo> versenyzok = versenyzoService.getAllVersenyzo();
        model.addAttribute("versenyzok", versenyzok);
        return "versenyzok";
    }

    @PostMapping("/versenyzok/modosit")
    public String updateVersenyzo(@RequestParam("vId") int vId,
                                  @RequestParam("nev") String nev,
                                  @RequestParam("szul_datum") String szulDatum,
                                  @RequestParam("szul_hely") String szulHely,
                                  @RequestParam("allampolgarsag") String allampolgarsag,
                                  @RequestParam("gyozelmi_arany") float gyozelmiArany,
                                  @RequestParam("aktiv") boolean aktiv){
        gyozelmiArany *= 0.01;
        Versenyzo v = versenyzoService.getVersenyzo(vId);
        if(v != null) {
            v.setNev(nev);
            v.setSzuletesiDatum(java.sql.Date.valueOf(szulDatum));
            v.setSzuletesiHely(szulHely);
            v.setAllampolgarsag(allampolgarsag);
            v.setGyozelmiArany(gyozelmiArany);
            v.setAktiv(aktiv);

            versenyzoService.updateVersenyzo(v);
        }
        return "redirect:/versenyzok";
    }

    @PostMapping("/versenyzok/torol")
    public String deleteVersenyzo(@RequestParam("vId") int vId) {
        versenyzoService.deleteVersenyzo(vId);
        return "redirect:/versenyzok";
    }

    @PostMapping("/versenyzo/hozzaad")
    public String addVersenyzo(@RequestParam("nev") String nev,
                               @RequestParam("szuletesi_datum") @DateTimeFormat(pattern = "yyyy-MM-dd") Date szuletesiDatum,
                               @RequestParam("szuletesi_hely") String szuletesiHely,
                               @RequestParam("allampolgarsag") String allampolgarsag,
                               @RequestParam("gyozelmi_arany") float gyozelmiArany,
                               @RequestParam("aktiv") boolean aktiv) {
        gyozelmiArany *= 0.01;
        Versenyzo v = new Versenyzo();
        v.setNev(nev);
        v.setSzuletesiDatum(new java.sql.Date(szuletesiDatum.getTime()));  // Update this field
        v.setSzuletesiHely(szuletesiHely);
        v.setAllampolgarsag(allampolgarsag);
        v.setGyozelmiArany(gyozelmiArany);
        v.setAktiv(aktiv);

        versenyzoService.addVersenyzo(v);

        return "redirect:/versenyzok";
    }

    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        Felhasznalo felhasznalo = (Felhasznalo) session.getAttribute("user");
        model.addAttribute("user", felhasznalo);
    }
}
