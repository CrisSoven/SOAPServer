import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.Random;

@WebService(name = "Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SOAPService {
    /////////////////////////////////////////////////////////////////////////////////////
    Random random = new Random();
    int numRandom = random.nextInt(5);
    @WebMethod(operationName = "NumRandom")
    public String NumRandom(@WebParam(name = "num") int num){
        System.out.println(numRandom);
        if(num != numRandom) {
            return "Intentalo de nuevo";
        }
        return "Bien hecho";
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @WebMethod(operationName = "Consonantes")
    public String Consonantes(@WebParam(name = "palabra") String palabra){
        if(palabra.length() == 0){
            return palabra;
        } else {
            return (Vocal(palabra.charAt(0)) ? "" : palabra.charAt(0)) + Consonantes(palabra.substring(1));
        }
    }

    public boolean Vocal(char vocal){
        return vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'c';
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @WebMethod(operationName = "RFC")
    public String RFC(@WebParam(name = "Nombre") String name,
                      @WebParam(name = "ApellidoPaterno") String lastname1,
                      @WebParam(name = "ApellidoMaterno") String lastname2,
                      @WebParam(name = "AnioNacimiento") String year,
                      @WebParam(name = "MesNacimiento") String mounth,
                      @WebParam(name = "DiaNacimiento") String day){

        String  abc = "QWERTYUIOPASDFGHJKLZXCVBNMÑ";
        String  nameUpper = name.toUpperCase(),
                lastnameUpper = lastname1.toUpperCase(),
                lastname2Upper = lastname2.toUpperCase();

        int     randString = (int)random.nextInt(27),
                randString2 = (int)random.nextInt(27),
                numRandom = random.nextInt(9);

        return lastnameUpper.charAt(0)+""+
                lastnameUpper.charAt(1)+""+
                lastname2Upper.charAt(0)+""+
                nameUpper.charAt(0)+""+
                year.substring(2,4)+""+
                mounth+""+
                day+""+
                abc.charAt(randString)+""+
                abc.charAt(randString2)+""+
                numRandom;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        System.out.println("Running server...");
        Endpoint.publish("http://localhost:8080/Service", new SOAPService());
        System.out.println("Waiting requests...");

    }
}