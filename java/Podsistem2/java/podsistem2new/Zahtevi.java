package podsistem2new;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entiteti.Artikal;
import entiteti.ArtikliUKorpi;
import entiteti.ArtikliUListiZelja;
import entiteti.Kategorija;
import entiteti.Korpa;
import entiteti.ListaZelja;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class Zahtevi {
    
    public Boolean kreiranjeKategorije(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

        String nazivKategorije = obj.get("nazivKategorije").getAsString();
        String nazivNadkategorije = obj.get("nazivNadkategorije").getAsString();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            Kategorija kategorija = new Kategorija();
            
            if(nazivNadkategorije.length()>0){
                TypedQuery<Kategorija> tq = em.createQuery("SELECT k FROM Kategorija k WHERE k.nazivKategorije= :naziv",Kategorija.class);
                tq.setParameter("naziv", nazivNadkategorije);
                List<Kategorija> kategorije = tq.getResultList();

                Kategorija nadKategorija;
                if(kategorije.size()>0){
                    nadKategorija = kategorije.get(0);
                }
                else{
                    return false;
                }
                kategorija.setIdNadkategorije(nadKategorija);
            }
            
            kategorija.setNazivKategorije(nazivKategorije);
            
            transaction.begin();
            em.persist(kategorija);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    
    public Boolean kreiranjeArtikla(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

        String nazivArtikla = obj.get("nazivArtikla").getAsString();
        String opis = obj.get("opis").getAsString();
        int cena = obj.get("cena").getAsInt();
        int popust = obj.get("popust").getAsInt();
        String kategorija = obj.get("kategorija").getAsString();
        String korisnik = obj.get("korisnik").getAsString();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Kategorija> tq = em.createQuery("SELECT k FROM Kategorija k WHERE k.nazivKategorije= :naziv",Kategorija.class);
            tq.setParameter("naziv", kategorija);
            List<Kategorija> kategorije = tq.getResultList();

            Kategorija k;
            if(kategorije.size()>0){
                k = kategorije.get(0);
            }
            else{
                return false;
            }
            
            Artikal artikal = new Artikal();
            artikal.setNazivArtikla(nazivArtikla);
            artikal.setOpisArtikla(opis);
            artikal.setPopust(popust);
            artikal.setIdKategorije(k);
            artikal.setIdKorisnika(1);
            
            transaction.begin();
            em.persist(artikal);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
        
    }
    
    public Boolean menjanjeCeneArtikla(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);
        
        String naziv = obj.get("nazivArtikla").getAsString();
        int novaCena = obj.get("novaCena").getAsInt();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();          
            
            TypedQuery<Artikal> tq = em.createQuery("SELECT a FROM Artikal a WHERE a.naziv = :naziv", Artikal.class);
            tq.setParameter("naziv", naziv);
            List<Artikal> artikli = tq.getResultList();
            
            Artikal artikal;
            if(artikli.size()>0)
                artikal = artikli.get(0);
            else
                return false;
            
            transaction.begin();
            artikal.setCenaArtikla(novaCena);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
        
    }
    
    public Boolean postavljanjePopustaZaArtikal(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);
        
        String naziv = obj.get("nazivArtikla").getAsString();
        int popust = obj.get("popust").getAsInt();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Artikal> tq = em.createQuery("SELECT a FROM Artikal a WHERE a.naziv = :naziv", Artikal.class);
            tq.setParameter("naziv", naziv);
            List<Artikal> artikli = tq.getResultList();
            
            Artikal artikal;
            if(artikli.size()>0)
                artikal = artikli.get(0);
            else
                return false;
            
            transaction.begin();
            artikal.setPopust(popust);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }    
    }  
    
    public Boolean dodavanjeArtiklaUKorpu(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);
        
        String naziv = obj.get("nazivArtikla").getAsString();
        int kolicina = obj.get("kolicina").getAsInt();
        int id = obj.get("korisnik").getAsInt();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Artikal> tq = em.createQuery("SELECT a FROM Artikal a WHERE a.naziv = :naziv", Artikal.class);
            tq.setParameter("naziv", naziv);
            List<Artikal> artikli = tq.getResultList();
            
            Artikal artikal;
            if(artikli.size()>0)
                artikal = artikli.get(0);
            else
                return false;
            
            TypedQuery<Korpa> tq2 = em.createQuery("SELECT k FROM Korpa k WHERE k.idKorisnika = :id", Korpa.class);
            tq2.setParameter("id", id);
            List<Korpa> korpe = tq2.getResultList();
            
            Korpa korpa;
            if(artikli.size()>0)
                korpa = korpe.get(0);
            else
                return false;
            
            ArtikliUKorpi auk = new ArtikliUKorpi();
            auk.setIdArtiklaFK(artikal);
            auk.setIdKorpeFK(korpa);
            auk.setKolicina(kolicina);
            
            transaction.begin();
            em.persist(auk);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }    
    }
    
    public Boolean dodavanjeArtiklaUListuZelja(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);
        
        String naziv = obj.get("nazivArtikla").getAsString();
        int id = obj.get("korisnik").getAsInt();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();          
            
            TypedQuery<Artikal> tq = em.createQuery("SELECT a FROM Artikal a WHERE a.naziv = :naziv", Artikal.class);
            tq.setParameter("naziv", naziv);
            List<Artikal> artikli = tq.getResultList();
            
            Artikal artikal;
            if(artikli.size()>0)
                artikal = artikli.get(0);
            else
                return false;
            
            TypedQuery<ListaZelja> tq2 = em.createQuery("SELECT l FROM ListaZelja l WHERE l.idKorisnika = :id", ListaZelja.class);
            tq2.setParameter("id", id);
            List<ListaZelja> liste = tq2.getResultList();
            
            ListaZelja lz;
            if(liste.size()>0)
                lz = liste.get(0);
            else
                return false;
            
            ArtikliUListiZelja aulz = new ArtikliUListiZelja();
            aulz.setIdArtikla(artikal);
            aulz.setIdListeZelja(lz);
            aulz.setVremeDodavanja(LocalDateTime.now().toString());
            
            transaction.begin();
            em.persist(aulz);
            transaction.commit();
            return true;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
        
    }
    
    public ArrayList<String> dohvatanjeSvihKategorija(String zahtevJSON){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem2newPU");
        EntityManager em = emf.createEntityManager();
        List<String> kategorije=null;
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<String> tq = em.createQuery("SELECT k.nazivKategorije FROM Kategorija k",String.class);
            kategorije = tq.getResultList();
            
            return new ArrayList<>(kategorije);
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }     
    }
    
}
