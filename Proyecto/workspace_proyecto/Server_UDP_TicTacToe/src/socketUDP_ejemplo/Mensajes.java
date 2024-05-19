package socketUDP_ejemplo;

public class Mensajes {

	static String tipo;

	static class MensajeComprobarConexion {
		String tipo;

		public MensajeComprobarConexion() {
			this.tipo = "comprobar_conexion";
		}
	}

	static class MensajeComprobarConexion_ping {
		String tipo;
		String mensaje;

		public MensajeComprobarConexion_ping() {
			this.tipo = "cliente_ping_servidor";
			this.mensaje = "sigo conectado";
		}
	}

	static class MensajeDesconexion {
		String tipo;

		public MensajeDesconexion() {
			this.tipo = "desconexion";
		}
	}

	static class MensajeEmailIncorrecto {
		String tipo;

		public MensajeEmailIncorrecto() {
			this.tipo = "Email incorrecto";
		}
	}

	static class MensajeUsuarioExistente {
		String tipo;

		public MensajeUsuarioExistente() {
			this.tipo = "Usuario existente";
		}
	}

	static class MensajeUsuarioNoExistente {
		String tipo;

		public MensajeUsuarioNoExistente() {
			this.tipo = "Usuario no existente";
		}
	}

	static class MensajePasswordIncorrecta {
		String tipo;

		public MensajePasswordIncorrecta() {
			this.tipo = "Contraseña incorrecta";
		}
	}

	static class MensajeLogin {
		String tipo, email, pwd_hash;

		public MensajeLogin(String email, String pwd_hash) {
			this.tipo = "Login";
			this.email = email;
			this.pwd_hash = pwd_hash;
		}
	}

	static class MensajeLoginCorrecto {
		String tipo;

		public MensajeLoginCorrecto() {
			this.tipo = "LoginCorrecto";
		}
	}

	static class MensajeRegister {
		String tipo, email, pwd_hash;

		public MensajeRegister(String email, String pwd_hash) {
			this.tipo = "Register";
			this.email = email;
			this.pwd_hash = pwd_hash;
		}
	}

	static class MensajeRegisterCorrecto {
		String tipo;

		public MensajeRegisterCorrecto() {
			this.tipo = "RegisterCorrecto";
		}
	}

	static class MensajeRegisterIncorrecto {
		String tipo;

		public MensajeRegisterIncorrecto() {
			this.tipo = "RegisterIncorrecto";
		}
	}

	static class MensajeGet_Riot_ApiKey {
		String tipo;

		public MensajeGet_Riot_ApiKey() {
			this.tipo = "Get_Riot_ApiKey";
		}
	}

	static class MensajeUpdate_Riot_ApiKey {
		String tipo, Riot_ApiKey;

		public MensajeUpdate_Riot_ApiKey(String Riot_ApiKey) {
			this.tipo = "Riot_ApiKey_Update";
			this.Riot_ApiKey = Riot_ApiKey;
		}
	}

}
