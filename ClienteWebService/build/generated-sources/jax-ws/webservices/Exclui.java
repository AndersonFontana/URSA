
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de exclui complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="exclui">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exclui" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exclui", propOrder = {
    "exclui"
})
public class Exclui {

    protected String exclui;

    /**
     * Obtém o valor da propriedade exclui.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExclui() {
        return exclui;
    }

    /**
     * Define o valor da propriedade exclui.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExclui(String value) {
        this.exclui = value;
    }

}
