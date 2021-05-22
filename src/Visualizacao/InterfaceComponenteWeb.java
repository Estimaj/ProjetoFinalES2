package Visualizacao;

import Aplicacao.CopiaEBook;
import Visualizacao.Exceptions.FileExtensionException;
import Visualizacao.Exceptions.FileSizeException;

import java.io.IOException;

public interface InterfaceComponenteWeb {
    void requestWebAPI(CopiaEBook copiaEBook) throws IOException, FileExtensionException, FileSizeException;
    int getResponseCode();
    void setResponseCode(int responseCode);
    String getJSONResponse();
    void setJSONResponse(String JSONResponse);
    String getContent();
    void setContent(String OutputContent);
    String getRequestMethod();
    void setRequestMethod(String requestMethod);

}
