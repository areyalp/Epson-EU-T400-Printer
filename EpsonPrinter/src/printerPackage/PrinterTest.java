package printerPackage;

import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class PrinterTest {
	
	public static void main(String[] args)
	{	
		PrinterOptions p=new PrinterOptions();

        p.resetAll();
        p.initialize();
        p.feedBack((byte)2);
        p.color(0);
        p.alignCenter();
        p.newLine();
        p.setText("Argenis es un gafo");
        p.newLine();
        p.setText("1234567890123456789012345678901234567890123456789012345678901234567890");
        p.newLine();
        p.addLineSeperator();
        p.setText("Probando");
        p.newLine();
        p.addLineSeperator();
        p.newLine();

        p.alignLeft();
        p.setText("POD No \t: 2001 \tTable \t: E511");
        p.newLine();


        p.setText("Res Date \t: " +  "01/01/1801 22:59");


        p.newLine();
        p.setText("Session \t: Evening Session");
        p.newLine();
        p.setText("Staff \t: Bum Dale");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignCenter();
        p.setText(" - Some Items - ");
        p.newLine();
        p.alignLeft();
        p.addLineSeperator();

        p.newLine();

        p.setText("No \tItem\t\tUnit\tQty");
        p.newLine();
        p.addLineSeperator();
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");
	    p.setText("1" + "\t" + "Aliens" + "\t\t" +  "Rats" + "\t" + "500" + "\n");

        p.addLineSeperator();
        p.feed((byte)3);
        String BarCode = new String("27-01-2016|10:55:00|1234567890|entrada1");
        p.barCode(BarCode);
        p.newLine();
        p.setText(BarCode);
        p.finit();

        feedPrinter(p.finalCommandSet().getBytes());
	}
	
	private static boolean feedPrinter(byte[] b)
    {
        try
        {
        	
            AttributeSet attrSet = new HashPrintServiceAttributeSet(new PrinterName("EPSON EU-T400 Receipt", Locale.getDefault())); //EPSON TM-U220 ReceiptE4


            DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();
                    //PrintServiceLookup.lookupDefaultPrintService().createPrintJob();  



        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(b, flavor, null);
        PrintJobWatcher pjDone = new PrintJobWatcher(job);

        job.print(doc, null);
        pjDone.waitForDone();
        System.out.println("Done !");
        }
        catch(javax.print.PrintException pex)
        {

            System.out.println("Printer Error " + pex.getMessage());
            return false;
        }
        catch(Exception e)
        {
        e.printStackTrace();
        return false;
        }


        return true;
    }


}
