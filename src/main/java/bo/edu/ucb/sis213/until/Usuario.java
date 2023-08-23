package bo.edu.ucb.sis213.until;

public class Usuario {
    public String usuario;
    public String nombre;
    public int password;
    public double saldo;
    public int id;
    public Usuario(String usuario,String nombre, int password, double saldo, int id){
        this.usuario=usuario;
        this.nombre=nombre;
        this.password=password;
        this.saldo=saldo;
        this.id=id;
    }
}
