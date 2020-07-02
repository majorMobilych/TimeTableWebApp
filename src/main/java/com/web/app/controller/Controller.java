package com.web.app.controller;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.AgendaDTO;
import com.web.app.model.UsersDTO;
import com.web.app.repository.AgendaRepository;
import com.web.app.repository.UserRepository;
import com.web.app.service.AgendaService;
import com.web.app.service.UserService;
import com.web.app.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AgendaService agendaService;
    private final AgendaRepository agendaRepository;
    private final UserValidator userValidator;


    @Autowired
    public Controller(UserService userService, UserRepository userRepository, AgendaService agendaService, AgendaRepository agendaRepository, UserValidator userValidator) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.agendaService = agendaService;
        this.agendaRepository = agendaRepository;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public ModelAndView redirectToHomePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/signUp")
    public ModelAndView redirectToSignUp() {
        return new ModelAndView("signUp");
    }

    @GetMapping("/logIn")
    public ModelAndView redirectToLogIn() {
        return new ModelAndView("logIn");
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody UsersDTO usersDTO) {
        userService.saveUserAndSendPassword(usersDTO.getLogin(), usersDTO.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/homeWithAgenda")
    public ModelAndView getWeekAgendas(@RequestParam String userName) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<AgendaEntity> agenda = agendaService.sortedUsersEntity(userName);
        modelAndView.addObject("agenda", agenda);
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody AgendaDTO agendaDTO) {
        agendaRepository.updateUsersAgenda(agendaDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody AgendaDTO agendaDTO) {
        agendaRepository.saveUsersAgenda(agendaDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseEntity del(@RequestParam int id) {
        System.out.println("id = " + id);
        agendaRepository.deleteUsersAgenda(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
