package Main;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        
        ObradaZahteva oz = new ObradaZahteva();
        oz.login();
        
        while(true){
            System.out.println("1. Provera postojanja korisnika na osnovu kredencijala");
            System.out.println("2. Kreiranje grada");
            System.out.println("3. Kreiranje korisnika");
            System.out.println("4. Dodavanje novca korisniku");
            System.out.println("5. Promena adrese i grada za korisnika");
            System.out.println("6. Kreiranje kategorije");
            System.out.println("7. Kreiranje artikla");
            System.out.println("8. Menjanje cene artikla");
            System.out.println("9. Postavljanje popusta za artikal");
            System.out.println("10. Dodavanje artikala u odredjenoj kolicini u korpu");
            System.out.println("11. Brisanje artikla u odredjenoj kolicini iz korpe");
            System.out.println("12. Dodavanje artikla u listu zelja");
            System.out.println("13. Brisanje artikla iz liste zelja");
            System.out.println("14. Placanje, koje obuhvata kreiranje transakcije, kreiranje narudzbine sa njenim stavkama, i brisanje sadrzaja iz korpe");
            System.out.println("15. Dohvatanje svih gradova");
            System.out.println("16. Dohvatanje svih korisnika");
            System.out.println("17. Dohvatanje svih kategorija");
            System.out.println("18. Dohvatanje svih artikala koje prodaje korisnik koji je poslao zahtev");
            System.out.println("19. Dohvatanje sadrzaja korpe korisnika koji je poslao zahtev");
            System.out.println("20. Dohvatanje sadrzaja liste zelja korisnika koji je poslao zahtev");
            System.out.println("21. Dohvatanje svih narudzbina korisnika koji je poslao zahtev");
            System.out.println("22. Dohvatanje svih narudzbina");
            System.out.println("23. Dohvatanje svih transakcija\n");
            
            System.out.println("Unesite stavku menija koju zelite da izvrsite: ");
            Scanner sc = new Scanner(System.in);
            int stavka = sc.nextInt();
            
            
            switch(stavka){
                case 1: oz.obradaZahteva1(); break;
                case 2: oz.obradaZahteva2(); break;
                case 3: oz.obradaZahteva3(); break;
                case 4: oz.obradaZahteva4(); break;
                case 5: oz.obradaZahteva5(); break;
                case 6: oz.obradaZahteva6(); break;
                case 7: oz.obradaZahteva7(); break;
                case 8: oz.obradaZahteva8(); break;
                case 9: oz.obradaZahteva9(); break;
                case 10: oz.obradaZahteva10(); break;
                case 11: oz.obradaZahteva11(); break;
                case 12: oz.obradaZahteva12(); break;
                case 13: oz.obradaZahteva13(); break;
//                case 14: oz.obradaZahteva14(); break;
                case 15: oz.obradaZahteva15(); break;
                case 16: oz.obradaZahteva16(); break;
                case 17: oz.obradaZahteva17(); break;
//                case 18: oz.obradaZahteva18(); break;
//                case 19: oz.obradaZahteva19(); break;
//                case 20: oz.obradaZahteva20(); break;
//                case 21: oz.obradaZahteva21(); break;
//                case 22: oz.obradaZahteva22(); break;
//                case 23: oz.obradaZahteva23(); break;
                default:
                    System.out.println("Uneli ste neispravnu stavku!\n");
                    break;
            }
        }
    }
}
