package effectivejava_builder_proposal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.IDocument;

class EclipseContext {

	private final ContentAssistInvocationContext context;
	private final IDocument document;

	EclipseContext(ContentAssistInvocationContext context, IDocument document) {
		this.context = context;
		this.document = document;
	}

	public boolean isWithinType() {
		return getEnclosingElement().getElementType() == IJavaElement.TYPE;
	}

	public List<Field> getFieldsForEnclosingType() throws JavaModelException {
		List<Field> fields = new ArrayList<Field>();
		for (IField f : ((IType) getEnclosingElement()).getFields()) {
			if (!Flags.isStatic(f.getFlags())) {
				String t = f.getTypeSignature();
				boolean isPrimative = (t.length() == 1 && Character.isUpperCase(t.charAt(0)));
				boolean isBoolean = t.equals("Z");
				boolean isString = t.equals("Qjava.lang.String;") || t.equals("QString;");
				String typeName = Signature.toString(f.getTypeSignature());
				fields.add(new Field(typeName, f.getElementName(), isPrimative, isBoolean, isString));
			}
		}
		return fields;
	}

	public void insertTextAtCursor(String text) {
		int position = context.getViewer().getSelectedRange().x;
		String newContents = textBeforeCursor() + text + textAfterCursor();
		document.set(newContents);
		moveCursorTo(position + text.length());
	}

	public String typeName() {
		return getEnclosingElement().getElementName();
	}

	private IJavaElement getEnclosingElement() {
		JavaContentAssistInvocationContext jcontext = (JavaContentAssistInvocationContext) context;
		return jcontext.getCoreContext().getEnclosingElement();
	}

	private String textBeforeCursor() {
		return document.get().substring(0, context.getInvocationOffset());
	}

	private String textAfterCursor() {
		return document.get().substring(context.getInvocationOffset());
	}

	private void moveCursorTo(int position) {
		context.getViewer().setSelectedRange(position, -1);
	}
}
