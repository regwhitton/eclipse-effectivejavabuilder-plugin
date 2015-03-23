package effectivejava_builder_proposal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

public final class EffectiveJavaBuilderProposalComputer implements IJavaCompletionProposalComputer {

	// a default constructor is required by Eclipse
	public EffectiveJavaBuilderProposalComputer() {
	}

	/** returns a list with one element which is an anonymous class */
	@Override
	public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context,
			IProgressMonitor monitor) {

		List<ICompletionProposal> res = new ArrayList<ICompletionProposal>();
		res.add(new BuilderCompletionProposal(context));
		res.add(new BuilderWithValidationCompletionProposal(context));
		return res;

	}

	@Override
	public List<IContextInformation> computeContextInformation(ContentAssistInvocationContext context,
			IProgressMonitor monitor) {
		return new ArrayList<IContextInformation>();
	}

	@Override
	public String getErrorMessage() {
		return "error during code completion";
	}

	@Override
	public void sessionStarted() {
		// Informs the completion system that a content assist session has
		// started. This call will always be followed by a sessionEnded() call,
		// but not necessarily by calls to computeCompletionProposals or
		// computeContextInformation.
	}

	@Override
	public void sessionEnded() {
		// Informs the completion system that a content assist session has
		// ended.
	}
}
