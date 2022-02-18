package com.itaitan.helpdesk.domain.enums;

public enum Prioridade {
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	private Integer codigo;
	
	private String descriscao;

	private Prioridade(Integer codigo, String descriscao) {
		this.codigo = codigo;
		this.descriscao = descriscao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescriscao() {
		return descriscao;
	}

	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválido");
	}
	
	
}
