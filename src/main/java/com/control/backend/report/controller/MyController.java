package com.control.backend.report.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.report.servive.PdfService;
import com.control.backend.report.util.PdfUtils;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.DocumentException;

@RestController
public class MyController {

	@Autowired
	private PdfService pdfService;

	@GetMapping("/api/pdf")
	public ResponseEntity<byte[]> exportPdf() throws IOException, DocumentException {
		var gastos = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("data", "04/02/2012");
		item.put("descricao", "Diária Hotel");
		item.put("valor", "R$ 260,00");
		item.put("categoria", "R.color.categoria_hospedagem");
		gastos.add(item);

		ByteArrayOutputStream pdfStream = PdfUtils.generatePdfStream(gastos);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
		headers.setContentLength(pdfStream.size());
		return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
	}

	@GetMapping("/api/pdf/vendas")
	public ResponseEntity<byte[]> gerarRelatorioVendas() {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			final var escritorPDF = new PdfWriter(baos);
			final var pdfDoc = new PdfDocument(escritorPDF);
			final var documento = new Document(pdfDoc, PageSize.A4);
			documento.setMargins(20, 20, 20, 20);

			// Título
			final var titulo = new Paragraph("Relatório de Vendas").setFontSize(20).setFontColor(ColorConstants.BLUE);
			documento.add(titulo);

			// Subtítulo
			final var subtitulo = new Paragraph("Janeiro de 2025").setFontSize(14).setFontColor(ColorConstants.GRAY);
			documento.add(subtitulo);
			documento.add(new Paragraph("\n"));

			// Tabela
			final float[] colunas = { 200F, 200F, 200F };
			final var tabela = new Table(colunas);
			tabela.addHeaderCell(new Cell().add(new Paragraph("Produto")));
			tabela.addHeaderCell(new Cell().add(new Paragraph("Quantidade")));
			tabela.addHeaderCell(new Cell().add(new Paragraph("Preço")));
			tabela.addCell(new Cell().add(new Paragraph("Produto A")));
			tabela.addCell(new Cell().add(new Paragraph("10")));
			tabela.addCell(new Cell().add(new Paragraph("$100")));
			tabela.addCell(new Cell().add(new Paragraph("Produto B")));
			tabela.addCell(new Cell().add(new Paragraph("5")));
			tabela.addCell(new Cell().add(new Paragraph("$50")));
			documento.add(tabela);
			documento.close();

			final var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=relatorio_vendas.pdf");

			return ResponseEntity.ok().headers(headers).contentType(org.springframework.http.MediaType.APPLICATION_PDF)
					.body(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/api/pdf/g")
	public ResponseEntity<?> generatePdf() {
		// Sample data
		final var tableData = Arrays.asList(new String[] { "1", "First row data" }, new String[] { "2", "Second row data" },
				new String[] { "3", "Third row data" }, new String[] { "4", "Fourth row data" });
		final byte[] pdfBytes = pdfService.createPdf(tableData);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "advanced_report.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}

}