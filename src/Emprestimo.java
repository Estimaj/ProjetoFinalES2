import Exceptions.ExtensaoEmprestimoException;

import java.time.LocalDate;

public class Emprestimo {

    private int id_emp = 0;
    private LocalDate dataHoraEmp;
    private LocalDate fimdataHoraEmp;
    private int extensaoEmprestimo = 0;
    private int extensaoMaximaEmprestimo = 2;
    private int extensaoMinimaEmprestimo = 0;
    private Utilizador utilizador;
    private CopiaEBook copiaEBook;
    private ReplicaServidor replicaServidor;
    private int Assinatura_TR = 0;


    public Emprestimo(int id_emp, LocalDate dataHoraEmp, LocalDate fimdataHoraEmp, Utilizador utilizador, CopiaEBook copiaEBook, ReplicaServidor replicaServidor, int asstr) {
        if (utilizador.getEstado_utilizador().equals("desativo"))
           return;
        if (this.getAssinatura_TR() == 0 )
            return;
        this.id_emp = id_emp;
        this.dataHoraEmp = dataHoraEmp;
        this.fimdataHoraEmp = fimdataHoraEmp;
        this.utilizador = utilizador;
        this.copiaEBook = copiaEBook;
        this.replicaServidor = replicaServidor;
        this.Assinatura_TR = asstr;

    }

    public int getAssinatura_TR() { return Assinatura_TR; }

    public void setAssinatura_TR(int assinatura_TR) {
        Assinatura_TR = assinatura_TR;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public LocalDate getDataHoraEmp() {
        return dataHoraEmp;
    }

    public void setDataHoraEmp(LocalDate dataHoraEmp) {
        this.dataHoraEmp = dataHoraEmp;
    }

    public LocalDate getFimdataHoraEmp() {
        return fimdataHoraEmp;
    }

    public void setFimdataHoraEmp(LocalDate fimdataHoraEmp) {
        this.fimdataHoraEmp = fimdataHoraEmp;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public CopiaEBook getCopiaEBook() {
        return copiaEBook;
    }

    public void setCopiaEBook(CopiaEBook copiaEBook) {
        this.copiaEBook = copiaEBook;
    }

    public ReplicaServidor getReplicaServidor() {
        return replicaServidor;
    }

    public void setReplicaServidor(ReplicaServidor replicaServidor) {
        this.replicaServidor = replicaServidor;
    }

    public int getExtensaoEmprestimo() {
        return extensaoEmprestimo;
    }

    public void extenderEmprestimo() throws ExtensaoEmprestimoException {
        if (extensaoEmprestimo < extensaoMinimaEmprestimo || extensaoEmprestimo > extensaoMaximaEmprestimo){
            System.out.println("Chegou ao limite de extensoes de emprestimo !!!");
            throw new ExtensaoEmprestimoException("Chegou ao limite de extensoes de emprestimo !!!");
        }
        this.extensaoEmprestimo++;
        this.fimdataHoraEmp = this.fimdataHoraEmp.plusMonths(1); //mais 1 mes
    }

    /*
    * @param Emprestimo
    * @param LocalDate data
    *
    * @return True/False
    *
    * @Test -> whiteBox
    * @Test -> whiteBox com extensao de emprestimo
    * */
    public int visualizacaoEBook(LocalDate data_visualizacao){
        /*
        *   se data_visualizacao maior getDataHoraEmp e data_visualizacao menor getFimdataHoraEmp e getUtilizador().estado igual a ativo
        *   devolve 200
         */
        return 400;
    }
}
