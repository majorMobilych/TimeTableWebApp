package com.web.app.controller;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.UserDTO;
import com.web.app.repository.AgendaRepository;
import com.web.app.repository.UserRepository;
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
    public UserDTO logIn(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.toString());
        return userRepository.checkUser(userDTO.getLogin(), userDTO.getPassword());
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody UserDTO userDTO) throws EmailException {
        String userPassword = userService.sendPassword(userDTO.getLogin());
        userDTO.setPassword(userPassword);
        userRepository.saveUser(userDTO);
        log.debug("USER SUCCESSFULLY SAVED TO DATABASE");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/homeWithAgenda")
    public ModelAndView getWeekAgendas(@RequestParam String userName) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<AgendaEntity> agenda = agendaRepository.getUsersAgenda(userName);
        modelAndView.addObject("agenda", agenda);
        return modelAndView;
    }
/*
    //TODO: КАК УМНЕЕ ОБРАБОТАТЬ ВСЕ ЭКСЕПШЕНЫ? А-ЛЯ БУДЕТ ЕНАМ(\АРЕЙЛИСТ) С ЛОГАМИ, ОТКУДА ОНИ БУДУТ БРАТЬСЯ И
    // ВСТАВЛЯТЬСЯ В ЛОГ
    @ExceptionHandler(NotExistingUserException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "NO SUCH USER FOUND")
    public void handleNotExistingUserException() {
        //TODO: ЭТО НОРМАЛЬНЫЙ УРОВЕНЬ ЛОГГИРОВАНИЯ ДЛЯ ЭКСЕПШЕНА?
        log.error("NO SUCH USER WAS FOUND");
    }*/

/*    //TODO: ТЕ ЖЕ ВОПРОСЫ
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "INCORRECT PASSWORD")
    public void handleIncorrectPasswordException() {
        log.error("INCORRECT PASSWORD");
    }*/
}
