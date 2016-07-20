/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.RegistroEntradaSalida;
import com.web.chon.negocio.NegocioRegEntSal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcogante
 */
@Stateless(mappedName = "ejbRegEntSal")
public class EjbRegEntSal implements NegocioRegEntSal
{
    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getUsuarioByIdUsuario(BigDecimal idUsuarioFK, String fechaHoy) {
      try {

            Query query = em.createNativeQuery("select reg.*,usu.NOMBRE_USUARIO,usu.APATERNO_USUARIO,usu.AMATERNO_USUARIO from REGISTROENTRADA reg inner join USUARIO usu\n" +
"on usu.ID_USUARIO_PK = reg.ID_USUARIO_FK where ID_USUARIO_FK = ? and TO_DATE(TO_CHAR(reg.FECHAENTRADA,'dd/mm/yyyy'),'dd/mm/yyyy') BETWEEN '"+fechaHoy +"' AND '"+fechaHoy +"'");
            query.setParameter(1, idUsuarioFK);

            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbRegEntSal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int updateSalidabyIdReg(RegistroEntradaSalida data) {
       Query query = em.createNativeQuery("UPDATE REGISTROENTRADA set FECHASALIDA =sysdate ,LATITUDSALIDA=?,LONGITUDSALIDA=? WHERE ID_REGENT_PK=?");
        query.setParameter(1, data.getLatitudSalida());
        query.setParameter(2, data.getLongitudSalida());
        query.setParameter(3, data.getIdRegEntSalPk());
        return query.executeUpdate();   
    }

    @Override
    public int insertEntradabyIdReg(RegistroEntradaSalida data) {
     Query query = em.createNativeQuery("INSERT INTO REGISTROENTRADA(ID_REGENT_PK,FECHAENTRADA,LATITUDENTRADA,LONGITUDENTRADA,ID_USUARIO_FK,ID_SUCURSAL_FK,LATITUDSALIDA,LONGITUDSALIDA) VALUES(?,sysdate,?,?,?,?,?,?)");
        query.setParameter(1, data.getIdRegEntSalPk());
        query.setParameter(2, data.getLatitudEntrada());
        query.setParameter(3, data.getLongitudEntrada());
        query.setParameter(4, data.getIdUsuarioFk());
        query.setParameter(5, data.getIdSucursalFk());
        query.setParameter(6, data.getLatitudSalida());
        query.setParameter(7, data.getLongitudSalida());
        
        return query.executeUpdate();   
    
    }

    @Override
    public int getNextVal() {
        Query query = em.createNativeQuery("SELECT S_REGISTROENTRADA.nextVal FROM DUAL");
        return Integer.parseInt(query.getSingleResult().toString());
    
    }

    @Override
    public List<Object[]> getRegistros(BigDecimal idUsuarioFK, String fechaInicio, String fechaFin) {
        System.out.println("EJB: idUser_ "+idUsuarioFK+" Fecha Inicio: "+fechaInicio+" Fecha Fin: "+fechaFin);
        try {

            Query query = em.createNativeQuery("select reg.*,usu.NOMBRE_USUARIO,usu.APATERNO_USUARIO,usu.AMATERNO_USUARIO from REGISTROENTRADA reg inner join USUARIO usu\n" +
"on usu.ID_USUARIO_PK = reg.ID_USUARIO_FK where ID_USUARIO_FK = ? and  TO_DATE(TO_CHAR(reg.FECHAENTRADA,'dd/mm/yyyy'),'dd/mm/yyyy') BETWEEN '"+fechaInicio +"' AND '"+fechaFin+"'");
            query.setParameter(1, idUsuarioFK);
            System.out.println("query: "+query);
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbRegEntSal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Object[]> getALL(String fechaInicio, String fechaFin) {
      try {

            Query query = em.createNativeQuery("select reg.*,usu.NOMBRE_USUARIO,usu.APATERNO_USUARIO,usu.AMATERNO_USUARIO from REGISTROENTRADA reg inner join USUARIO usu\n" +
"on usu.ID_USUARIO_PK = reg.ID_USUARIO_FK where TO_DATE(TO_CHAR(reg.FECHAENTRADA,'dd/mm/yyyy'),'dd/mm/yyyy') BETWEEN '"+fechaInicio +"' AND '"+fechaFin+"'");
            System.out.println("query: "+query);
            return query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(EjbRegEntSal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
}
