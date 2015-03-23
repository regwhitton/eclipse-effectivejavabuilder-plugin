package effectivejava_builder_proposal;

import java.util.List;

public class BuilderFormatter {

	private final StringBuilder buffer = new StringBuilder();
	protected final String className;
	protected final List<Field> fields;
	protected int indent = 1;

	public BuilderFormatter(String className, List<Field> fields) {
		this.className = className;
		this.fields = fields;
	}

	public String format() {
		newline();
		addBuilder();
		newline();
		addConstructor();
		addValidationMethod();
		return buffer.toString();
	}

	private void addBuilder() {
		line("public static class Builder {");
		indent++;
		for (Field field : fields) {
			addBuilderField(field);
		}
		newline();
		for (Field field : fields) {
			addBuilderSetter(field);
			newline();
		}
		addBuilderBuildMethod();
		indent--;
		line("}");
	}

	private void addBuilderField(Field field) {
		line("private " + field.type + " " + field.name + ";");
	}

	private void addBuilderSetter(Field field) {
		line("public Builder " + field.name + "(" + field.type + " " + field.name + ") {");
		indent++;
		line("this." + field.name + " = " + field.name + ";");
		line("return this;");
		indent--;
		line("}");
	}

	private void addBuilderBuildMethod() {
		line("public " + className + " build() {");
		indent++;
		line("return new " + className + "(this);");
		indent--;
		line("}");
	}

	private void addConstructor() {
		line("private " + className + " (Builder builder) {");
		indent++;
		for (Field field : fields) {
			line("this." + field.name + " = builder." + field.name + ";");
		}
		addValidationMethodReference();
		indent--;
		line("}");
	}

	protected void addValidationMethodReference() {
	}

	protected void addValidationMethod() {
	}

	protected void line(String text) {
		for (int i = 0; i < indent; i++) {
			buffer.append("    ");
		}
		buffer.append(text);
		buffer.append("\n");
	}

	protected void newline() {
		buffer.append("\n");
	}
}
