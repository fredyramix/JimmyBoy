package com.web.chon.service;

import com.web.chon.dominio.AbonoCredito;
import com.web.chon.negocio.NegocioAbonoCredito;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan de la Cruz
 */
public class ServiceAbonoCredito implements IfaceAbonoCredito {

    NegocioAbonoCredito ejb;

    private void getEjb() {
        if (ejb == null) {
            try {
                ejb = (NegocioAbonoCredito) Utilidades.getEJBRemote("ejbAbonoCredito", NegocioAbonoCredito.class.getName());
            } catch (Exception ex) {
                Logger.getLogger(ServiceAbonoCredito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int update(AbonoCredito abonoCredito) {
        getEjb();
        return ejb.update(abonoCredito);
    }

    @Override
    public int insert(AbonoCredito abonoCredito) {
        getEjb();
        return ejb.insert(abonoCredito);
    }

    @Override
    public int delete(BigDecimal idAbonoCredito) {
        getEjb();
        return ejb.delete(idAbonoCredito);
    }

    @Override
    public ArrayList<AbonoCredito> getAll() {
        getEjb();
        ArrayList<AbonoCredito> lstAbonoCredito = new ArrayList<AbonoCredito>();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        for (Object[] object : lstObject) {
            AbonoCredito abonoCredito = new AbonoCredito();

            abonoCredito.setIdAbonoCreditoPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            abonoCredito.setIdCreditoFk(object[1] == null ? null : new BigDecimal(object[1].toString()));
            abonoCredito.setMontoAbono(object[2] == null ? null : new BigDecimal(object[2].toString()));
            abonoCredito.setFechaAbono(object[3] == null ? null : (Date) object[3]);
            abonoCredito.setIdUsuarioFk(object[4] == null ? null : new BigDecimal(object[4].toString()));

            lstAbonoCredito.add(abonoCredito);
        }

        return lstAbonoCredito;

    }

    @Override
    public AbonoCredito getById(BigDecimal idAbonoCredito) {
        getEjb();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getAll();
        AbonoCredito abonoCredito = new AbonoCredito();
        for (Object[] object : lstObject) {

            abonoCredito.setIdAbonoCreditoPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            abonoCredito.setIdCreditoFk(object[1] == null ? null : new BigDecimal(object[1].toString()));
            abonoCredito.setMontoAbono(object[2] == null ? null : new BigDecimal(object[2].toString()));
            abonoCredito.setFechaAbono(object[3] == null ? null : (Date) object[3]);
            abonoCredito.setIdUsuarioFk(object[4] == null ? null : new BigDecimal(object[4].toString()));

        }

        return abonoCredito;
    }

}
