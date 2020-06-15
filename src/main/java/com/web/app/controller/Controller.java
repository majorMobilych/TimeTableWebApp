package com.web.app.controller;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private AgendaRepository agendaRepository;

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
        System.out.println(usersDTO.toString());
        return userRepository.checkUser(usersDTO.getLogin(), usersDTO.getPassword());
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody UsersDTO usersDTO) throws EmailException {
        String userPassword = userService.sendPassword(usersDTO.getLogin());
        usersDTO.setPassword(userPassword);
        userRepository.saveUser(usersDTO);
        log.debug("USER SUCCESSFULLY SAVED TO DATABASE");
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
}
