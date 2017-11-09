
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de lista_ab complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="lista_ab">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lista_ab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lista_ab", propOrder = {
    "listaAb"
})
public class ListaAb {

    @XmlElement(name = "lista_ab")
    protected String listaAb;

    /**
     * Obtém o valor da propriedade listaAb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaAb() {
        return listaAb;
    }

    /**
     * Define o valor da propriedade listaAb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaAb(String value) {
        this.listaAb = value;
    }

}
