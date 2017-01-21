package io.sever86.dao;

import io.sever86.domain.Personal;

import java.util.List;

/**
 * Created by home on 13.01.2017.
 */
public interface PersonalDao {

     void addPersonal(Personal personal);

     List<Personal> loadPersonalInf();

}
