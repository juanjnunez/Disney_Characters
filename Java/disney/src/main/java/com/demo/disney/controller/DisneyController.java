package com.demo.disney.controller;

import com.demo.disney.DAO.JdbcDisneyDao;
import com.demo.disney.model.Disney;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class DisneyController {

    JdbcDisneyDao disneyDao;

    public DisneyController(JdbcDisneyDao disneyDao) {
        this.disneyDao = disneyDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/character", method = RequestMethod.POST)
    public Disney create(@RequestBody @Valid Disney disney) {
        if (disney == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Disney character Not Created");
        } else {
            return disneyDao.create(disney);
        }
    }
}
