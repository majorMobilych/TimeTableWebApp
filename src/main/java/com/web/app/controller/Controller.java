package com.web.app.controller;

import com.web.app.exceptions.UserAlreadyExistsException;
import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.AgendaDTO;
import com.web.app.model.UsersDTO;
import com.web.app.repository.AgendaRepository;
import com.web.app.repository.UserRepository;
import com.web.app.service.AgendaService;
import com.web.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
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

    @Autowired
    public Controller(UserService userService, UserRepository userRepository, AgendaService agendaService, AgendaRepository agendaRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.agendaService = agendaService;
        this.agendaRepository = agendaRepository;
    }

    @GetMapping("/")
    public ModelAndView redirectToHomePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/logInSignUp")
    public ModelAndView redirectToLogInSignUp() {
        return new ModelAndView("logInSignUp");
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    @ResponseBody
    public UsersDTO logIn(@RequestBody UsersDTO usersDTO) {
        return userRepository.checkUser(usersDTO.getLogin(), usersDTO.getPassword());
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

    //TODO: переписать, дто писать надо?
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseEntity del(@RequestParam int id) {
        agendaRepository.deleteUsersAgenda(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
