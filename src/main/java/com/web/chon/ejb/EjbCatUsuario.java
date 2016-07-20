package com.web.chon.ejb;

import com.web.chon.dominio.Usuario;
import com.web.chon.negocio.NegocioCatUsuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juan
 */
@Stateless(mappedName = "ejbCatUsuario")
public class EjbCatUsuario implements NegocioCatUsuario {

    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getUsuarios() {
        try {

            System.out.println("EJB_GET");

            Query query = em.createNativeQuery("SELECT * FROM USUARIO");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Object[]> getUsuarioById(int idUsuario) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM Usuario WHERE ID_USUARIO_PK = ?");
            query.setParameter(1, idUsuario);

            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int deleteUsuario(int idUsuario) {
        try {

            System.out.println("Id Usuario a eliminar: " + idUsuario);
            Query query = em.createNativeQuery("DELETE Usuario WHERE ID_USUARIO_PK = ?");
            query.setParameter(1, idUsuario);

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateUsuario(Usuario usuario) {

        try {

            System.out.println("Usuario a modificar :" + usuario.getNombreUsuario() + " desc " + usuario.getRfcUsuario() + " " + usuario.getIdUsuarioPk().intValue());
            Query query = em.createNativeQuery("UPDATE Usuario SET NOMBRE_USUARIO = ?, APATERNO_USUARIO = ?, AMATERNO_USUARIO = ?, "
                    + "CLAVE_USUARIO = ?, CONTRASENA_USUARIO = ?, RFC_USUARIO = ?, ID_ROL_FK = ?,ID_SUCURSAL_FK = ? WHERE ID_USUARIO_PK = ? ");
            query.setParameter(1, usuario.getNombreUsuario());
            query.setParameter(2, usuario.getApaternoUsuario());
            query.setParameter(3, usuario.getAmaternoUsuario());
            query.setParameter(4, usuario.getClaveUsuario());
            query.setParameter(5, usuario.getContrasenaUsuario());
            query.setParameter(6, usuario.getRfcUsuario());
            query.setParameter(7, usuario.getIdRolFk());
            query.setParameter(8, usuario.getIdSucursal() == -1 ? null : usuario.getIdSucursal());
            query.setParameter(9, usuario.getIdUsuarioPk());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int insertUsuario(Usuario usuario) {
        System.out.println("ejb");
        try {

            Query querySel = em.createNativeQuery("SELECT CLAVE_USUARIO FROM USUARIO WHERE CLAVE_USUARIO = '" + usuario.getClaveUsuario() + "' ");

            List<Object[]> resultList = null;
            resultList = querySel.getResultList();

            if (resultList.isEmpty()) {

                Query query = em.createNativeQuery("INSERT INTO USUARIO (ID_USUARIO_PK,NOMBRE_USUARIO, APATERNO_USUARIO, AMATERNO_USUARIO, "
                        + "CLAVE_USUARIO, CONTRASENA_USUARIO, RFC_USUARIO,ID_ROL_FK,ID_SUCURSAL_FK) VALUES (S_USUARIO.NextVal,?,?,?,?,?,?,?,?)");
                query.setParameter(1, usuario.getNombreUsuario());
                query.setParameter(2, usuario.getApaternoUsuario());
                query.setParameter(3, usuario.getAmaternoUsuario());
                query.setParameter(4, usuario.getClaveUsuario());
                query.setParameter(5, usuario.getContrasenaUsuario());
                query.setParameter(6, usuario.getRfcUsuario());
                query.setParameter(7, usuario.getIdRolFk());
                query.setParameter(8, usuario.getIdSucursal() == -1 ? null : usuario.getIdSucursal());

                return query.executeUpdate();

            } else {

                return 0;

            }

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int insertarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> getUsuarioByNombreCompleto(String nombreUsuario, int idSucursal) {
        Query query;

        //se comento  and ID_ROL_FK = 2 para filtro por rol vendedor TODO FIXME
        if (idSucursal == 0) {
            query = em.createNativeQuery("SELECT * FROM USUARIO WHERE UPPER(NOMBRE_USUARIO ||' '|| APATERNO_USUARIO ||' '|| AMATERNO_USUARIO )  LIKE UPPER('%" + nombreUsuario + "%') ");
        } else {
            query = em.createNativeQuery("SELECT * FROM USUARIO WHERE UPPER(NOMBRE_USUARIO ||' '|| APATERNO_USUARIO ||' '|| AMATERNO_USUARIO )  LIKE UPPER('%" + nombreUsuario + "%') and id_sucursal_fk = ?");
            query.setParameter(1, idSucursal);
        }

        return query.getResultList();
    }

    @Override
    public List<Object[]> getUsuarioByClave(String clave, int idSucursal) {
        try {
            Query query;

            if (idSucursal == 0) {
                query = em.createNativeQuery("SELECT * FROM Usuario WHERE TRIM(CLAVE_USUARIO) = ?");
                query.setParameter(1, clave.trim());
            } else {
                query = em.createNativeQuery("SELECT * FROM Usuario WHERE TRIM(CLAVE_USUARIO) = ? AND id_sucursal_fk = ?");
                query.setParameter(1, clave.trim());
                query.setParameter(2, idSucursal);
            }

            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Object[]> getUsuariosbyIdSucursal(int idSucursal) {

        try {

            System.out.println("EJB_GET");

            Query query = em.createNativeQuery("SELECT * FROM USUARIO where ID_SUCURSAL_FK = ?");
            query.setParameter(1, idSucursal);
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<Object[]> getMeseros() {
        try {

            System.out.println("EJB_GET_MESEROS");

            Query query = em.createNativeQuery("select u.ID_USUARIO_PK, u.NOMBRE_USUARIO,u.APATERNO_USUARIO,u.AMATERNO_USUARIO from usuario u where u.ID_ROL_FK = 3");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbCatUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

}
