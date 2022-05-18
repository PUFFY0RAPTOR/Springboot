package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.Models.Dao;

import java.util.List;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.Models.Entity.Cliente;


public interface IClienteDao { /*Esto es para crear la interfaz que nos mostrará la BD (O también un elemento de comunicacion con la BD)*/
    
    public List<Cliente> findAll();

    public void save(Cliente cliente);

}
