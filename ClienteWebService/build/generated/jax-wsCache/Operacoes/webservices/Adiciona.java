
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de adiciona complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="adiciona">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adiciona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adiciona", propOrder = {
    "adiciona"
})
public class Adiciona {

    protected String adiciona;

    /**
     * Obtém o valor da propriedade adiciona.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdiciona() {
        return adiciona;
    }

    /**
     * Define o valor da propriedade adiciona.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdiciona(String value) {
        this.adiciona = value;
    }

}
