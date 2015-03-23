package effectivejava_builder_proposal;

import java.util.List;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;

public class BuilderCompletionProposal implements ICompletionProposal {

	private ContentAssistInvocationContext context;

	public BuilderCompletionProposal(ContentAssistInvocationContext context) {
		this.context = context;
	}

	/** Inserts the proposed completion at the cursor position */
	@Override
	public void apply(IDocument document) {
		EclipseContext ctx = new EclipseContext(context, document);
		try {
			if (ctx.isWithinType()) {
				List<Field> fields = ctx.getFieldsForEnclosingType();
				String builderSrc = createBuilderFormatter(ctx.typeName(), fields).format();
				ctx.insertTextAtCursor(builderSrc);
			}
		} catch (JavaModelException ex) {
			ctx.insertTextAtCursor(ex.getMessage());
		}
	}

	protected BuilderFormatter createBuilderFormatter(String typeName, List<Field> fields) {
		return new BuilderFormatter(typeName, fields);
	}

	@Override
	public String getDisplayString() {
		// this is the label shown in the completion list
		return "builder class";
	}

	@Override
	public String getAdditionalProposalInfo() {
		// this is the additional piece of information shown when
		// selected
		return "This inserts an internal builder class as described in Effective Java.";
	}

	@Override
	public IContextInformation getContextInformation() {
		// @return the context information for this proposal or
		// <code>null</code>
		return null;
	}

	@Override
	public org.eclipse.swt.graphics.Point getSelection(IDocument document) {
		// If it returns * <code>null</code>, no new selection is set.
		return null;
	}

	@Override
	public Image getImage() {
		// @return the image to be shown or <code>null</code> if no
		// image is desired
		return null;
	}
}
