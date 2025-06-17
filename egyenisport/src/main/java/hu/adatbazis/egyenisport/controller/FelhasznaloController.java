package hu.adatbazis.egyenisport.controller;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import hu.adatbazis.egyenisport.service.FelhasznaloService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FelhasznaloController {

    @Autowired
    private FelhasznaloService felhasznaloService;

    // Bejelentkezési oldal
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Regisztrációs oldal
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Regisztráció kezelése
    @PostMapping("/register")
    public String registerUser(@RequestParam String felhasznalonev, @RequestParam String jelszo, @RequestParam String nev, Model model) {

        // Ellenőrizzük, hogy a felhasználónév már létezik-e
        if (felhasznaloService.getFelhasznalo(felhasznalonev) != null) {
            model.addAttribute("error", "A felhasználónév már foglalt!");
            return "register";
        }

        // Új Felhasznalo objektum létrehozása
        Felhasznalo felhasznalo = new Felhasznalo();
        felhasznalo.setFelhasznalonev(felhasznalonev);
        felhasznalo.setJelszo(jelszo);
        felhasznalo.setNev(nev);
        felhasznalo.setJogosultsag("nezo");

        // Új felhasználó hozzáadása
        felhasznaloService.addFelhasznalo(felhasznalo);

        return "redirect:/login";  // Sikeres regisztráció után visszairányítjuk a bejelentkezési oldalra
    }

    // Bejelentkezés kezelése
    @PostMapping("/login")
    public String loginUser(@RequestParam String felhasznalonev, @RequestParam String jelszo, Model model, HttpSession session) {
        // Keresés a felhasználó adatbázisában a felhasználónév alapján
        Felhasznalo felhasznalo = felhasznaloService.getFelhasznalo(felhasznalonev);

        if (felhasznalo == null || !felhasznalo.getJelszo().equals(jelszo)) {
            // Ha a felhasználó nem található, vagy a jelszó nem egyezik, hibát jelezünk
            model.addAttribute("error", "Hibás felhasználónév vagy jelszó!");
            return "login";  // Hibás bejelentkezés esetén újra a login oldalra irányítunk
        }

        // Ha a bejelentkezés sikeres, beállítjuk a felhasználót a session-ben
        session.setAttribute("user", felhasznalo);

        return "redirect:/bajnoksagok";  // Bejelentkezés után átirányítjuk a kezdőlapra
    }

    // Kijelentkezés kezelése
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Kijelentkezéskor töröljük a felhasználói adatokat a session-ből
        session.removeAttribute("user");
        return "redirect:/login";  // Kijelentkezés után visszairányítjuk a bejelentkezési oldalra
    }

    // Minden oldal előtt hozzáadjuk a bejelentkezett felhasználót a modellhez
    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        Felhasznalo felhasznalo = (Felhasznalo) session.getAttribute("user");
        model.addAttribute("user", felhasznalo);
    }
}
