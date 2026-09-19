package Main;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ObradaZahteva {
    
    private final SistemApi api;
    private final Scanner sc;
    private String korisnickoIme;
    private Integer idKorisnika;
    
    public ObradaZahteva(){
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/CentralniSistemNew/resources/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = rf.create(SistemApi.class);
        this.sc = new Scanner(System.in);
    }
    
    public void login() throws IOException{  
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 1);
        
        System.out.println("Unesite korisnicko ime: ");
        String username = sc.nextLine();
        obj.addProperty("imeKorisnika", username);
        System.out.println("Unesite sifru: ");
        String password = sc.nextLine();
        obj.addProperty("sifraKorisnika", password);

        Response<Integer> res = api.proveraPostojanjaKorisnika(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()>=0){
                System.out.println("Uspesno ste se ulogovali!\n");
                this.korisnickoIme = username;
                this.idKorisnika = res.body();
                System.out.println("Id: " + this.idKorisnika);
            }
            else
                System.out.println("Korisnik ne postoji u sistemu!\n");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva1() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 1);
        
        System.out.println("Unesite korisnicko ime: ");
        String username = sc.nextLine();
        obj.addProperty("imeKorisnika", username);
        System.out.println("Unesite sifru: ");
        String password = sc.nextLine();
        obj.addProperty("sifraKorisnika", password);

        Response<Integer> res = api.proveraPostojanjaKorisnika(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()>=0){
                System.out.println("Korisnik postoji u sistemu!\n");
            }
            else
                System.out.println("Korisnik ne postoji u sistemu!\n");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva2() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 2);
        
        System.out.println("Unesite naziv grada: ");
        String nazivGrada = sc.nextLine();
        obj.addProperty("nazivGrada", nazivGrada);

        Response<Boolean> res = api.kreiranjeGrada(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali grad!\n");
            }
            else
                System.out.println("Neuspesno dodavanje grada!\n");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva3() throws IOException{    
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 3);
        
        //public Korisnik(Integer idKorisnik, String korisnickoIme, String sifra, String ime, String prezime, String adresa, int stanjeNovca) {

        System.out.println("Unesite korisnicko ime: ");
        String username = sc.nextLine();
        obj.addProperty("korisnickoIme", username);
        System.out.println("Unesite sifru: ");
        String password = sc.nextLine();
        obj.addProperty("sifra", password);
        System.out.println("Unesite ime: ");
        String ime = sc.nextLine();
        obj.addProperty("ime", ime);
        System.out.println("Unesite prezime: ");
        String prezime = sc.nextLine();
        obj.addProperty("prezime", prezime);
        System.out.println("Unesite adresu: ");
        String adresa = sc.nextLine();
        obj.addProperty("adresa", adresa);
        System.out.println("Unesite naziv grada: ");
        String grad = sc.nextLine();
        obj.addProperty("nazivGrada", grad);
        System.out.println("Unesite stanje novca: ");
        int stanje = sc.nextInt();
        obj.addProperty("stanjeNovca", stanje);
        sc.nextLine();
        

        Response<Boolean> res = api.kreiranjeKorisnika(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali korisnika!\n");
            }
            else
                System.out.println("Neuspesno dodavanje korisnika!\n");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    public void obradaZahteva4() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 4);
        
        System.out.println("Unesite vrednost novca koja se dodaje na racun: ");
        String vrednost = sc.nextLine();
        obj.addProperty("vrednost", vrednost);
        System.out.println("Unesite username korisnika kome se dodaje novac: ");
        String username = sc.nextLine();
        obj.addProperty("korisnik", username);

        Response<Boolean> res = api.dodavanjeNovcaKorisniku(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali novac korisniku: \n" + username);
            }
            else
                System.out.println("Neuspesno dodavanje novca korisniku: \n" + username);
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva5() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 5);
        
        System.out.println("Unesite username korisnika kome se dodaje novac: ");
        String username = sc.nextLine();
        obj.addProperty("korisnickoIme", username);
        System.out.println("Unesite novu adresu korisnika: ");
        String adresa = sc.nextLine();
        obj.addProperty("novaAdresa", adresa);

        Response<Boolean> res = api.dodavanjeNovcaKorisniku(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali novac korisniku: \n" + username);
            }
            else
                System.out.println("Neuspesno dodavanje novca korisniku: \n" + username);
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva15() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 15);

        Response<ArrayList<String>> res = api.dohvatanjeSvihGradova(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            System.out.println("Gradovi u sistemu:");
            for(String grad : res.body())
                System.out.println(grad);
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva16() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 16);

        Response<ArrayList<String>> res = api.dohvatanjeSvihKorisnika(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            System.out.println("Korisnici u sistemu:");
            for(String korisnik : res.body())
                System.out.println(korisnik);
                
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva6() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 6);
        
        System.out.println("Unesite naziv kategorije: ");
        String nazivKategorije = sc.nextLine();
        obj.addProperty("nazivKategorije", nazivKategorije);
        
        System.out.println("Da li kategorija koju dodajete ima nadkategoriju?(Izaberite : 1-da | 0-ne): ");
        int imaNadkategoriju = sc.nextInt();
        sc.nextLine();
        if(imaNadkategoriju==1){
            System.out.println("Unesite naziv nadkategorije: ");
            String nazivNadkategorije = sc.nextLine();
            obj.addProperty("nazivNadkategorije", nazivNadkategorije);
        }
        else{
            obj.addProperty("nazivNadkategorije", "");
        }

        Response<Boolean> res = api.kreiranjeKategorije(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali novu kategoriju!");
            }
            else
                System.out.println("Neuspesno dodavanje kategorije!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    public void obradaZahteva7() throws IOException{
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 6);
        
        System.out.println("Unesite naziv artikla: ");
        String nazivArtikla = sc.nextLine();
        obj.addProperty("nazivArtikla", nazivArtikla);
        System.out.println("Unesite opis artikla: ");
        String opis = sc.nextLine();
        obj.addProperty("opis", opis);
        System.out.println("Unesite cenu artikla: ");
        int cena = sc.nextInt();
        obj.addProperty("cena", cena);
        sc.nextLine();
        System.out.println("Unesite popust(ceo broj koji oznacava procente): ");
        int popust = sc.nextInt();
        obj.addProperty("popust", popust);
        sc.nextLine();
        
        System.out.println("Unesite naziv kategorije artikla: ");
        String nazivKategorije = sc.nextLine();
        obj.addProperty("nazivKategorije", nazivKategorije);
        
        Response<Boolean> res = api.kreiranjeArtikla(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali novi artikal!");
            }
            else
                System.out.println("Neuspesno dodavanje artikla!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva8() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 8);

        System.out.println("Unesite naziv artikla kojem menjate cenu: ");
        String artikal = sc.nextLine();
        obj.addProperty("nazivArtikla", artikal);

        System.out.println("Unesite novu cenu: ");
        int novaCena = sc.nextInt();
        sc.nextLine();
        obj.addProperty("novaCena", novaCena);

        Response<Boolean> res = api.menjanjeCeneArtikla(obj).execute();
        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali novi artikal!");
            }
            else
                System.out.println("Neuspesno dodavanje artikla!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva9() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 9);

        System.out.println("Unesite naziv artikla: ");
        String naziv = sc.nextLine();
        obj.addProperty("nazivArtikla", naziv);

        System.out.println("Unesite novi popust(ceo broj koji oznacava procente): ");
        int popust = sc.nextInt();
        sc.nextLine(); 
        obj.addProperty("popust", popust);

        Response<Boolean> res = api.postavljanjePopustaArtikla(obj).execute();
        
        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste postavili popust artiklu!");
            }
            else
                System.out.println("Neuspesno postavljanje popusta!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva10() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 10);

        System.out.println("Unesite naziv artikla koji zelite da dodate u korpu: ");
        String naziv = sc.nextLine();
        obj.addProperty("nazivArtikla", naziv);

        System.out.println("Unesite kolicinu: ");
        int kolicina = sc.nextInt();
        sc.nextLine(); 
        obj.addProperty("kolicina", kolicina);
        
        obj.addProperty("korisnik", this.idKorisnika);

        Response<Boolean> res = api.dodavanjeArtiklaUKorpu(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali artikal u korpu!");
            }
            else
                System.out.println("Neuspesno dodavanje artikla u korpu!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva11() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 11);

        System.out.println("Unesite naziv artikla koji zelite da obrisete iz korpe: ");
        String naziv = sc.nextLine();
        obj.addProperty("nazivArtikla", naziv);

        System.out.println("Unesite kolicinu koju brisete: ");
        int kolicina = sc.nextInt();
        sc.nextLine(); 
        obj.addProperty("kolicina", kolicina);

        obj.addProperty("korisnik", this.idKorisnika);
        
        Response<Boolean> res = api.brisanjeArtiklaIzKorpe(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste obrisali artikal iz korpe!");
            }
            else
                System.out.println("Neuspesno brisajne artikla iz korpe!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }

    public void obradaZahteva12() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 12);

        System.out.println("Unesite naziv artikla koji zelite da dodate u listu zelja: ");
        String naziv = sc.nextLine();
        obj.addProperty("nazivArtikla", naziv);

        obj.addProperty("korisnik", this.idKorisnika);
        
        Response<Boolean> res = api.dodavanjeArtiklaUListuZelja(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste dodali artikal u listu zelja!");
            }
            else
                System.out.println("Neuspesno dodavanje artikla u listu zelja!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva13() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 12);

        System.out.println("Unesite naziv artikla koji zelite da obrisete iz listu zelja: ");
        String naziv = sc.nextLine();
        obj.addProperty("nazivArtikla", naziv);
        
        obj.addProperty("korisnik", this.idKorisnika);

        Response<Boolean> res = api.brisanjeArtiklaIzListeZelja(obj).execute();

        if (res.isSuccessful()) {
            if(res.body()==true){
                System.out.println("Uspesno ste obrisali artikal iz liste zelja!");
            }
            else
                System.out.println("Neuspesno brisajne artikla iz liste zelja!");
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva17() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 12);

        Response<ArrayList<String>> res = api.dohvatanjeSvihKategorija(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            System.out.println("Kategorije u sistemu:");
            for(String kategorija : res.body())
                System.out.println(kategorija);
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva18() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 18);
        
        obj.addProperty("korisnik", this.idKorisnika);

        Response<ArrayList<String>> res = api.dohvatanjeProdajnihArtikalaKorisnika(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            System.out.println("Artikli koje prodaje korisnik:");
            for(String artikal : res.body())
                System.out.println(artikal);
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva19() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 19);
        
        obj.addProperty("korisnik", this.idKorisnika);

        Response<ArrayList<String>> res = api.dohvatanjeProdajnihArtikalaKorisnika(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            if(res.body().isEmpty()){
                System.out.println("Korpa je prazna!");
            }
            else{
                System.out.println("Sadrzaj korpe korisnika:");
                for(String artikal : res.body())
                    System.out.println(artikal);
            }
            
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
    public void obradaZahteva20() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("idZahteva", 20);
        
        obj.addProperty("korisnik", this.idKorisnika);

        Response<ArrayList<String>> res = api.dohvatanjeProdajnihArtikalaKorisnika(obj).execute();

        if (res.isSuccessful() && res.body()!=null) {
            if(res.body().isEmpty()){
                System.out.println("Lista zelja je prazna!");
            }
            else{
                System.out.println("Sadrzaj korpe korisnika:");
                for(String artikal : res.body())
                    System.out.println(artikal);
            }
            
        }
        else{
            System.out.println("Server vratio gresku: " + res.code());
        }
    }
    
}
