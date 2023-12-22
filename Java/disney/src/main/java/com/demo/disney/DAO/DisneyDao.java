package com.demo.disney.DAO;

import com.demo.disney.model.Disney;

public interface DisneyDao {

    Disney create (Disney character);

    Disney getDisneyById(int disneyId);
}
