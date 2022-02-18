package com.itaitan.helpdesk.domain.enums;

public enum Perfil {
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	private Integer codigo;
	
	private String descriscao;

	private Perfil(Integer codigo, String descriscao) {
		this.codigo = codigo;
		this.descriscao = descriscao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescriscao() {
		return descriscao;
	}

	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}
	
	
}
