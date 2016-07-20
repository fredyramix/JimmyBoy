/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public interface IfaceCatUsuario {

    public ArrayList<Usuario> getUsuarios();
    
     public ArrayList<Usuario> getUsuariosbyIdSucursal(int idSucursal);

    public Usuario getUsuariosById(int idUsuario);

    public int deleteUsuarios(int idUsuario);

    public int updateUsuario(Usuario usuario);

    public int insertarUsuarios(Usuario usuario);

    public ArrayList<Usuario> getUsuarioByNombreCompleto(String nombre,int idSucursal);
    
    public Usuario getUsuarioByClave(String clave,int idSucursal);
    
    public ArrayList<Usuario> getMeseros();
}
