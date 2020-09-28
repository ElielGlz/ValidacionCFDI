/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanotech.validacionsatcfdi.clases;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author nano
 */
public class CFDIxml {
    
    private final String RutaXML;
    private String datoRFCemisor;
    private String datoRFCrecpetor;
    private String datoMonto;
    private String datoUUID;
    
    public CFDIxml(String rutaArchivo){
        
        RutaXML = rutaArchivo;
        
    }

    public String getDatoRFCemisor() {
        return datoRFCemisor;
    }

    public String getDatoRFCrecpetor() {
        return datoRFCrecpetor;
    }

    public String getDatoMonto() {
        return datoMonto;
    }

    public String getDatoUUID() {
        return datoUUID;
    }
    
    
    
    public void leerArchivoXML(){
        
        try{
            
            File archivoXML = new File(RutaXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document documento = docBuilder.parse(archivoXML);
            
            documento.getDocumentElement().normalize();
            
            Node nodoPadre = documento.getFirstChild();
            NamedNodeMap atributosNodoPadre = nodoPadre.getAttributes();
                      
            datoMonto = atributosNodoPadre.getNamedItem("Total").getNodeValue();
            
            NodeList listaNodos = nodoPadre.getChildNodes();
            for(int i = 0; i < listaNodos.getLength(); i++){
                
                Node nodohijo = listaNodos.item(i);
                
                NamedNodeMap atributosHijos = nodohijo.getAttributes();
                
                switch(nodohijo.getNodeName()){
                                      
                    case "cfdi:Emisor":
                        
                        datoRFCemisor = atributosHijos.getNamedItem("Rfc").getNodeValue();
                        
                        break;
                    case "cfdi:Receptor":
                        
                        datoRFCrecpetor = atributosHijos.getNamedItem("Rfc").getNodeValue();
                        
                        break;
                    
                }
                
                if("cfdi:Complemento".equals(nodohijo.getNodeName())){
                    
                    NodeList listaNodos2 = nodohijo.getChildNodes();
                    
                    for(int j = 0; j < listaNodos2.getLength(); j++){
                        
                        Node nodohijo2 = listaNodos2.item(j);
                        
                        if("tfd:TimbreFiscalDigital".equals(nodohijo2.getNodeName())){
                            
                            datoUUID = nodohijo2.getAttributes().getNamedItem("UUID").getNodeValue();
                            
                        }
                        
                    }
                    
                }
                
            }
            
        } catch(IOException | ParserConfigurationException | SAXException ex){
            
            
            
        }
        
    }
    
}
