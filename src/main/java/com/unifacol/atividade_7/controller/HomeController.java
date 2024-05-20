package com.unifacol.atividade_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unifacol.atividade_7.model.User;
import com.unifacol.atividade_7.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	private static Long idUser; // ID do usuário atual
	private User user; // Aux

	// Mapeia a URL raiz ("/") para exibir a página inicial
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		return mv;
	}

	// Adiciona um novo usuário e exibe uma mensagem de sucesso
	@PostMapping("/addUser")
	public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("email") String email) {
		this.user = new User(null, name, email);
		userService.addUser(user);
		idUser = userService.searchByName(name);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("message", "Usuario adicionado com sucesso");
		mv.addObject("nomeUsuario", user.getName());
		mv.addObject("to_alter", "Deseja alterar o nome do usuario?");

		return mv;
	}

	// Recebe a escolha do usuário para alterar ou não os dados
	@PostMapping("/userChoice")
	public ModelAndView choiceUser(@RequestParam("choice") String response) {
		ModelAndView mv = new ModelAndView();
		this.user = this.userService.findById(idUser);

		if (response.equals("Sim")) {
			mv.setViewName("/home/to_alter");
			return mv;
		} else {
			mv.setViewName("/home/index");
			mv.addObject("message", "Usuario adicionado com sucesso");
			mv.addObject("nomeUsuario", user.getName());
			mv.addObject("to_alter", "Deseja alterar o nome do usuario?");
			mv.addObject("delete", "Deseja deletar este cadastro");

		}

		return mv;
	}

	// Atualiza os dados do usuário e exibe uma mensagem de sucesso
	@PostMapping("/userNew")
	public ModelAndView update(@RequestParam("newName") String novoNome, @RequestParam("newEmail") String novoEmail) {
		ModelAndView mv = new ModelAndView();
		this.user = userService.updateById(idUser, novoNome, novoEmail);

		mv.addObject("message", "Novo nome: " + this.user.getName() + "\nNovo email: " + this.user.getEmail());
		mv.addObject("message_2", "Nome e Email alterados com sucesso");
		mv.addObject("delete", "Deseja deletar este cadastro");
		mv.setViewName("home/to_alter");
		return mv;
	}

	// Recebe a escolha do usuário para deletar ou não o cadastro e executa a ação
	// correspondente
	@PostMapping("/userChoice_Del")
	public ModelAndView delete(@RequestParam("choice_del") String response_del) {
		ModelAndView mv = new ModelAndView();
		if (response_del.equals("Sim")) {
			userService.deleteById(idUser);
			mv.setViewName("/home/index");
			mv.addObject("to_alter", "Deseja alterar o nome do usuario?");
			mv.addObject("msg_delete", "Usuario deletado com sucesso");
			return mv;
		} else {
			mv.setViewName("/home/index");
			mv.addObject("nomeUsuario", user.getName());
			mv.addObject("to_alter", "Deseja alterar o nome do usuario?");
			mv.addObject("delete", "Deseja deletar este cadastro");
			return mv;
		}
	}
}
