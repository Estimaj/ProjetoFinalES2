package Visualizacao;

import Aplicacao.CopiaEBook;
import Visualizacao.Exceptions.FileExtensionException;
import Visualizacao.Exceptions.FileSizeException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class StubWebAPI implements InterfaceComponenteWeb {

    private int responseCode = 400;
    private String JSONResponse = null;
    private String OutputContent = null;
    private String RequestMethod = null;


    @Override
    public void requestWebAPI(CopiaEBook copiaEBook) throws IOException, FileExtensionException, FileSizeException {

        //File file = new File("teste\\src\\Main.java");
        //String[] parts = file.getPath().split("\\\\");
        //String extension = check_file_extension(parts);


        String extension = copiaEBook.getEBook().getFormato();

        if (extension == null || !extension.equals("pdf"))
            throw new FileExtensionException("O formato do ficheiro so pode ser pdf ou ");

        if (extension.getBytes().length > 100)
            throw new FileSizeException("Tamanho do ficheiro nao suportado");

        String word = copiaEBook.getEBook().getTitulo();


        //Establishes a HTTP Request with the server
        URL url = new URL("https://owlbot.info/api/v4/dictionary/" + word + "?format=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
        conn.setRequestMethod("GET");

        this.setResponseCode(conn.getResponseCode());
        this.setRequestMethod(conn.getRequestMethod());

        String line = "";
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        //Close the scanner
        scanner.close();

        System.out.println(line);
        this.setContent(line);

        //Close the Connection
        conn.disconnect();
    }

    @Override
    public int getResponseCode() {
        return this.responseCode;
    }

    @Override
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getJSONResponse() {
        return this.JSONResponse;
    }

    @Override
    public void setJSONResponse(String JSONResponse) {
        this.JSONResponse = JSONResponse;
    }

    @Override
    public String getContent() {
        return this.OutputContent;
    }

    @Override
    public void setContent(String OutputContent) {
        this.OutputContent = OutputContent;
    }

    @Override
    public String getRequestMethod() {
        return RequestMethod;
    }

    @Override
    public void setRequestMethod(String requestMethod) {
        RequestMethod = requestMethod;
    }

    private String check_file_extension(String[] parts) {
        for (String s : parts) {
            if (s.contains(".java"))
                return s;
        }
        return null;
    }

}
