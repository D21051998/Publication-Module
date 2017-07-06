package com.publication.constants;

public enum Roles {

	ROLE_ADMIN("ROLE_ADMIN"), ROLE_DC_CSE("ROLE_DC_CSE"), ROLE_DC_ECE("ROLE_DC_ECE"), ROLE_RDIL("ROLE_RDIL");

	private final String text;

	private Roles(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public static void main(String[] args) {
		System.out.println(Roles.ROLE_ADMIN);
	}
	
}
