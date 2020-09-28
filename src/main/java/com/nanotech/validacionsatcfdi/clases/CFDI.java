/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanotech.validacionsatcfdi.clases;

/**
 *
 * @author nano
 */
public class CFDI {

    private String RFCemisor;
    private String RFCreceptor;
    private String monto;
    private String UUID;
    private String Existe;
    private String Estatus;

    public void setRFCemisor(String RFCemisor) {
        this.RFCemisor = RFCemisor;
    }

    public void setRFCreceptor(String RFCreceptor) {
        this.RFCreceptor = RFCreceptor;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
    
    public String getEstatus() {
        
        return Estatus;
        
    }
    
    public String getExiste(){
        verificarCFDI();
        return Existe;
    }
    
    public CFDI(){
        
    }
    
    public CFDI(String rfcE, String rfcR, String mon, String uuid){
        
        RFCemisor = rfcE;
        RFCreceptor = rfcR;
        monto = mon;
        UUID = uuid;
        
    }
    
    
    public void verificarCFDI(){
     
        String request = "re=" + RFCemisor + "&rr=" + RFCreceptor + "&tt=" + monto + "&id=" + UUID + "";
        
        
        try { // Call Web Service Operation
            org.tempuri.ConsultaCFDIService service = new org.tempuri.ConsultaCFDIService();
            org.tempuri.IConsultaCFDIService port = service.getBasicHttpBindingIConsultaCFDIService();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            org.datacontract.schemas._2004._07.sat_cfdi_negocio_consultacfdi.Acuse result = port.consulta(request);
            
            Existe = result.getCodigoEstatus().getValue();
            Estatus = result.getEstado().getValue();
            
        } catch (Exception ex) {
            Existe = "err";
        }

        
    }
    
}
