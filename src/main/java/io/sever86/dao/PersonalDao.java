package io.sever86.dao;

import io.sever86.domain.Persona;

import java.util.List;

/**
 * Created by home on 13.01.2017.
 */
public interface PersonalDao {

    public void addPersonal(Persona persona);

    public List<Persona> loadPersonalInf();

}
