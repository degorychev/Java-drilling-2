package lab3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchForm {

	protected Shell shell;
	private Text SearchText;
	
	public boolean ok;
	public String output;


	public static void main(String[] args) {
		try {
			SearchForm window = new SearchForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		ok = false;
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(379, 122);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("\u0417\u0430\u0432\u043E\u0434-\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C =");
		
		SearchText = new Text(shell, SWT.BORDER);
		GridData gd_SearchText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_SearchText.heightHint = 33;
		gd_SearchText.widthHint = 201;
		SearchText.setLayoutData(gd_SearchText);
		
		Button SearchButton = new Button(shell, SWT.NONE);
		SearchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ok=true;
				output = SearchText.getText();
				shell.close();
			}
		});
		SearchButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		SearchButton.setText("\u041F\u043E\u0438\u0441\u043A");
		
		Button CancelButton = new Button(shell, SWT.NONE);
		CancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ok = false;
				shell.close();
			}
		});
		CancelButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		CancelButton.setText("\u041E\u0442\u043C\u0435\u043D\u0430");

	}
}
