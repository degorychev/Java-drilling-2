package lab3;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Spinner;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;

public class SuperFunction {

	protected Shell shlC;

	

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(ArrayList<automobil> Spisok) {
		Display display = Display.getDefault();
		createContents(Spisok);
		shlC.open();
		shlC.layout();
		while (!shlC.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents(ArrayList<automobil> Spisok) {
		shlC = new Shell();
		shlC.setSize(350, 300);
		shlC.setText("C\u0440\u0435\u0434\u043D\u0435\u0435 \u0430\u0440\u0438\u0444\u043C\u0435\u0442\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043F\u043E \u0432\u043E\u0437\u0440\u0430\u0441\u0442\u0443");
		shlC.setLayout(new GridLayout(3, false));
		
		Label label = new Label(shlC, SWT.NONE);
		label.setText("\u0412\u0445\u043E\u0434\u043D\u044B\u0435 \u0434\u0430\u043D\u043D\u044B\u0435:");
		new Label(shlC, SWT.NONE);
		new Label(shlC, SWT.NONE);
		
		List list = new List(shlC, SWT.BORDER);
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_list.widthHint = 159;
		gd_list.heightHint = 230;
		list.setLayoutData(gd_list);
		
		Label label_1 = new Label(shlC, SWT.NONE);
		label_1.setText("\u0421\u0440\u0435\u0434\u043D\u0435\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435:");
		
		Spinner output = new Spinner(shlC, SWT.BORDER);
		output.setMaximum(10000);
		
		int schet = 0;
		for(automobil auto1 : Spisok){
			Calendar today = new GregorianCalendar();
			long diff = today.getTimeInMillis() - auto1.Vipusk.getTimeInMillis();
	        // в секундах
	        long seconds = diff / 1000;
	        // в минутах
	        long minutes = seconds / 60;
	        // в часах
	        long hours = minutes / 60;
	        // в днях
	        long days = hours / 24;
			list.add(String.valueOf(days) + " Дней");
			schet += days;
        }
		output.setSelection(schet/list.getItemCount());

	}

}
