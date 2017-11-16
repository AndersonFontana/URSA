/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import BANCO.DaoBanco;
import com.google.gson.Gson;
import dominio.Oportunidade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author luis
 */
@Path("oportunidade")
public class oportunidade {

    @Context
    private UriInfo context;
    
    @EJB
    DaoBanco dao = new DaoBanco();

    /**
     * Creates a new instance of oportunidade
     */
    public oportunidade() {
       
    }

    /**
     * Retrieves representation of an instance of servico.oportunidade
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consulta/{codop}")
    public String getJson(@PathParam("codop") int codop)  {
      //  DaoBanco dao = new DaoBanco();
        Gson g = new Gson();
        
        Oportunidade op = new Oportunidade();
        try {
            op = dao.consultar(codop);
        } catch (Exception ex) {
            
            return  g.toJson(ex.getMessage());
        }
       
        return g.toJson(op);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaop/{carg}")
    public String getlistaop (@PathParam("carg")int carg ) throws Exception
    {
        List <Oportunidade> op = dao.listaOportunidades(carg);
        Gson g = new Gson();
        return g.toJson(op);   
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaop/{test}")
    public String getlistaaberta (@PathParam("test")String test ) throws Exception
    {   
        List <Oportunidade> op ;
        if(test=="")
        op = dao.listaAbertas();
        else           
        op = dao.listaAbertas(Integer.parseInt(test));
        Gson g = new Gson();
        return g.toJson(op);   
        
    }
    
     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("incluir/adicionar")
    public String postJson(String json) {
        //TODO return proper representation object
        Oportunidade op = new Oportunidade();

                
      //  dao = new DaoBanco();
        Gson g = new Gson();
        op = g.fromJson(json, Oportunidade.class);
        System.out.println(op.toString());
        
        try {
            dao.adicionar(op);
        } catch (Exception ex) {
            return g.toJson(ex.getMessage());
        }
        
        return  op.toString();
    }

    /**
     * PUT method for updating or creating an instance of oportunidade
     * @param content representation for the resource
     */
     
    @DELETE
    @Path("deletar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void exclui (@PathParam("id" )int id ) throws Exception{
        dao.excluir(id);
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
