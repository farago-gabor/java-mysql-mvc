package hu.adatbazis.egyenisport.controller;

import hu.adatbazis.egyenisport.model.Felhasznalo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AlapController {
/*
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }



    @GetMapping("/merkozesek")
    public String showMerkozesekPage(){
        return "merkozesek";
    }
*/
    @GetMapping("/home")
    public String redirect(){
        return "redirect:/bajnoksagok";
    }
/*
    @GetMapping("/bajnoksagok")
    public String showBajnoksagokPage(){
        return "bajnoksagok";
    }
*/
    @GetMapping("/reszvetel")
    public String showReszvetelPage(){
        return "reszvetel";
    }
/*
    @GetMapping("/versenyzok")
    public String showVersenyzokPage(){
        return "versenyzok";
    }
*/
    @GetMapping("/")
    public String fooldal(){
        return "redirect:/bajnoksagok";
    }

    // Minden oldal előtt hozzáadjuk a bejelentkezett felhasználót a modellhez
    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        Felhasznalo felhasznalo = (Felhasznalo) session.getAttribute("user");
        model.addAttribute("user", felhasznalo);
    }

}
