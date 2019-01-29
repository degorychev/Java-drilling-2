package lab3;

import java.awt.Color;
import java.util.GregorianCalendar;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

public class AutoAdd {

	public automobil output;
	public Boolean ok;
	protected Shell shell;
	private Text zavod;
	private Text model;
	private Text nomer;

	
	Color selectColor = new Color(255, 255, 255);
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AutoAdd window = new AutoAdd();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		ok=false;
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
		shell.setSize(301, 248);
		shell.setText("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		shell.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u0417\u0430\u0432\u043E\u0434--\u0438\u0437\u0433\u043E\u0442\u043E\u0432\u0438\u0442\u0435\u043B\u044C");
		
		zavod = new Text(shell, SWT.BORDER);
		zavod.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("\u041C\u043E\u0434\u0435\u043B\u044C");
		
		model = new Text(shell, SWT.BORDER);
		model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("\u0414\u0430\u0442\u0430 \u0432\u044B\u043F\u0443\u0441\u043A\u0430");
		
		DateTime dateVipusk = new DateTime(shell, SWT.BORDER);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("\u041C\u043E\u0449\u043D\u043E\u0441\u0442\u044C \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043B\u044F");
		
		Spinner Power = new Spinner(shell, SWT.BORDER);
		Power.setMaximum(1000);
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("\u0426\u0432\u0435\u0442");
		
		Button SelectColorButton = new Button(shell, SWT.NONE);
		SelectColorButton.setBackground(new org.eclipse.swt.graphics.Color(null, new RGB(255, 255, 255)));
		SelectColorButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			    ColorDialog dlg = new ColorDialog(shell);
			    dlg.setRGB(new RGB(0, 0, 255));
			    RGB rgb = dlg.open();
			    SelectColorButton.setBackground(new org.eclipse.swt.graphics.Color(null, rgb));
			    selectColor = new Color(rgb.red, rgb.green, rgb.blue);
			}
		});
		SelectColorButton.setText("\u0412\u044B\u0431\u0440\u0430\u0442\u044C");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("\u041D\u043E\u043C\u0435\u0440");
		
		nomer = new Text(shell, SWT.BORDER);
		nomer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button SaveButton = new Button(shell, SWT.NONE);
		SaveButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				output = new automobil(zavod.getText(), model.getText(), new GregorianCalendar(dateVipusk.getYear(), dateVipusk.getMonth(), dateVipusk.getDay()), Power.getSelection(), selectColor, nomer.getText());
				ok = true;
				shell.close();
			}
		});
		SaveButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		SaveButton.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		
		Button CancelButton = new Button(shell, SWT.NONE);
		CancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ok = false;
				shell.close();
			}
		});
		CancelButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		CancelButton.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");

	}

}
