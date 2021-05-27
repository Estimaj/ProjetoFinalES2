import Aplicacao.EBook;
import Aplicacao.Exceptions.InvalidCopiaEBookException;
import Aplicacao.Exceptions.InvalidEBookException;
import Aplicacao.Exceptions.InvalidUserException;
import Aplicacao.Exceptions.UtilizadorNullException;
import Aplicacao.Utilizador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UtilizadorNullException, InvalidUserException, InvalidCopiaEBookException, InvalidEBookException, NoSuchAlgorithmException {
        Utilizador user = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Krypton,Krypton","111-111-111","ativo");
        EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");

        String str = "123";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);

        System.out.println(encoded);


/*
        System.out.println("hello");
        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Krypton","111","ativo");
        DetecaoFraudeInterface detecaoFraudeInterface = new DetecaoFraudeStub();
        detecaoFraudeInterface.detecao_fraude(u);

        EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        ReplicaServidor replicaServidor_faro = new ReplicaServidor("Faro",copiaEBook);
        ReplicaServidor replicaServidor_porto = new ReplicaServidor("Porto",copiaEBook);

        Server main_server = new Server("Portugal");
        System.out.println(main_server.get_replicas_ArrayList_Size());


        main_server.addReplica(replicaServidor_aveiro);
        main_server.addReplica(replicaServidor_faro);
        main_server.addReplica(replicaServidor_porto);

        System.out.println(main_server.get_replicas_ArrayList_Size());

        u = new Utilizador(1,"Clark","Clark@exemplo.pt","Aveiro, Portugal","111","ativo");


        String[] parts = u.getMorada_utilizador().split(",");
        String part1 = parts[0]; // get cidade
        String part2 = parts[1].replaceAll("\\s+",""); // remove spaces

        System.out.println(part1);
        System.out.println(part2);

        ArrayList<Integer> teste = new ArrayList<>();
        teste.add(1);
        teste.add(2);
        teste.add(3);


        File file = new File("teste\\src\\Main.java");
        String path = file.getPath();
        System.out.println(path);
        String[] aux = path.split("\\\\");
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].contains(".java"))
                System.out.println("true");
        }

        /*System.out.println(teste.get(1));
        try {
            String aux = getHTTPRequest("teste.pdf"); //funciona
            System.out.println(aux);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
    public static String getHTTPRequest(String word) throws IOException, MalformedURLException {
        System.out.println(" word: " + word);
        //Establishes a HTTP Request with the server
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://owlbot.info/api/v4/dictionary/" + word + "?format=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }

        //Close the scanner
        scanner.close();
        System.out.println(line);
        System.out.println("code = " + conn.getResponseCode());
        rd.close();
        conn.disconnect();
        return result.toString();
    }

}

