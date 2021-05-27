package Visualizacao;

import Aplicacao.CopiaEBook;
import Aplicacao.Emprestimo;
import Aplicacao.Exceptions.InvalidVisualizacaoException;
import Visualizacao.Exceptions.FileExtensionException;
import Visualizacao.Exceptions.FileSizeException;

import java.io.IOException;

public interface InterfaceComponenteWeb {
    void requestWebAPI(Emprestimo emp) throws IOException, FileExtensionException, FileSizeException, InvalidVisualizacaoException;
    int getResponseCode();
    void setResponseCode(int responseCode);
    String getJSONResponse();
    void setJSONResponse(String JSONResponse);
    String getContent();
    void setContent(String OutputContent);
    String getRequestMethod();
    void setRequestMethod(String requestMethod);

}
