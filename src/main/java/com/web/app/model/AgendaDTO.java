package com.web.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//TODO НИЧЕГО ТУПЕЕ Я ЕЩЕ НЕ ПИСАЛ. ЗАЧЕМ ДТО, КОГДА ЭНТИТИ ТАКОЙ ЖЕ? НО ЭТИТИ Я НЕ МОГУ ИСПОЛЬЗОВАТЬ
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AgendaDTO {
    private int id;
    private String username;
    private String day;
    private String time;
    private String note;
}
