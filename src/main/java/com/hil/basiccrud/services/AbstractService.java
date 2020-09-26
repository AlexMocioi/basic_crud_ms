package com.hil.basiccrud.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ModelMapper mapper;
}
