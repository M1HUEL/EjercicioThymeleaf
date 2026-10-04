package com.itson.EjercicioThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index(@RequestParam(name = "nombre", required = false, defaultValue = "Invitado") String nombre, Model model, HttpSession session) {
    model.addAttribute("mensajeBienvenida", "¡Hola Mundo desde Springboot!");
    model.addAttribute("nombreUsuario", nombre);

    session.setAttribute("operador", "Admin");

    return "index";
  }

  @GetMapping("/saludo")
  public String saludo(Model model) {
    LocalTime fecha = LocalTime.now();

    int hora = fecha.getHour();

    String saludo;
    String claseCss;

    if (hora >= 5 && hora < 12) {
      saludo = "¡Buenos días! Hora de programar!";
      claseCss = "color: #198754";
    } else if (hora >= 12 && hora < 19) {
      saludo = "¡Buenas tardes! Sigue así";
      claseCss = "color: #0d6efd";
    } else {
      saludo = "¡Buenas noches! A descansar";
      claseCss = "color: #dc3545";
    }

    model.addAttribute("saludo", saludo);
    model.addAttribute("claseCss", claseCss);

    return "saludo";
  }

  @GetMapping("/catalogo")
  public String catalogo(Model model) {
    List<Map<String, Object>> juegos = new ArrayList<>();

    juegos.add(crearJuego("The Legend of Zelda: Tears of the Kingdom", "Adventure", "Nintendo Switch", 584.48, true));
    juegos.add(crearJuego("Elden Ring", "RPG", "Multiplataforma", 384.34, true));
    juegos.add(crearJuego("Bloodborne", "Action RPG", "Playstation 4", 853.45, false));
    juegos.add(crearJuego("Cyberpunk 2077", "RPG", "PC / Consoals", 1285.32, true));

    model.addAttribute("juegos", juegos);

    return "catalogo";
  }

  private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, double precio, boolean disponible) {
    Map<String, Object> juego = new HashMap<>();

    juego.put("titulo", titulo);
    juego.put("genero", genero);
    juego.put("plataforma", plataforma);
    juego.put("precio", precio);
    juego.put("disponible", disponible);

    return juego;
  }
}
