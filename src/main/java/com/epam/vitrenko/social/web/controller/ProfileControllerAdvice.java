package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.service.NotFriendsException;
import com.epam.vitrenko.social.domain.service.UsernameAlreadyExistsException;
import com.epam.vitrenko.social.domain.service.UsernameNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ProfileControllerAdvice {

    @ExceptionHandler({UsernameAlreadyExistsException.class, NotFriendsException.class})
    public void handleExistingUsername(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UsernameNotExist.class)
    public void handleNotExistingUsername(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
