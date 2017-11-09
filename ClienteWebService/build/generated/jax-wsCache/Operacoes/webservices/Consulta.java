
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de consulta complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="consulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consulta", propOrder = {
    "consulta"
})
public class Consulta {

    protected String consulta;

    /**
     * Obtém o valor da propriedade consulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsulta() {
        return consulta;
    }

    /**
     * Define o valor da propriedade consulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsulta(String value) {
        this.consulta = value;
    }

}
