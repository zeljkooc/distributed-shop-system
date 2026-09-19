package podsistem1new;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entiteti.Grad;
import entiteti.Korisnik;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class Zahtevi {
    
    public Integer proveraPostojanjaKorisnika(String zahtevJSON){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            Gson gson = new Gson();
            JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

            String username = obj.get("imeKorisnika").getAsString();
            String password = obj.get("sifraKorisnika").getAsString();
                        
            TypedQuery<Korisnik> tq = em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoIme= :username AND k.sifra = :password",Korisnik.class);
            tq.setParameter("username", username);
            tq.setParameter("password", password);
            List<Korisnik> korisnici = tq.getResultList();
            if(korisnici.size()==1)
                return korisnici.get(0).getIdKorisnik();
            else
                return -1;
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    
    public Boolean kreiranjeGrada(String zahtevJSON){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            Gson gson = new Gson();
            JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

            String nazivGrada = obj.get("nazivGrada").getAsString();

            Grad grad = new Grad();
            grad.setNazivGrada(nazivGrada);
            
            transaction.begin();
            em.persist(grad);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        emf.close();
        return true;
    }
    
    public Boolean kreiranjeKorisnika(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

        String korisnickoIme = obj.get("korisnickoIme").getAsString();
        String sifra = obj.get("sifra").getAsString();
        String ime = obj.get("ime").getAsString();
        String prezime = obj.get("prezime").getAsString();
        String adresa = obj.get("adresa").getAsString();
        int stanjeNovca = obj.get("stanjeNovca").getAsInt();
        String nazivGrada = obj.get("nazivGrada").getAsString();
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Grad> tq = em.createQuery("SELECT g FROM Grad g WHERE g.nazivGrada= :grad",Grad.class);
            tq.setParameter("grad", nazivGrada);
            List<Grad> gradovi = tq.getResultList();
            
            Grad grad;
            if(gradovi.size()>0){
                grad = gradovi.get(0);
            }
            else{
                return false;
            }
            
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);
            korisnik.setSifra(sifra);
            korisnik.setIme(ime);
            korisnik.setPrezime(prezime);
            korisnik.setAdresa(adresa);
            korisnik.setStanjeNovca(stanjeNovca);
            korisnik.setIdGrad(grad);
            
            transaction.begin();
            em.persist(korisnik);
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
    
    public Boolean dodavanjeNovcaKorisniku(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

        int vrednost = obj.get("vrednost").getAsInt();
        String korisnickoIme = obj.get("korisnik").getAsString();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Korisnik> tq = em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoIme= :username",Korisnik.class);
            tq.setParameter("username", korisnickoIme);
            List<Korisnik> korisnici = tq.getResultList();
            
            Korisnik korisnik;
            if(korisnici.size()>0){
                korisnik = korisnici.get(0);
            }
            else{
                return false;
            }
            
            transaction.begin();
            int trenutnoStanje = korisnik.getStanjeNovca();
            korisnik.setStanjeNovca(trenutnoStanje+vrednost);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        emf.close();
        return true;
    }
    
    public Boolean promenaAdreseIGradaKorisnika(String zahtevJSON){
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(zahtevJSON, JsonObject.class);

        String korisnickoIme = obj.get("imeKorisnika").getAsString();
        String novaAdresa = obj.get("novaAdresa").getAsString();
        String noviGrad = obj.get("noviGrad").getAsString();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<Korisnik> tq = em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoIme= :username",Korisnik.class);
            tq.setParameter("username", korisnickoIme);
            List<Korisnik> korisnici = tq.getResultList();
            
            Korisnik korisnik;
            if(korisnici.size()>0){
                korisnik = korisnici.get(0);
            }
            else{
                return false;
            }
            TypedQuery<Grad> tq2 = em.createQuery("SELECT g FROM Grad g WHERE g.nazivGrada= :grad",Grad.class);
            tq2.setParameter("grad", noviGrad);
            List<Grad> gradovi = tq2.getResultList();
            
            Grad grad;
            if(gradovi.size()>0){
                grad = gradovi.get(0);
            }
            else{
                return false;
            }
            
            transaction.begin();
            korisnik.setAdresa(novaAdresa);
            korisnik.setIdGrad(grad);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        emf.close();
        return true;
    }
    
    public ArrayList<String> dohvatanjeSvihKorisnika(String zahtevJSON){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        List<String> korisnici=null;
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<String> tq = em.createQuery("SELECT k.korisnickoIme FROM Korisnik k",String.class);
            korisnici = tq.getResultList();
            
            return new ArrayList<>(korisnici);
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }     
    }
    
    public ArrayList<String> dohvatanjeSvihGradova(String zahtevJSON){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podsistem1newPU");
        EntityManager em = emf.createEntityManager();
        List<String> gradovi=null;
        try{
            EntityTransaction transaction = em.getTransaction();
            
            TypedQuery<String> tq = em.createQuery("SELECT g.nazivGrada FROM Grad g",String.class);
            gradovi = tq.getResultList();
            
            return new ArrayList<>(gradovi);
        }
        finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    
    
}
