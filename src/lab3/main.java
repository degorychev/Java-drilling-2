package lab3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.text.SimpleDateFormat;

import com.ibm.icu.util.Calendar;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.awt.Color;
import java.util.GregorianCalendar;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class main {

	protected Shell shell;
	private Table table;

	static ArrayList<automobil> SpisokAuto = new ArrayList<automobil>();

	public static void main(String[] args) {
		try {
			main window = new main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
		shell.setSize(810, 349);
		shell.setText("\u041A\u0430\u0442\u0430\u043B\u043E\u0433 \u0430\u0432\u0442\u043E\u043C\u043E\u0431\u0438\u043B\u0435\u0439");
		shell.setLayout(new GridLayout(3, false));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn column1 = new TableColumn(table, SWT.LEFT);
	    TableColumn column2 = new TableColumn(table, SWT.LEFT);
	    TableColumn column3 = new TableColumn(table, SWT.LEFT);
	    TableColumn column4 = new TableColumn(table, SWT.LEFT);
	    TableColumn column5 = new TableColumn(table, SWT.LEFT);
	    TableColumn column6 = new TableColumn(table, SWT.LEFT);
	    column1.setText("Завод--изготовитель");
	    column2.setText("Модель");
	    column3.setText("Дата выпуска");
	    column4.setText("Мощность двигателя");
	    column5.setText("Цвет");
	    column6.setText("Номер");
	    column1.setWidth(150);
	    column2.setWidth(100);
	    column3.setWidth(100);
	    column4.setWidth(100);
	    column5.setWidth(100);
	    column6.setWidth(100);
	    table.setHeaderVisible(true);

		
		Button AddButton = new Button(shell, SWT.NONE);
		AddButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AutoAdd aadd = new AutoAdd();
				aadd.open();
				
				if (aadd.ok)
					SpisokAuto.add(aadd.output);
				updateTable();
			}
		});
		AddButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
		AddButton.setText("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		
		Button DeleteButton = new Button(shell, SWT.NONE);
		DeleteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				SpisokAuto.remove(table.getSelectionIndex());
				updateTable();
			}
		});
		DeleteButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
		DeleteButton.setText("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		
		Button SearchButton = new Button(shell, SWT.NONE);
		SearchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				SearchForm SF = new SearchForm();
				SF.open();
				if(SF.ok){
					table.removeAll();
					for(automobil auto1 : SpisokAuto){
						if(auto1.zavod.equals(SF.output)) {
							TableItem item1 = new TableItem(table, SWT.NONE);
							SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
							item1.setText(new String[] { auto1.zavod, auto1.model, df.format(auto1.Vipusk.getTime()), String.valueOf(auto1.power), auto1.colorauto.toString(), auto1.Nomer });
						}
			        }
				}
				else {
					updateTable();
				}
			}
		});
		SearchButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
		SearchButton.setText("\u041F\u043E\u0438\u0441\u043A");
		
		Button SFbutton = new Button(shell, SWT.NONE);
		SFbutton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				SuperFunction SF = new SuperFunction();
				SF.open(SpisokAuto);
			}
		});
		SFbutton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 2, 1));
		SFbutton.setText("\u0421\u0443\u043F\u0435\u0440\u0424\u0443\u043D\u043A\u0446\u0438\u044F");
		
		Button SaveButton = new Button(shell, SWT.NONE);
		SaveButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//Export();
				ExportXml();
			}
		});
		SaveButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		SaveButton.setText("\u042D\u043A\u0441\u043F\u043E\u0440\u0442");
		
		Button LoadButton = new Button(shell, SWT.NONE);
		LoadButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//Import();
				
				try {
					importXml();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoadButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		LoadButton.setText("\u0418\u043C\u043F\u043E\u0440\u0442");

	}
	
	private void Export() {
		try(FileWriter writer = new FileWriter("notes.txt", false))
        {
			for(automobil auto1 : SpisokAuto){
				 String text = auto1.zavod+";"+auto1.model+";"+auto1.Vipusk.get(Calendar.YEAR)+","+(auto1.Vipusk.get(Calendar.MONTH)+1)+","+auto1.Vipusk.get(Calendar.DAY_OF_MONTH)+";"+String.valueOf(auto1.power)+";"+String.valueOf(auto1.colorauto.getRGB())+";"+auto1.Nomer+"|";
		            writer.write(text);
		            writer.flush();
			}		             
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
	}
	
	private void Import() {
		try(FileReader reader = new FileReader("notes.txt")){
			SpisokAuto.clear();
			int c;
			String auto = "";
			while((c=reader.read())!=-1){
				if((char)c != '|')
					auto+=(char)c;
				else {
					String[] bufer = auto.split(";");
					String[] data = bufer[2].split(",");
					automobil auto1 = new automobil(bufer[0], bufer[1], new GregorianCalendar(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])), Integer.parseInt(bufer[3]), new Color(Integer.parseInt(bufer[4])), bufer[5]);
					SpisokAuto.add(auto1);
					auto = "";
				}
			} 
			updateTable();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	private void importXml() throws ParserConfigurationException, SAXException, IOException  {
		SpisokAuto.clear();
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("autos.xml"), handler);
        updateTable();
	}
	
	
	private void ExportXml() {
		try {

		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		    // root elements
		    Document doc = docBuilder.newDocument();
		    Element rootElement = doc.createElement("autoList");
		    doc.appendChild(rootElement);

		    

			for(automobil auto1 : SpisokAuto){
		    // staff elements
			    Element staff = doc.createElement("auto");
			    rootElement.appendChild(staff);
			    staff.setAttribute("name", auto1.zavod);
			    staff.setAttribute("model", auto1.model);
			    staff.setAttribute("YearOfBirth", String.valueOf(auto1.Vipusk.get(Calendar.YEAR)));
			    staff.setAttribute("MonthOfBirth", String.valueOf(auto1.Vipusk.get(Calendar.MONTH)));
			    staff.setAttribute("DayOfBirth", String.valueOf(auto1.Vipusk.get(Calendar.DAY_OF_MONTH)));
			    staff.setAttribute("Power", String.valueOf(auto1.power));
			    staff.setAttribute("Color", String.valueOf(auto1.colorauto.getRGB()));
			    staff.setAttribute("Nomer", auto1.Nomer);
			}

		    // write the content into xml file
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File("autos.xml"));

		    // Output to console for testing
		    // StreamResult result = new StreamResult(System.out);

		    transformer.transform(source, result);


		  } catch (ParserConfigurationException pce) {
		    pce.printStackTrace();
		  } catch (TransformerException tfe) {
		    tfe.printStackTrace();
		  }
	}
	
	private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("auto")) {
                String name = attributes.getValue("name");
                String model = attributes.getValue("model");
                String YearOfBirth = attributes.getValue("YearOfBirth");
                String MonthOfBirth = attributes.getValue("MonthOfBirth");
                String DayOfBirth = attributes.getValue("DayOfBirth");
                String Power = attributes.getValue("Power");
                String Color = attributes.getValue("Color");
                String Nomer = attributes.getValue("Nomer");
                
                GregorianCalendar GC = new GregorianCalendar(Integer.parseInt(YearOfBirth), Integer.parseInt(MonthOfBirth), Integer.parseInt(DayOfBirth));
                Color clr = new Color(Integer.parseInt(Color));
                
                automobil auto1 = new automobil(name, model, GC, Integer.parseInt(Power), clr, Nomer);
                SpisokAuto.add(auto1);
				
            }
        }
        
        
    }
	
	
	private void updateTable() {
		table.removeAll();
		for(automobil auto1 : SpisokAuto){
			TableItem item1 = new TableItem(table, SWT.NONE);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			item1.setText(new String[] { auto1.zavod, auto1.model, df.format(auto1.Vipusk.getTime()), String.valueOf(auto1.power), auto1.colorauto.toString(), auto1.Nomer });
			item1.setBackground(new org.eclipse.swt.graphics.Color(null, auto1.colorauto.getRed(),auto1.colorauto.getGreen(),auto1.colorauto.getBlue()));
        }

	}
}

