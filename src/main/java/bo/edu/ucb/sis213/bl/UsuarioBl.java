package bo.edu.ucb.sis213.bl;

import bo.edu.ucb.sis213.dao.UsuariosDao;
import bo.edu.ucb.sis213.until.Usuario;

public class UsuarioBl {
    int intentos;
    public Usuario usuario;
    UsuariosDao usuariosDao;
    public UsuarioBl(){
        intentos=3;
        usuario= new Usuario(null, null, 0,0,0);
        usuariosDao= new UsuariosDao();
    }
    public String login(String alias, int pin){
        String res="";
        if(intentos>0){
			if(alias!=null && pin!=0){
                usuario = usuariosDao.verificarUsuario(pin, alias);
                if( usuario.id !=0){
                    res="SI";
                    return res;
                }else{
                    intentos--;
                    res="Usuario o contraseña incorrectos, le quedan ";
                    res+=intentos;
                    res+=" intentos.";
                    return res;
                }
            }else{
                res="Ingrese los parámetros solicitados.";
                return res;
            }
        }else{
            res = "Número de intentos agotados.";
        }
        return res;
    }
    public double consultarSaldo(){
        double saldoActual = usuariosDao.consultarSaldo(usuario.id);
        usuario.saldo=saldoActual;
        return saldoActual;
    }
    public double depositar(double monto){
        /*
         * 1 -> cantidad no valida
         * saldo_actual -> Deposito realizado con exito
         * 3 -> no se pudo realizar el deposito
         */
        double saldo_actual= usuariosDao.consultarSaldo(usuario.id);
        if(monto>0){
            if(usuariosDao.actualizarSaldo(usuario.id, monto, "Deposito")){
                saldo_actual= usuariosDao.consultarSaldo(usuario.id);
                return saldo_actual;
            }else{
                return 3;
            }
		}else{
		    return 1;
		}
    }
    public double retirar(double monto){
        /*
         * 1 -> cantidad no valida
         * saldo_actual -> Deposito realizado con exito
         * 2 -> saldo insuficiente
         * 3 -> no se pudo realizar el deposito
         */
        double saldo_actual= usuariosDao.consultarSaldo(usuario.id);
        if(monto>0){
            if(saldo_actual>=monto){
                if(usuariosDao.actualizarSaldo(usuario.id, monto, "Retiro")){
                    saldo_actual= usuariosDao.consultarSaldo(usuario.id);
                    return saldo_actual;
                }else{
                    return 3;
                }
            }else{
                return 2;
            }
		}else{
		    return 1;
		}
    }
    public int confirmarPassword(int pinIngresado){
        /*
         * 1 -> ingrese su pin
         * 2 -> pin confirmado con exito
         * 3 -> pin no igual
         */
        if(pinIngresado!=0){
            if(this.usuario.password ==pinIngresado){
                return 2;
            }else{
                return 3;
            }
        }else
        return 1;
    }
    public int cambiarPassword(int nuevoPin, int pinConf){
        /*
         * 1 -> campos vacios
         * 2 -> pines no coinciden
         * 3 -> pin no se pudo cambiar
         * 4 -> pin cambiado con exito
         */
        if(nuevoPin!=0 && pinConf!=0){
			if(nuevoPin==pinConf){
                if(usuariosDao.cambiarPassword(nuevoPin, usuario.id)){
                    usuario.password=nuevoPin;
                    return 4;
                }else{
                    return 3;
                }
            }else{
                return 2;
            }
		}else{
            return 1;
        }
    }
}
