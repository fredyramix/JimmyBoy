/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;


import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freddy
 */
@Remote
public interface NegocioCatCodigosPostales 
{
      public List<Object[]> getCodigosByCP(String cp);
      public List<Object[]> getCodigosByIdMun(int idMun);
    
}
