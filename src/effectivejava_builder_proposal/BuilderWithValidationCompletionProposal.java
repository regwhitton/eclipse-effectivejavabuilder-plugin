package effectivejava_builder_proposal;

import java.util.List;

import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;

public class BuilderWithValidationCompletionProposal extends BuilderCompletionProposal {

	public BuilderWithValidationCompletionProposal(ContentAssistInvocationContext context) {
		super(context);
	}

	@Override
	protected BuilderFormatter createBuilderFormatter(String typeName, List<Field> fields) {
		return new BuilderWithValidationFormatter(typeName, fields);
	}

	@Override
	public String getDisplayString() {
		// this is the label shown in the completion list
		return "builder class with validation";
	}

	@Override
	public String getAdditionalProposalInfo() {
		// this is the additional piece of information shown when
		// selected
		return "This inserts an internal builder class as described in Effective Java, "
				+ "which also proposes validation of fields.";
	}
}
