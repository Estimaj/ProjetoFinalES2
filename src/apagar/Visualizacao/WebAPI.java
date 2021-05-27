package apagar.Visualizacao;
import Aplicacao.Emprestimo;

public class WebAPI implements InterfaceComponenteWeb {

    private int responseCode = 400;
    private String JSONResponse = null;
    private String OutputContent = null;
    private String RequestMethod = null;

    @Override
    public void requestWebAPI(Emprestimo emp) {
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
}
