/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicosws;

import BANCO.DaoBanco;
import com.google.gson.Gson;
import dominio.Oportunidade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author pinot
 */
@Path("oportunidadesws")
public class oportunidadesws {

    @Context
    private UriInfo context;
    private  DaoBanco dao = new DaoBanco();
    private  Gson g = new Gson();

    /**
     * Creates a new instance of oportunidadesws
     */
    public oportunidadesws() {
    }

    /**
     * Retrieves representation of an instance of servicosws.oportunidadesws
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consulta/{id}")
    public  String consulta (@PathParam("id") int id ){
        Oportunidade op = null ;
        try {
             op  = dao.consultar(id);
//             op = new Oportunidade();
//             op.setAcesso(4);
//             op.setCodcargo(1);
//             op.setCodigo(22);
//             op.setDescricao("test rest erro");
//             op.setFechada(new Timestamp(System.currentTimeMillis())) ;
//             dao.adicionar(op)
        } catch (Exception ex) {
            return "nao foi possivel consultar";
        }
        return g.toJson(op);
}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaop/{carg}")
    public String getlistaop (@PathParam("carg")int carg ) throws Exception
    {
        List <Oportunidade> op = dao.listaOportunidades(carg);
        g = new Gson();
        return g.toJson(op);   
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaaberta/{test}")
    public String getlistaaberta (@PathParam("test")String test ) throws Exception
    {   
        List <Oportunidade> op ;
        if(test=="")
        op = dao.listaAbertas();
        else           
        op = dao.listaAbertas(Integer.parseInt(test));
        g = new Gson();
        return g.toJson(op);   
        
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("incluir/adicionar")
    public String add(String json) {
        //TODO return proper representation object
        Oportunidade op  = new Oportunidade();

                
        dao = new DaoBanco();
        g = new Gson();
        op = g.fromJson(json, Oportunidade.class);
           
        try {
            dao.adicionar(op);
        } catch (Exception ex) {
           return  "nao foi possivel adicionara";
        }
        return  "ok";
    }
    
    
    @PUT
    @Path("altera")
    @Consumes(MediaType.APPLICATION_JSON)
    public  String altera ( String js){
        g = new Gson();
        Oportunidade op = g.fromJson(js, Oportunidade.class);
        try {
            dao.alterar(op);
        } catch (Exception ex) {
           return ex.getMessage();
        }
        
        return "ok";
    
    }
    
    /**
     *
     * @param id
     */
 
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("oprtunidade/excluir/{id}")
//    public Response deleta (@PathParam("id") int id ){
//             
//        try {
//            dao.excluir(id);
//        } catch (Exception ex) {
//          //  return ex.getMessage();
//        } 
//        return Response.ok(id);
//     
//    }
    
    
    
    
    
    
    /**
     * PUT method for updating or creating an instance of oportunidadesws
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
