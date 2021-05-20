import Exceptions.ExtensaoEmprestimoException;

import java.time.LocalDate;

public class Emprestimo {

    private int id_emp = 0;
    private LocalDate dataHoraEmp;
    private LocalDate fimdataHoraEmp;
    private int extensaoEmprestimo = 0;
    private Utilizador utilizador;
    private CopiaEBook copiaEBook;

    public Emprestimo(int id_emp, LocalDate dataHoraEmp, LocalDate fimdataHoraEmp, Utilizador utilizador, CopiaEBook copiaEBook) {
        this.id_emp = id_emp;
        this.dataHoraEmp = dataHoraEmp;
        this.fimdataHoraEmp = fimdataHoraEmp;
        this.utilizador = utilizador;
        this.copiaEBook = copiaEBook;
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

    public CopiaEBook getCopia() {
        return copiaEBook;
    }

    public void setCopia(CopiaEBook copiaEBook) {
        this.copiaEBook = copiaEBook;
    }

    public int getExtensaoEmprestimo() {
        return extensaoEmprestimo;
    }

    public void extenderEmprestimo() throws ExtensaoEmprestimoException {
        if (extensaoEmprestimo < 0 || extensaoEmprestimo > 2){
            System.out.println("Chegou ao limite de extensoes de emprestimo !!!");
            throw new ExtensaoEmprestimoException("Chegou ao limite de extensoes de emprestimo !!!");
        }
        extensaoEmprestimo++;
    }
}
