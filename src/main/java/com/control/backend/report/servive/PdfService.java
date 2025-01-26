package com.control.backend.report.servive;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class PdfService {

	public byte[] createPdf(List<?> tableData) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			final PdfWriter writer = new PdfWriter(baos);
			final PdfDocument pdfDoc = new PdfDocument(writer);
			final Document document = new Document(pdfDoc);

			// Custom font
			// PdfFont font = PdfFontFactory.createFont("fonts/arial.ttf", "Identity-H",
			// true);
			// document.setFont(font);

			// Adding header
			addHeader(document, "Advanced PDF Report");

			// Adding dynamic table
			addTable(document);

			// Adding footer
			addFooter(document);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return baos.toByteArray();
	}

	private void addHeader(Document document, String headerText) {
		final Paragraph header = new Paragraph(headerText).setTextAlignment(TextAlignment.CENTER).setFontSize(16);
		document.add(header);
	}

	private void addFooter(Document document) {
		final Paragraph footer = new Paragraph("+250791446610 KN 78 St, Kigali, Rwanda\ninfo@edencaremedical.com")
				.setTextAlignment(TextAlignment.CENTER).setFontSize(10)
				.setFixedPosition(30, 30, UnitValue.createPercentValue(100));
		document.add(footer);
	}

	private void addTable(Document document) {
		final float[] columnWidths = { 1, 5 };
		final Table table = new Table(columnWidths);

		// Adding table headers
		table.addHeaderCell(createStyledCell("ID"));
		table.addHeaderCell(createStyledCell("Description"));

		// Adding table rows
//		for (String[] rowData : tableData) {
//			table.addCell(createStyledCell(rowData[0]));
//			table.addCell(createStyledCell(rowData[1]));
//		}

		document.add(table);
	}

	private Cell createStyledCell(String content) {
		return new Cell().add(new Paragraph(content)).setBackgroundColor(ColorConstants.LIGHT_GRAY)
				.setTextAlignment(TextAlignment.CENTER);
	}
}
