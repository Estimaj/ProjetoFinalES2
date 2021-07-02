import Aplicacao.*;
import Aplicacao.Exceptions.*;
import Aplicacao.stubDB.Database;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegration_DatabaseStub {

    private final Database db = new Database();
    private Utilizador u = new Utilizador(1,"Maria","maria@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
    private EBook eBook = new EBook(1,"akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",0.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1, eBook);
    private Emprestimo emp = new Emprestimo(1, LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook ,1);;
    private Funcionario func = new Funcionario(1,"Joao","Joao@exemplo.com","Abc1abcABC!");
    private ReplicaServidor rp = new ReplicaServidor(1,"Portugal");

    public TestIntegration_DatabaseStub() throws InvalidUserException, InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException, EmprestimoException, InvalidFuncException, InvalidReplicaException, InvalidCopiaEBookException {
    }

    @Test
    void saveUserInStubOK() {
        assertEquals(1,db.addUser(u));
    }

    @Test
    void saveUserInStubFalse() {
        assertEquals(-1,db.addUser(null));
    }

    @Test
    void removeUserInStubOK() {
        db.addUser(u);
        assertEquals(1,db.removeUser(1));
    }

    @Test
    void removeUserInStubFalse() {
        assertEquals(0,db.removeUser(-5));
    }

    @Test
    void getUserOK() {
        db.addUser(u);
        assertEquals(u,db.getUser(1));
    }

    @Test
    void getUserFalse() {
        assertNull(db.getUser(-8));
    }

    @Test
    void UpdateUserOK() throws InvalidUserException {
        db.addUser(u);
        assertEquals(1,db.updateUser(1,"Abc1abcABC..1","121-231-123"));
    }

    @Test
    void UpdateUserPwdNull() throws InvalidUserException {
        db.addUser(u);
        assertEquals(0,db.updateUser(1,null,"121-231-123"));
    }

    @Test
    void UpdateUserPwdEmpty() throws InvalidUserException {
        db.addUser(u);
        assertEquals(0,db.updateUser(1,"","121-231-123"));
    }

    @Test
    void UpdateUserTelefoneEmpty() throws InvalidUserException {
        db.addUser(u);
        assertEquals(0,db.updateUser(1,"Abc1abcABC..1",""));
    }

    @Test
    void UpdateUserTelefonenull() throws InvalidUserException {
        db.addUser(u);
        assertEquals(0,db.updateUser(1,"Abc1abcABC..1",null));
    }

    @Test
    void LoginOK() {
        db.addUser(u);
        assertEquals(u,db.Login("maria@exemplo.com","Abc1abcABC"));
    }

    @Test
    void LoginFalseEmailandPwdEmpty() {
        db.addUser(u);
        assertNull(db.Login("", ""));
    }

    @Test
    void LoginFalseEmailEmptyPwdOK() {
        db.addUser(u);
        assertNull(db.Login("", "Abc1abcABC"));
    }

    @Test
    void LoginFalseEmailOKPwdEmpty() {
        db.addUser(u);
        assertNull(db.Login("maria@exemplo.com", ""));
    }

    @Test
    void LoginEmailPwdCheckNullParams() {
        db.addUser(u);
        assertNull(db.Login("maria@exemplo.com", null));
        assertNull(db.Login(null, "Abc1abcABC"));
        assertNull(db.Login(null, null));
    }

    @Test
    void ListaOfUsers() throws InvalidUserException {
        u = new Utilizador(1,"maria","maria@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
        db.addUser(u);
        u = new Utilizador(2,"ines","ines@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
        db.addUser(u);
        JSONObject jsonObject = db.ListaOfUsers();
        //System.out.println(jsonObject);

        assertTrue(jsonObject.has("1"));
        assertTrue(jsonObject.has("2"));
    }

    @Test
    void ListaOfUsersEmpty() {
        JSONObject jsonObject = db.ListaOfUsers();
        assertEquals(0,jsonObject.length());
    }

    //----------------------------------
    @Test
    void saveEBookInStubOK() {
        assertEquals(1,db.addEBook(eBook));
    }

    @Test
    void saveEBookInStubFalse() {
        assertEquals(-1,db.addEBook(null));
    }

    @Test
    void getEBookOK() {
        db.addEBook(eBook);
        assertEquals(eBook,db.getEBook(1));
    }

    @Test
    void getEBookFalse() {
        assertNull(db.getEBook(-1));
    }

    @Test
    void ListaOfEBooks() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        eBook = new EBook(1,"akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",0.f,"Stephen king sig");
        db.addEBook(eBook);
        eBook = new EBook(2,"akjshdahq123123","Lauren","The Haunting","Ray Lovejoy","pdf",0.f,"Lauren king sig");
        db.addEBook(eBook);
        JSONObject jsonObject = db.ListarEBooks();


        assertTrue(jsonObject.has("1"));
        assertTrue(jsonObject.has("2"));
    }

    @Test
    void ListaOfEBooksEmpty() {
        JSONObject jsonObject = db.ListarEBooks();
        assertEquals(0,jsonObject.length());
    }

    @Test
    void removeEBookInStubOK() {
        db.addEBook(eBook);
        assertEquals(1,db.removeEBook(1));
    }

    @Test
    void removeEBookInStubFalse() {
        assertEquals(0,db.removeEBook(-1));
    }

    //----------------------------------
    @Test
    void saveEmprestimoInStubOK() {
        assertEquals(1,db.addEmprestimo(emp));
    }

    @Test
    void saveEmprestimoInStubFalse() {
        assertEquals(-1,db.addEmprestimo(null));
    }

    @Test
    void getEmprestimoOK() {
        db.addEmprestimo(emp);
        assertEquals(emp,db.getEmprestimo(1));
    }

    @Test
    void getEmprestimoFalse() {
        assertNull(db.getEmprestimo(-10));
    }

    @Test
    void getEBookFromEmprestimoOK() {
        db.addEmprestimo(emp);
        assertEquals(eBook,db.getEBookFromEmprestimo(1));
    }

    @Test
    void getEBookFromEmprestimoWhenEmprestimoDoesntExist() {
        db.addEmprestimo(emp);
        assertNull(db.getEBookFromEmprestimo(-20));
    }

    @Test
    void ListarEmprestimoByUser() throws InvalidUserException, EmprestimoException {
        u = new Utilizador(1,"Maria","maria@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo emp = new Emprestimo(1, LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook ,1);
        db.addEmprestimo(emp);
        emp = new Emprestimo(2, LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook ,1);
        db.addEmprestimo(emp);
        JSONObject jsonObject = db.ListarEmprestimoByUser(1);

        assertTrue(jsonObject.has("1"));
        assertTrue(jsonObject.has("2"));
    }

    @Test
    void ListarEmprestimoByUserWhenIdDoesntExist() throws InvalidUserException, EmprestimoException {
        u = new Utilizador(1,"Maria","maria@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo emp = new Emprestimo(1, LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook,1);
        db.addEmprestimo(emp);
        emp = new Emprestimo(2, LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook,1);
        db.addEmprestimo(emp);
        JSONObject jsonObject = db.ListarEmprestimoByUser(0);

        assertEquals(0,jsonObject.length());
    }

    @Test
    void getUserFromEmprestimoOK() {
        db.addEmprestimo(emp);
        assertEquals(u,db.getUtilizadorFromEmprestimo(1));
    }

    @Test
    void getUserFromEmprestimoWhenEmprestimoDoesntExist() {
        db.addEmprestimo(emp);
        assertNull(db.getUtilizadorFromEmprestimo(-20));
    }

    @Test
    void getReplicaFromEmprestimoOK() throws EmprestimoException {
        db.addEmprestimo(emp);
        emp.setReplicaServidor(rp);
        assertEquals(rp,db.getReplicaFromEmprestimo(1));
    }

    @Test
    void getReplicaFromEmprestimoWhenEmprestimoDoesntExist() throws EmprestimoException {
        db.addEmprestimo(emp);
        emp.setReplicaServidor(rp);
        assertNull(db.getReplicaFromEmprestimo(-20));
    }

    @Test
    void getAssinaturaTRFromEmprestimoOK() {
        db.addEmprestimo(emp);
        assertEquals(1,db.getAssinaturaTR(1));
    }

    @Test
    void getAssinaturaTRFromEmprestimoWhenEmprestimoDoesntExist() {
        db.addEmprestimo(emp);
        assertEquals(-1,db.getAssinaturaTR(-20));
    }

    @Test
    void removeEmprestimoInStubOK() {
        db.addEmprestimo(emp);
        assertEquals(1,db.removeEmprestimo(1));
    }

    @Test
    void removeEmprestimoInStubFalse() {
        assertEquals(0,db.removeEmprestimo(-10));
    }

    //------------
    @Test
    void saveFuncInStubOK() {
        assertEquals(1,db.addFuncionario(func));
    }

    @Test
    void saveFuncInStubFalse() {
        assertEquals(-1,db.addFuncionario(null));
    }

    @Test
    void removeFuncInStubOK() {
        db.addFuncionario(func);
        assertEquals(1,db.removeFuncionario(1));
    }

    @Test
    void removeFuncInStubFalse() {
        assertEquals(0,db.removeFuncionario(-5));
    }

    @Test
    void getFuncOK() {
        db.addFuncionario(func);
        assertEquals(func,db.getFuncionario(1));
    }

    @Test
    void getFuncFalse() {
        assertNull(db.getFuncionario(-8));
    }

    @Test
    void LoginFuncOK() {
        db.addFuncionario(func);
        assertEquals(func,db.LoginFuncionario("Joao@exemplo.com","Abc1abcABC!"));
    }

    @Test
    void LoginFuncFalseEmailandPwdEmpty() {
        db.addFuncionario(func);
        assertNull(db.LoginFuncionario("", ""));
    }

    @Test
    void LoginFuncFalseEmailNullPwdOK() {
        db.addFuncionario(func);
        assertNull(db.LoginFuncionario("", "Abc1abcABC!"));
    }

    @Test
    void LoginFuncFalseEmailOKPwdEmpty() {
        db.addFuncionario(func);
        assertNull(db.LoginFuncionario("Joao@exemplo.com", ""));
    }

    @Test
    void LoginFuncEmailPwdCheckNullParams() {
        db.addFuncionario(func);
        assertNull(db.LoginFuncionario("Joao@exemplo.com", null));
        assertNull(db.LoginFuncionario(null, "Abc1abcABC!"));
        assertNull(db.LoginFuncionario(null, null));
    }
    //----------------------------------
    @Test
    void saveReplicaInStubOK() {
        assertEquals(1,db.addReplicaServidor(rp));
    }

    @Test
    void saveReplicaInStubFalse() {
        assertEquals(-1,db.addReplicaServidor(null));
    }

    @Test
    void getReplicaOK() {
        db.addReplicaServidor(rp);
        assertEquals(rp,db.getReplicaServidor(1));
    }
    @Test
    void getReplicaFalse() {
        assertNull(db.getReplicaServidor(-10));
    }

    @Test
    void removeReplicaInStubOK() {
        db.addReplicaServidor(rp);
        assertEquals(1,db.removeReplicaServidor(1));
    }

    @Test
    void removeReplicaInStubFalse() {
        assertEquals(0,db.removeReplicaServidor(-5));
    }

    @Test
    void getCopiasFromReplicasOK() throws InvalidCopiaEBookException, InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        EBook eBook_Lusiadas = new EBook(1,"akjshdahq123123","Stephen King","The Haunting","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
        CopiaEBook copiaEBook_lusiadas = new CopiaEBook(2,eBook_Lusiadas);
        rp.addCopiaEBook(copiaEBook);
        rp.addCopiaEBook(copiaEBook_lusiadas);
        db.addReplicaServidor(rp);

        JSONObject jsonObject = db.getCopiaFromReplica(1);

        assertTrue(jsonObject.has("1"));
        assertTrue(jsonObject.has("2"));

    }

}
