package com.web.chon.negocio;

import com.web.chon.dominio.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author marcogante
 */
@Remote
public interface NegocioCatUsuario {

    public List<Object[]> getUsuarios();

    public List<Object[]> getMeseros();

    public List<Object[]> getUsuariosbyIdSucursal(int idSucursal);

    public List<Object[]> getUsuarioById(int idUsuario);

    public int deleteUsuario(int idUsuario);

    public int updateUsuario(Usuario usuario);

    public int insertUsuario(Usuario usuario);

    public int insertarUsuario(Usuario usuario);

    public List<Object[]> getUsuarioByNombreCompleto(String nombreUsuario, int idSucursal);

    public List<Object[]> getUsuarioByClave(String clave, int idSucursal);

}
