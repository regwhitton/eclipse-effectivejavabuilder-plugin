package effectivejava_builder_proposal;

import java.util.List;

public class BuilderWithValidationFormatter extends BuilderFormatter {

	public BuilderWithValidationFormatter(String className, List<Field> fields) {
		super(className, fields);
	}

	@Override
	protected void addValidationMethodReference() {
		line("validate();");
	}

	@Override
	protected void addValidationMethod() {
		newline();
		line("private void validate() {");
		indent++;
		line("// TODO - ensure that either valid instances are created or that IllegalStateException is thrown.");
		line("// final String className = \"" + className + "\";");
		for (Field field : fields) {
			addValidateField(field);
		}
		indent--;
		line("}");
	}

	private void addValidateField(Field field) {
		if (field.isBoolean) {
			line("// Validate.isTrue(" + field.name + ", \"" + field.name + "\", className);");
		} else if (field.isPrimative) {
			line("// Validate.between(" + field.name + ", \"" + field.name + "\", className, MIN, MAX);");
		} else if (field.isString) {
			line("// Validate.notEmpty(" + field.name + ", \"" + field.name + "\", className);");
		} else {
			line("// Validate.provided(" + field.name + ", \"" + field.name + "\", className);");
		}
	}
}
