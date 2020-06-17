package org.sid.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import org.sid.dto.FactureDto;
import org.sid.dto.TacheDto;
import org.sid.entities.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(FactureDto factureDto) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// ajout text au fichier pdf
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Facture numéro"+factureDto.getRef(), font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			//-------------------------------Text---------------------------------------------

		    PdfPTable table0;
		    PdfPCell cell;

		    table0 = new PdfPTable(4);
		    table0.setSpacingAfter(30);
		    cell = new PdfPCell(new Phrase("dotted left border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new DottedBorder(PdfPCell.LEFT));
		    table0.addCell(cell);
		    cell = new PdfPCell(new Phrase("solid right border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new SolidBorder(PdfPCell.RIGHT));
		    table0.addCell(cell);
		    cell = new PdfPCell(new Phrase("solid top border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new SolidBorder(PdfPCell.TOP));
		    table0.addCell(cell);
		    cell = new PdfPCell(new Phrase("dashed bottom border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		    table0.addCell(cell);
		    document.add(table0);

		    PdfPTable table1 = new PdfPTable(4);
		    table1.setSpacingAfter(30);
		    cell = new PdfPCell(new Phrase("dotted left and dashed top border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new DottedBorder(PdfPCell.LEFT));
		    cell.setCellEvent(new DashedBorder(PdfPCell.TOP));
		    table1.addCell(cell);
		    cell = new PdfPCell(new Phrase("solid right and dotted bottom border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new DottedBorder(PdfPCell.BOTTOM));
		    cell.setCellEvent(new SolidBorder(PdfPCell.RIGHT));
		    table1.addCell(cell);
		    cell = new PdfPCell(new Phrase("no border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    table1.addCell(cell);
		    cell = new PdfPCell(new Phrase("full border"));
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setCellEvent(new DottedBorder(PdfPCell.LEFT | PdfPCell.RIGHT));
		    cell.setCellEvent(new SolidBorder(PdfPCell.TOP));
		    cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		    table1.addCell(cell);
		    document.add(table1);
			//----------------------------------------------------------------------------
		    float[] columnWidths = {1, 5, 5};
	        PdfPTable tableX = new PdfPTable(columnWidths);
	        tableX.setWidthPercentage(100);
	        tableX.getDefaultCell().setUseAscender(true);
	        tableX.getDefaultCell().setUseDescender(true);
	        Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
	        PdfPCell cellX = new PdfPCell(new Phrase("This is a header", f));
	        cellX.setBackgroundColor(GrayColor.GRAYBLACK);
	        cellX.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cellX.setColspan(3);
	        tableX.addCell(cellX);
	        tableX.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
	        for (int i = 0; i < 2; i++) {
	        	tableX.addCell("#");
	        	tableX.addCell("Key");
	        	tableX.addCell("Value");
	        }
	        tableX.setHeaderRows(3);
	        tableX.setFooterRows(1);
	        tableX.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
	        tableX.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        for (int counter = 1; counter < 101; counter++) {
	        	tableX.addCell(String.valueOf(counter));
	        	tableX.addCell("key " + counter);
	        	tableX.addCell("value " + counter);
	        }
	        document.add(tableX);
		    //----------------------------------------------------------------------------
//	        Table table2 = new Table(new float[]{2, 1, 1});
//	        table2.setMarginTop(10);
//	        table2.setBorder(new SolidBorder(1));
//	        table2.setWidthPercent(80);
//	        table2.setHorizontalAlignment(HorizontalAlignment.CENTER);
//	        table2.addCell(new Cell(1, 3)
//	            .add("Cell with colspan 3").setBorder(Border.NO_BORDER));
//	        table2.addCell(new Cell(2, 1)
//	            .add("Cell with rowspan 2").setBorder(Border.NO_BORDER));
//	        table2.addCell(new Cell()
//	            .add("row 1; cell 1").setBorder(Border.NO_BORDER));
//	        table2.addCell(new Cell()
//	            .add("row 1; cell 2").setBorder(Border.NO_BORDER));
//	        table2.addCell(new Cell()
//	            .add("row 2; cell 1").setBorder(Border.NO_BORDER));
//	        table2.addCell(new Cell()
//	            .add("row 2; cell 2").setBorder(Border.NO_BORDER));
//	        document.add(table2);
	        //----------------------------------------------------------------------------
        	
        	PdfPTable table = new PdfPTable(2);
        	table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        	//table.DefaultCell.Border = Rectangle.LEFT | Rectangle.RIGHT;
        	// Add PDF Table Header ->
			Stream.of("Travaux", "Prix")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Tache tache : factureDto.getChantier().getTaches()) {
            	
//            	PdfPCell idCell = new PdfPCell(new Phrase(tache.getId().toString()));
//            	idCell.setPaddingLeft(4);
//            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(tache.getDescriptif()));
                firstNameCell.setBorder(0);
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(tache.getPrix())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}