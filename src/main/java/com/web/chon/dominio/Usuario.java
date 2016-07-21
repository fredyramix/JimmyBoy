/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author marcogante
 */
public class Usuario extends Generic {

    private static final long serialVersionUID = 1L;
    private BigDecimal idUsuarioPk;
    private String nombreUsuario;
    private String apaternoUsuario;
    private String amaternoUsuario;
    private String contrasenaUsuario;
    private String confirmaUsuario;
    private Character sexoUsuario;
    private Long telefonoMovilUsuario;
    private Long telefonoFijoUsuario;
    private String idNextelUsuario;
    private String correoUsuario;
    private Short numeroInteriorUsuario;
    private Short numeroExterioUsuario;
    private String referenciaDirecionUsuario;
    private String calleUsuario;
    private String coloniaUsuario;
    private Short diasCreditoUsuario;
    private String rfcUsuario;
    private Long creditoLimiteUsuario;
    private String claveUsuario;
    private String sitioWeb;
    private String razonSocialUsuario;
    private Date fechaAltaUsuario;
    private String latitudUsuario;
    private String longitudUsuario;
    private Municipios idMunicipioFk;
    private BigDecimal idRolFk;
    private String nombreCompletoUsuario;
    private List<Venta> ventaList;
    private List<Venta> ventaList1;
    private List<Camion> camionList;
    private List<CuentaBancaria> cuentaBancariaList;
    private List<Sucursal> sucursalList;
    private int idSucursal;
    
    //Datos para el login y el menu
    private String perDescripcion;
    private Set<Menu> menu =  new HashSet<Menu>();
    private Set<String> allowedUrl =  new HashSet<String>();

    public Usuario() {
    }

    public Usuario(BigDecimal idUsuarioPk) {
        this.idUsuarioPk = idUsuarioPk;
    }

    public Usuario(BigDecimal idUsuarioPk, String nombreUsuario, String apaternoUsuario) {
        this.idUsuarioPk = idUsuarioPk;
        this.nombreUsuario = nombreUsuario;
        this.apaternoUsuario = apaternoUsuario;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    
    
    public BigDecimal getIdUsuarioPk() {
        return idUsuarioPk;
    }

    public void setIdUsuarioPk(BigDecimal idUsuarioPk) {
        this.idUsuarioPk = idUsuarioPk;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApaternoUsuario() {
        return apaternoUsuario;
    }

    public void setApaternoUsuario(String apaternoUsuario) {
        this.apaternoUsuario = apaternoUsuario;
    }

    public String getAmaternoUsuario() {
        return amaternoUsuario;
    }

    public void setAmaternoUsuario(String amaternoUsuario) {
        this.amaternoUsuario = amaternoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getConfirmaUsuario() {
        return confirmaUsuario;
    }

    public void setConfirmaUsuario(String confirmaUsuario) {
        this.confirmaUsuario = confirmaUsuario;
    }

    public Character getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(Character sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public Long getTelefonoMovilUsuario() {
        return telefonoMovilUsuario;
    }

    public void setTelefonoMovilUsuario(Long telefonoMovilUsuario) {
        this.telefonoMovilUsuario = telefonoMovilUsuario;
    }

    public Long getTelefonoFijoUsuario() {
        return telefonoFijoUsuario;
    }

    public void setTelefonoFijoUsuario(Long telefonoFijoUsuario) {
        this.telefonoFijoUsuario = telefonoFijoUsuario;
    }

    public String getIdNextelUsuario() {
        return idNextelUsuario;
    }

    public void setIdNextelUsuario(String idNextelUsuario) {
        this.idNextelUsuario = idNextelUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Short getNumeroInteriorUsuario() {
        return numeroInteriorUsuario;
    }

    public void setNumeroInteriorUsuario(Short numeroInteriorUsuario) {
        this.numeroInteriorUsuario = numeroInteriorUsuario;
    }

    public Short getNumeroExterioUsuario() {
        return numeroExterioUsuario;
    }

    public void setNumeroExterioUsuario(Short numeroExterioUsuario) {
        this.numeroExterioUsuario = numeroExterioUsuario;
    }

    public String getReferenciaDirecionUsuario() {
        return referenciaDirecionUsuario;
    }

    public void setReferenciaDirecionUsuario(String referenciaDirecionUsuario) {
        this.referenciaDirecionUsuario = referenciaDirecionUsuario;
    }

    public String getCalleUsuario() {
        return calleUsuario;
    }

    public void setCalleUsuario(String calleUsuario) {
        this.calleUsuario = calleUsuario;
    }

    public String getColoniaUsuario() {
        return coloniaUsuario;
    }

    public void setColoniaUsuario(String coloniaUsuario) {
        this.coloniaUsuario = coloniaUsuario;
    }

    public Short getDiasCreditoUsuario() {
        return diasCreditoUsuario;
    }

    public void setDiasCreditoUsuario(Short diasCreditoUsuario) {
        this.diasCreditoUsuario = diasCreditoUsuario;
    }

    public String getRfcUsuario() {
        return rfcUsuario;
    }

    public void setRfcUsuario(String rfcUsuario) {
        this.rfcUsuario = rfcUsuario;
    }

    public Long getCreditoLimiteUsuario() {
        return creditoLimiteUsuario;
    }

    public void setCreditoLimiteUsuario(Long creditoLimiteUsuario) {
        this.creditoLimiteUsuario = creditoLimiteUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getRazonSocialUsuario() {
        return razonSocialUsuario;
    }

    public void setRazonSocialUsuario(String razonSocialUsuario) {
        this.razonSocialUsuario = razonSocialUsuario;
    }

    public Date getFechaAltaUsuario() {
        return fechaAltaUsuario;
    }

    public void setFechaAltaUsuario(Date fechaAltaUsuario) {
        this.fechaAltaUsuario = fechaAltaUsuario;
    }

    public String getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(String latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public String getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(String longitudUsuario) {
        this.longitudUsuario = longitudUsuario;
    }

    public Municipios getIdMunicipioFk() {
        return idMunicipioFk;
    }

    public void setIdMunicipioFk(Municipios idMunicipioFk) {
        this.idMunicipioFk = idMunicipioFk;
    }

    public BigDecimal getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(BigDecimal idRolFk) {
        this.idRolFk = idRolFk;
    }

    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public List<Venta> getVentaList1() {
        return ventaList1;
    }

    public void setVentaList1(List<Venta> ventaList1) {
        this.ventaList1 = ventaList1;
    }

    public List<Camion> getCamionList() {
        return camionList;
    }

    public void setCamionList(List<Camion> camionList) {
        this.camionList = camionList;
    }

    public List<CuentaBancaria> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancaria> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public String getNombreCompletoUsuario() {
        return nombreUsuario == null ? "":nombreUsuario+" "+(apaternoUsuario == null ? "":apaternoUsuario+" "+(amaternoUsuario == null ? "":" "+amaternoUsuario));
    }

    public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
        this.nombreCompletoUsuario = nombreCompletoUsuario;
    }

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }

    public Set<String> getAllowedUrl() {
        return allowedUrl;
    }

    public void setAllowedUrl(Set<String> allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    public String getPerDescripcion() {
        return perDescripcion;
    }

    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioPk != null ? idUsuarioPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuarioPk == null && other.idUsuarioPk != null) || (this.idUsuarioPk != null && !this.idUsuarioPk.equals(other.idUsuarioPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuarioPk=" + idUsuarioPk + ", nombreUsuario=" + nombreUsuario + ", apaternoUsuario=" + apaternoUsuario + ", amaternoUsuario=" + amaternoUsuario + ", contrasenaUsuario=" + contrasenaUsuario + ", confirmaUsuario=" + confirmaUsuario + ", sexoUsuario=" + sexoUsuario + ", telefonoMovilUsuario=" + telefonoMovilUsuario + ", telefonoFijoUsuario=" + telefonoFijoUsuario + ", idNextelUsuario=" + idNextelUsuario + ", correoUsuario=" + correoUsuario + ", numeroInteriorUsuario=" + numeroInteriorUsuario + ", numeroExterioUsuario=" + numeroExterioUsuario + ", referenciaDirecionUsuario=" + referenciaDirecionUsuario + ", calleUsuario=" + calleUsuario + ", coloniaUsuario=" + coloniaUsuario + ", diasCreditoUsuario=" + diasCreditoUsuario + ", rfcUsuario=" + rfcUsuario + ", creditoLimiteUsuario=" + creditoLimiteUsuario + ", claveUsuario=" + claveUsuario + ", sitioWeb=" + sitioWeb + ", razonSocialUsuario=" + razonSocialUsuario + ", fechaAltaUsuario=" + fechaAltaUsuario + ", latitudUsuario=" + latitudUsuario + ", longitudUsuario=" + longitudUsuario + ", idMunicipioFk=" + idMunicipioFk + ", idRolFk=" + idRolFk + ", ventaList=" + ventaList + ", ventaList1=" + ventaList1 + ", camionList=" + camionList + ", cuentaBancariaList=" + cuentaBancariaList + ", sucursalList=" + sucursalList + '}';
    }

    
}
