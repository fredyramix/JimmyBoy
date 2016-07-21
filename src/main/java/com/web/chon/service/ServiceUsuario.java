/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Usuario;
import com.web.chon.negocio.NegocioLogin;
import com.web.chon.util.Utilidades;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class ServiceUsuario implements IfaceUsuario {
  NegocioLogin ejb;

    @Override
    public Usuario validarLogin(Usuario obj) throws Exception {
       ejb = (NegocioLogin) Utilidades.getEJBRemote("ejbUsuario", NegocioLogin.class.getName());
       return ejb.validarLogin(obj);

    }

}
