package Aplicacao;

import Aplicacao.Exceptions.EmprestimoException;
import Aplicacao.Exceptions.ExtensaoEmprestimoException;

import java.time.LocalDate;

public class Emprestimo {

    private int id_emp = 0;
    private LocalDate dataHoraEmp;
    private LocalDate fimdataHoraEmp;
    private int extensaoEmprestimo = 0;
    private int extensaoMaximaEmprestimo = 2;
    private int extensaoMinimaEmprestimo = 0;
    private Utilizador utilizador;
    private EBook eBook;
    private ReplicaServidor replicaServidor;
    private int assinaturaTR = 0;
    private CopiaEBook copiaEBook;


    public Emprestimo(int id_emp, LocalDate dataHoraEmp, LocalDate fimdataHoraEmp, Utilizador utilizador, EBook eBook, int assinaturaTR) throws EmprestimoException {
        if (utilizador.getEstado_utilizador().equals("desativado") || utilizador == null)
            throw new EmprestimoException("O Aplicacao.Utilizador está desativo");

        if(assinaturaTR != 1)
            throw new EmprestimoException("Não assinou os Termos de Responsabilidade");

        if (dataHoraEmp.isEqual(fimdataHoraEmp) || fimdataHoraEmp.isBefore(dataHoraEmp))
            throw new EmprestimoException("Datas de inicio e fim de emprestimo são iguais !!!");

        if (eBook == null)
            throw new EmprestimoException("EBook é null !!!");




        this.id_emp = id_emp;
        this.dataHoraEmp = dataHoraEmp;
        this.fimdataHoraEmp = fimdataHoraEmp;
        this.utilizador = utilizador;
        this.eBook = eBook;
        this.assinaturaTR = assinaturaTR;
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

    public EBook getEBook() {
        return eBook;
    }

    public void setEBook(EBook eBook) {
        this.eBook = eBook;
    }

    public ReplicaServidor getReplicaServidor() {
        return replicaServidor;
    }

    public void setReplicaServidor(ReplicaServidor replicaServidor) throws EmprestimoException {
        if (replicaServidor == null)
            throw new EmprestimoException("setReplicaServidor null");

        this.replicaServidor = replicaServidor;
    }

    public CopiaEBook getCopiaEBook() {
        return copiaEBook;
    }

    public void setCopiaEBook(CopiaEBook copiaEBook) throws EmprestimoException {
        if (copiaEBook == null)
            throw new EmprestimoException("setCopiaEBook null");

        this.copiaEBook = copiaEBook;
    }

    public int getAssinaturaTR() {
        return assinaturaTR;
    }

    public void setAssinaturaTR(int assinaturaTR) {
        this.assinaturaTR = assinaturaTR;
    }

    public int getExtensaoEmprestimo() {
        return extensaoEmprestimo;
    }

    public void extenderEmprestimo() throws ExtensaoEmprestimoException {
        if (extensaoEmprestimo < extensaoMinimaEmprestimo || extensaoEmprestimo >= extensaoMaximaEmprestimo){
            System.out.println("Chegou ao limite de extensoes de emprestimo !!!");
            throw new ExtensaoEmprestimoException("Chegou ao limite de extensoes de emprestimo !!!");
        }
        this.extensaoEmprestimo++;
        this.fimdataHoraEmp = this.fimdataHoraEmp.plusMonths(1);
    }
}
