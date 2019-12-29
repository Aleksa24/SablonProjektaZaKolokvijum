package thread;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import domain.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.ConnectionResetException;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author aleksa
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    //argumenti specificni za zadatak
    
    //mozda da ubacim korisnika ako treba ovde
    private Korisnik korisnik;
    
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                RequestObject requestObject = (RequestObject) objectInputStream.readObject();
                ResponseObject responseObject = handleRequest(requestObject);
                objectOutputStream.writeObject(responseObject);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
                System.out.println("uhvacena connection reset nzm zasto");
            }
        }
    }

    private ResponseObject handleRequest(RequestObject requestObject) {
        int operation = requestObject.getOperation();
        switch (operation) {
            case Operation.OPERATION_LOGIN:
                return login((Map) requestObject.getData());
            case Operation.OPERATION_OPERACIJA:
                return operacija((Map) requestObject.getData());
            case Operation.OPERATION_LOGOUT:
                return logout((Korisnik) requestObject.getData());
        }
        return null;
    }

    private ResponseObject login(Map data) {
        String korisnickoIme = (String) data.get("korisnickoIme");
        //String korisnickoIme = (String) data.get("");

        ResponseObject responseObject = new ResponseObject();
        try {
            Korisnik korisnik = loginKorisnik(korisnickoIme);//baca gresku ako je sistem na sistemu ili ako ga nema
            Map<String, Object> mapaZaSlanje = new HashMap<>();
            
            //treba nesto da se ubaci u mapu kada se loguje-------------------------uraditi nesto

            mapaZaSlanje.put("korisnik", korisnik);//poslat korisnik,mozda izbacitimada nema potrebe

            responseObject.setData(mapaZaSlanje);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }

    public Socket getSocket() {
        return socket;
    }

    private ResponseObject operacija(Map map) {
        ResponseObject responseObject = new ResponseObject();
        //citanje iz mape sta mi treba

        //provera uslova, specificno za zadatak postavljanje statusa i 
        return responseObject;
    }

    private Korisnik loginKorisnik(String korisnickoIme) throws Exception {
        Korisnik korisnik = new Korisnik(korisnickoIme);//ubaciti parametre, ne zaboravi equals da overajdujes
        if (!ServerThread.korisniciServera.contains(korisnik)) {//korisnici servera treba da su napunjeni
            System.out.println("nema klijenta u korisnicima");
            throw new Exception("nema klijenta u korisnicima");
        }
        if (ServerThread.korisniciUlogovani.contains(korisnik)) {//korisnici servera treba da su napunjeni
            System.out.println("klijent vec ulogovan");
            throw new Exception("klijent vec ulogovan!");
        }
        if (ServerThread.korisniciServera.contains(korisnik)) {//korisnici servera treba da su napunjeni
            ServerThread.korisniciUlogovani.add(korisnik);
            System.out.println("ubacio korisnika u korisnike");
            this.korisnik = korisnik;
            return korisnik;
        }
        System.out.println("ne bi trebalo da dodje do ovde");
        throw new Exception("ne bi trebalo da je doslo do ovde!!!!");
    }

    private ResponseObject logout(Korisnik korisnikZaSkidanjeSaSistema) {
        //String korisnickoIme = (String) data.get("");

        ResponseObject responseObject = new ResponseObject();
        try {
            logoutKorisnik(korisnikZaSkidanjeSaSistema);//baca gresku

            responseObject.setData(null);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage(ex.getMessage());
        }

        return responseObject;
    }

    private void logoutKorisnik(Korisnik korisnikZaSkidanjeSaSistema) throws Exception {
        
        if (!ServerThread.korisniciUlogovani.contains(korisnikZaSkidanjeSaSistema)) {
            System.out.println("nema korisnika u ulogovanim korisnicima,ovo ne bi trebalo");
            throw new Exception("nema korisnika u ulogovanim korisnicima,ovo ne bi trebalo");
        }
        if (!this.korisnik.equals(korisnikZaSkidanjeSaSistema)) {
            System.out.println("nije dobar korisnik sto ne bi trebalo da se desi");
            throw new Exception("nije dobar korisnik sto ne bi trebalo da se desi");
        }
        ServerThread.korisniciUlogovani.remove(korisnikZaSkidanjeSaSistema);
    }

}
