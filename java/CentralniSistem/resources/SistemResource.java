package com.mycompany.centralnisistemnew.resources;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("komunikacija")
public class SistemResource {
    
    @Resource(lookup="myConnFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup="QueuePodsistem1")
    private Queue queuePodsistem1;
    
    @Resource(lookup="QueueCentralni1")
    private Queue QueueCentralni1;
    
    @Resource(lookup="QueuePodsistem2")
    private Queue queuePodsistem2;
    
    @Resource(lookup="QueueCentralni2")
    private Queue QueueCentralni2;

    
    private Response prosledi(String zahtev, Queue outputQueue, Queue inputQueue){
        try (JMSContext context = connectionFactory.createContext()) {

            TextMessage message = context.createTextMessage(zahtev);
            context.createProducer().send(outputQueue, message);
            
            JMSConsumer consumer = context.createConsumer(inputQueue);
            Message msg = consumer.receive(5000);

            if(msg!=null){
                Boolean uspeh = (Boolean) ((ObjectMessage) msg).getObject();
                return Response.ok(uspeh).build();
            }
            return Response.status(Response.Status.GATEWAY_TIMEOUT).entity("{\"error\":\"Podsistem nije odgovorio\"}").build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("{\"error\":\"Greska na serveru\"}").build();
        }
    }
    
    private Response proslediInt(String zahtev, Queue outputQueue, Queue inputQueue){
        try (JMSContext context = connectionFactory.createContext()) {

            TextMessage message = context.createTextMessage(zahtev);
            context.createProducer().send(outputQueue, message);
            
            JMSConsumer consumer = context.createConsumer(inputQueue);
            Message msg = consumer.receive(5000);

            if(msg!=null){
                Integer uspeh = (Integer) ((ObjectMessage) msg).getObject();
                return Response.ok(uspeh).build();
            }
            return Response.status(Response.Status.GATEWAY_TIMEOUT).entity("{\"error\":\"Podsistem nije odgovorio\"}").build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("{\"error\":\"Greska na serveru\"}").build();
        }
    }
    
    private Response proslediList(String zahtev, Queue outputQueue, Queue inputQueue){
        try (JMSContext context = connectionFactory.createContext()) {

            TextMessage message = context.createTextMessage(zahtev);
            context.createProducer().send(outputQueue, message);
            
            JMSConsumer consumer = context.createConsumer(inputQueue);
            Message msg = consumer.receive(5000);

            if(msg!=null){
                ArrayList<String> uspeh = (ArrayList<String>) ((ObjectMessage) msg).getObject();
                return Response.ok(uspeh).build();
            }
            return Response.status(Response.Status.GATEWAY_TIMEOUT).entity("{\"error\":\"Podsistem nije odgovorio\"}").build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("{\"error\":\"Greska na serveru\"}").build();
        }
    }
    
    @POST
    @Path("provera-postojanja-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response proveraPostojanjaKorisnika(String zahtev){
        return proslediInt(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    
    @POST
    @Path("kreiranje-grada")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kreiranjeGrada(String zahtev){
        return prosledi(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    @POST
    @Path("kreiranje-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kreiranjeKorisnika(String zahtev){
        return prosledi(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    @POST
    @Path("dodavanje-novca-korisniku")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodavanjeNovcaKorisniku(String zahtev){
        return prosledi(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    @POST
    @Path("promena-adrese-i-grada")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promenaAdreseIGrada(String zahtev){
        return prosledi(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    @POST
    @Path("dohvatanje-svih-gradova")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeSvihGradova(String zahtev){
        return proslediList(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    @POST
    @Path("dohvatanje-svih-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeSvihKorisnika(String zahtev){
        return proslediList(zahtev,queuePodsistem1,QueueCentralni1);
    }
    
    //Podsistem 2
    @POST
    @Path("kreiranje-kategorije")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kreiranjeKategorije(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/kreiranje-artikla")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response kreiranjeArtikla(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/menjanje-cene-artikla")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response menjanjeCeneArtikla(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/postavljanje-popusta-artikla")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postavljanjePopustaArtikla(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dodavanje-artikla-u-korpu")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodavanjeArtiklaUKorpu(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/brisanje-artikla-iz-korpe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response brisanjeArtiklaIzKorpe(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dodavanje-artikla-u-listu-zelja")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodavanjeArtiklaUListuZelja(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/brisanje-artikla-iz-liste-zelja")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response brisanjeArtiklaIzListeZelja(String zahtev){
        return prosledi(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dohvatanje-svih-kategorija")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeSvihKategorija(String zahtev){
        return proslediList(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dohvatanje-prodajnih-artikala-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeProdajnihArtikalaKorisnika(String zahtev){
        return proslediList(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dohvatanje-sadrzaja-korpe-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeSadrzajaKorpeKorisnika(String zahtev){
        return proslediList(zahtev,queuePodsistem2,QueueCentralni2);
    }
    
    @POST
    @Path("komunikacija/dohvatanje-liste-zelja-korisnika")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dohvatanjeListeZeljaKorisnika(String zahtev){
        return proslediList(zahtev,queuePodsistem2,QueueCentralni2);
    }

    
}
