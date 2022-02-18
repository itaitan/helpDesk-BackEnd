package com.itaitan.helpdesk.domain.enums;

public enum Status {
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	private Integer codigo;
	
	private String descriscao;

	private Status(Integer codigo, String descriscao) {
		this.codigo = codigo;
		this.descriscao = descriscao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescriscao() {
		return descriscao;
	}

	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido");
	}
	
	
}
