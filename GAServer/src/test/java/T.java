import java.security.Provider;
import java.security.Security;
import java.util.TreeSet;

public class T {

    public static void main(String[] args) {

        TreeSet<String> algorithms = new TreeSet<>();
        for (Provider provider : Security.getProviders())
            for (Provider.Service service : provider.getServices())
                if (service.getType().equals("Signature"))
                    algorithms.add(service.getAlgorithm());
        for (String algorithm : algorithms)
            System.out.println(algorithm);


    }

}
