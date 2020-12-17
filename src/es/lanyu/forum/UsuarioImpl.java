package es.lanyu.forum;

public class UsuarioImpl implements Usuario {

	private String user;

	public UsuarioImpl(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		return user;
	}
	
	
}
