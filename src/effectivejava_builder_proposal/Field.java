package effectivejava_builder_proposal;

class Field {
	final String type;
	final String name;
	final boolean isPrimative;
	final boolean isBoolean;
	final boolean isString;

	Field(String type, String name, boolean isPrimative, boolean isBoolean, boolean isString) {
		this.type = type;
		this.name = name;
		this.isPrimative = isPrimative;
		this.isBoolean = isBoolean;
		this.isString = isString;
	}
}