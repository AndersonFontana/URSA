
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de lista_op complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="lista_op">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lista_op" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lista_op", propOrder = {
    "listaOp"
})
public class ListaOp {

    @XmlElement(name = "lista_op")
    protected String listaOp;

    /**
     * Obtém o valor da propriedade listaOp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaOp() {
        return listaOp;
    }

    /**
     * Define o valor da propriedade listaOp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaOp(String value) {
        this.listaOp = value;
    }

}
