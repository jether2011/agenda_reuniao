/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.ws;

import br.unisal.dao.SalaDao;
import br.unisal.model.Sala;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Lazaro
 */
@Path("sala")
public class SalaResource implements Serializable {

    @Context
    private UriInfo context;
    private SalaDao dao;

    /**
     * Creates a new instance of PessoaResource
     */
    public SalaResource() {
    }
    
    /**
     * Retrieves representation of an instance of br.unisal.ws.SalaResource
     * @param v1
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getSala(@PathParam("id") Integer id) {
        Sala p = getDao().getById(id);
        GenericEntity<Sala> sala = new GenericEntity<Sala>(getDao().getById(id)){};       
        return Response
                .ok(sala)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
    }
    
    /**
     * Retrieves representation of an instance of br.unisal.ws.SalaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getSalas() {
        GenericEntity<List<Sala>> salas = new GenericEntity<List<Sala>>(getDao().getAll()){}; 
        return Response
                .ok(salas)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
    }
    
    /**
     * Retrieves representation of an instance of br.unisal.ws.SalaResource
     * @param nome
     * @param email
     * @return an instance of java.lang.String
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteSala(@PathParam("id") Integer id) {
        getDao().remove(new Sala(id));
        String msg = "{\"msg\": \"Cadastro removido com sucesso!\"}";
        return Response
                .ok(msg)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
    }

    /**
     * PUT method for updating or creating an instance of SalaResource
     * @param p representation for the resource
     * @param id
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSala(Sala e, @PathParam("id")Integer id) {
        e.setIdSala(id);
        getDao().update(e);
        String msg = "{\"msg\":\"Atualização realizada com sucesso!\"}";
        return Response
                .ok(msg)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
    }
    
    /**
     * POST method for creating an instance of SalaResource
     * 
     * @param p
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSala(Sala e) {        
        getDao().insert(e);
        String msg = "{\"msg\":\"Inserção realizada com sucesso!\"}";
        return Response
                .ok(msg)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
    }
    
    public SalaDao getDao() {
        if(dao == null){
            dao = new SalaDao();
        }
        return dao;
    }
}
