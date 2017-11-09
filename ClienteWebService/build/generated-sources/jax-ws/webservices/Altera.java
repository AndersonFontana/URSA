
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de altera complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="altera">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "altera", propOrder = {
    "altera"
})
public class Altera {

    protected String altera;

    /**
     * Obtém o valor da propriedade altera.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltera() {
        return altera;
    }

    /**
     * Define o valor da propriedade altera.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltera(String value) {
        this.altera = value;
    }

}
