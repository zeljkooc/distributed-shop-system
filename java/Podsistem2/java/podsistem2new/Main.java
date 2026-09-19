package podsistem2new;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

public class Main {

    @Resource(lookup="myConnFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup="QueuePodsistem2")
    private static Queue QueuePodsistem2;
    
    @Resource(lookup="QueueCentralni2")
    private static Queue QueueCentralni2;
    
    public static void main(String[] args) {
        
        JMSContext context = connectionFactory.createContext();
        
        JMSConsumer consumer = context.createConsumer(QueuePodsistem2);
        
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
                case 6:
                    uspesno = z.kreiranjeKategorije(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 7:
                    uspesno = z.kreiranjeArtikla(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 8:
                    uspesno = z.menjanjeCeneArtikla(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 9:
                    uspesno = z.postavljanjePopustaZaArtikal(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 10:
                    uspesno = z.dodavanjeArtiklaUKorpu(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 12:
                    uspesno = z.dodavanjeArtiklaUListuZelja(zahtevJSON);
                    objMsg = context.createObjectMessage(uspesno);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
                case 17:
                    ArrayList<String> kategorije = z.dohvatanjeSvihKategorija(zahtevJSON);
                    objMsg = context.createObjectMessage(kategorije);
                    context.createProducer().send(QueueCentralni2, objMsg);
                    break;
            }
        }
    } 
}
