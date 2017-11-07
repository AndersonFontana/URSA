
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AlteraResponse_QNAME = new QName("http://WebServices/", "alteraResponse");
    private final static QName _ConsultaResponse_QNAME = new QName("http://WebServices/", "consultaResponse");
    private final static QName _ListaAbResponse_QNAME = new QName("http://WebServices/", "lista_abResponse");
    private final static QName _Exclui_QNAME = new QName("http://WebServices/", "exclui");
    private final static QName _AdicionaResponse_QNAME = new QName("http://WebServices/", "adicionaResponse");
    private final static QName _Altera_QNAME = new QName("http://WebServices/", "altera");
    private final static QName _Adiciona_QNAME = new QName("http://WebServices/", "adiciona");
    private final static QName _ListaAb_QNAME = new QName("http://WebServices/", "lista_ab");
    private final static QName _ListaOp_QNAME = new QName("http://WebServices/", "lista_op");
    private final static QName _Consulta_QNAME = new QName("http://WebServices/", "consulta");
    private final static QName _ExcluiResponse_QNAME = new QName("http://WebServices/", "excluiResponse");
    private final static QName _ListaOpResponse_QNAME = new QName("http://WebServices/", "lista_opResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListaAbResponse }
     * 
     */
    public ListaAbResponse createListaAbResponse() {
        return new ListaAbResponse();
    }

    /**
     * Create an instance of {@link AlteraResponse }
     * 
     */
    public AlteraResponse createAlteraResponse() {
        return new AlteraResponse();
    }

    /**
     * Create an instance of {@link ConsultaResponse }
     * 
     */
    public ConsultaResponse createConsultaResponse() {
        return new ConsultaResponse();
    }

    /**
     * Create an instance of {@link Exclui }
     * 
     */
    public Exclui createExclui() {
        return new Exclui();
    }

    /**
     * Create an instance of {@link Adiciona }
     * 
     */
    public Adiciona createAdiciona() {
        return new Adiciona();
    }

    /**
     * Create an instance of {@link AdicionaResponse }
     * 
     */
    public AdicionaResponse createAdicionaResponse() {
        return new AdicionaResponse();
    }

    /**
     * Create an instance of {@link Altera }
     * 
     */
    public Altera createAltera() {
        return new Altera();
    }

    /**
     * Create an instance of {@link ListaAb }
     * 
     */
    public ListaAb createListaAb() {
        return new ListaAb();
    }

    /**
     * Create an instance of {@link ListaOp }
     * 
     */
    public ListaOp createListaOp() {
        return new ListaOp();
    }

    /**
     * Create an instance of {@link ExcluiResponse }
     * 
     */
    public ExcluiResponse createExcluiResponse() {
        return new ExcluiResponse();
    }

    /**
     * Create an instance of {@link Consulta }
     * 
     */
    public Consulta createConsulta() {
        return new Consulta();
    }

    /**
     * Create an instance of {@link ListaOpResponse }
     * 
     */
    public ListaOpResponse createListaOpResponse() {
        return new ListaOpResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlteraResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "alteraResponse")
    public JAXBElement<AlteraResponse> createAlteraResponse(AlteraResponse value) {
        return new JAXBElement<AlteraResponse>(_AlteraResponse_QNAME, AlteraResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "consultaResponse")
    public JAXBElement<ConsultaResponse> createConsultaResponse(ConsultaResponse value) {
        return new JAXBElement<ConsultaResponse>(_ConsultaResponse_QNAME, ConsultaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaAbResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "lista_abResponse")
    public JAXBElement<ListaAbResponse> createListaAbResponse(ListaAbResponse value) {
        return new JAXBElement<ListaAbResponse>(_ListaAbResponse_QNAME, ListaAbResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exclui }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "exclui")
    public JAXBElement<Exclui> createExclui(Exclui value) {
        return new JAXBElement<Exclui>(_Exclui_QNAME, Exclui.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdicionaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "adicionaResponse")
    public JAXBElement<AdicionaResponse> createAdicionaResponse(AdicionaResponse value) {
        return new JAXBElement<AdicionaResponse>(_AdicionaResponse_QNAME, AdicionaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Altera }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "altera")
    public JAXBElement<Altera> createAltera(Altera value) {
        return new JAXBElement<Altera>(_Altera_QNAME, Altera.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Adiciona }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "adiciona")
    public JAXBElement<Adiciona> createAdiciona(Adiciona value) {
        return new JAXBElement<Adiciona>(_Adiciona_QNAME, Adiciona.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaAb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "lista_ab")
    public JAXBElement<ListaAb> createListaAb(ListaAb value) {
        return new JAXBElement<ListaAb>(_ListaAb_QNAME, ListaAb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "lista_op")
    public JAXBElement<ListaOp> createListaOp(ListaOp value) {
        return new JAXBElement<ListaOp>(_ListaOp_QNAME, ListaOp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consulta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "consulta")
    public JAXBElement<Consulta> createConsulta(Consulta value) {
        return new JAXBElement<Consulta>(_Consulta_QNAME, Consulta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExcluiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "excluiResponse")
    public JAXBElement<ExcluiResponse> createExcluiResponse(ExcluiResponse value) {
        return new JAXBElement<ExcluiResponse>(_ExcluiResponse_QNAME, ExcluiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "lista_opResponse")
    public JAXBElement<ListaOpResponse> createListaOpResponse(ListaOpResponse value) {
        return new JAXBElement<ListaOpResponse>(_ListaOpResponse_QNAME, ListaOpResponse.class, null, value);
    }

}
