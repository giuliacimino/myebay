package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.service.CategoriaService;


@Controller
@RequestMapping(value = "/public")
public class HomeController {

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(value = { "/home", "" })
	public String home() {
			return "public/index";
		}
	}

