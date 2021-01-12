package es.lanyu.forum;

public class UsuarioImpl implements Usuario {

	private String userName;

	public UsuarioImpl(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	@Override
	public String toString() {
		return userName;
	}

	
}
