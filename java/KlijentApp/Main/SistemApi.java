package Main;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.google.gson.JsonObject;
import java.util.ArrayList;

public interface SistemApi {
    
    //Podsistem 1 
    
    @POST("komunikacija/provera-postojanja-korisnika")
    Call<Integer> proveraPostojanjaKorisnika(@Body JsonObject zahtev);
    
    @POST("komunikacija/kreiranje-grada")
    Call<Boolean> kreiranjeGrada(@Body JsonObject zahtev);
    
    @POST("komunikacija/kreiranje-korisnika")
    Call<Boolean> kreiranjeKorisnika(@Body JsonObject zahtev);
    
    @POST("komunikacija/dodavanje-novca-korisniku")
    Call<Boolean> dodavanjeNovcaKorisniku(@Body JsonObject zahtev);
    
    @POST("komunikacija/promena-adrese-i-grada")
    Call<Boolean> promenaAdreseIGrada(@Body JsonObject zahtev);

    @POST("komunikacija/dohvatanje-svih-gradova")
    Call<ArrayList<String>> dohvatanjeSvihGradova(@Body JsonObject zahtev);
    
    @POST("komunikacija/dohvatanje-svih-korisnika")
    Call<ArrayList<String>> dohvatanjeSvihKorisnika(@Body JsonObject zahtev);
    
    //Podsistem 2
    
    @POST("komunikacija/kreiranje-kategorije")
    Call<Boolean> kreiranjeKategorije(@Body JsonObject zahtev);
    
    @POST("komunikacija/kreiranje-artikla")
    Call<Boolean> kreiranjeArtikla(@Body JsonObject zahtev);
    
    @POST("komunikacija/menjanje-cene-artikla")
    Call<Boolean> menjanjeCeneArtikla(@Body JsonObject zahtev);
    
    @POST("komunikacija/postavljanje-popusta-artikla")
    Call<Boolean> postavljanjePopustaArtikla(@Body JsonObject zahtev);
    
    @POST("komunikacija/dodavanje-artikla-u-korpu")
    Call<Boolean> dodavanjeArtiklaUKorpu(@Body JsonObject zahtev);
    
    @POST("komunikacija/brisanje-artikla-iz-korpe")
    Call<Boolean> brisanjeArtiklaIzKorpe(@Body JsonObject zahtev);
    
    @POST("komunikacija/dodavanje-artikla-u-listu-zelja")
    Call<Boolean> dodavanjeArtiklaUListuZelja(@Body JsonObject zahtev);
    
    @POST("komunikacija/brisanje-artikla-iz-liste-zelja")
    Call<Boolean> brisanjeArtiklaIzListeZelja(@Body JsonObject zahtev);
    
    @POST("komunikacija/dohvatanje-svih-kategorija")
    Call<ArrayList<String>> dohvatanjeSvihKategorija(@Body JsonObject zahtev);
    
    @POST("komunikacija/dohvatanje-prodajnih-artikala-korisnika")
    Call<ArrayList<String>> dohvatanjeProdajnihArtikalaKorisnika(@Body JsonObject zahtev);
    
    @POST("komunikacija/dohvatanje-sadrzaja-korpe-korisnika")
    Call<ArrayList<String>> dohvatanjeSadrzajaKorpeKorisnika(@Body JsonObject zahtev);
    
    @POST("komunikacija/dohvatanje-liste-zelja-korisnika")
    Call<ArrayList<String>> dohvatanjeListeZeljaKorisnika(@Body JsonObject zahtev);
}
