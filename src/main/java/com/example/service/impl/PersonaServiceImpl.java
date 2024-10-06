package com.example.service.impl;

import com.example.common.GenericServiceImpl;
import com.example.model.Persona;
import com.example.service.PersonaServiceAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements PersonaServiceAPI {

    @Inject
    EntityManager em;

    public PersonaServiceImpl() {
        super(Persona.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
