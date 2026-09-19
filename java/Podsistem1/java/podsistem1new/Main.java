package podsistem1new;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entiteti.Korisnik;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;

public class Main {

    @Resource(lookup="myConnFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup="QueuePodsistem1")
    private static Queue QueuePodsistem1;
    
    @Resource(lookup="QueueCentralni1")
    private static Queue QueueCentralni1;
    
    public static void main(String[] args) {
        
        JMSContext context = connectionFactory.createContext();
        
        JMSConsumer consumer = context.createConsumer(QueuePodsistem1);
        
        Zahtevi z = new Zahtevi();
        
        JMSContext context2 = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        while(true){
            int zahtev=0;
            Boolean uspesno = false;
            ObjectMessage objMsg=null;
            String zahtevJSON = consumer.receiveBody(String.class);
            Gson gson = new Gson();
            JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);
        
            zahtev = obj.get("idZahteva").getAsInt();
                        
            switch(zahtev){
                case 1:
                    Integer id = z.proveraPostojanjaKorisnika(zahtevJSON);
                    objMsg = context.createObjectMessage(id);
                    context.createProducer().send(QueueCentralni1, objMsg);
                    break;
                case 2:
                    uspesno = z.kreiranjeGrada(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni1, objMsg);
                    break;
                case 3:
                    uspesno = z.kreiranjeKorisnika(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni1, objMsg);
                    break;
                case 4:
                    uspesno = z.dodavanjeNovcaKorisniku(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni1, objMsg);
                    break;
                case 5:
                    uspesno = z.promenaAdreseIGradaKorisnika(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni1, objMsg);
                    break;
                case 15:
                    ArrayList<String> gradovi = z.dohvatanjeSvihGradova(zahtevJSON);
                    objMsg = context.createObjectMessage(gradovi);
                    context.createProducer().send(QueueCentralni1, objMsg); 
                    break;
                case 16:
                    ArrayList<String> korisnici = z.dohvatanjeSvihKorisnika(zahtevJSON);
                    objMsg = context.createObjectMessage(korisnici);
                    context.createProducer().send(QueueCentralni1, objMsg);    
                    break;
            }
        }

        
    }
    
}
