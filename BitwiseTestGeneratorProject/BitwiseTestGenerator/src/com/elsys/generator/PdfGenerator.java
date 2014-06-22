package com.elsys.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

	public class PdfGenerator {
		private static String FILE_NAME = "C:\\Users\\Alexander\\Desktop\\tasks.pdf";

		private static Font red;

		private static Font black;

		public static void main(String[] args) {

			try {
				// Initialize the base font.
				BaseFont baseFont = BaseFont.createFont("libs/resources/times.ttf",
						BaseFont.IDENTITY_H, true);

				// Initialize the colors.
				red = new Font(baseFont, 50f, 0, BaseColor.RED);
				black = new Font(baseFont, 50f, 0, BaseColor.BLACK);

				// Create the PDF document.
				Document document = createPDF();

				// Create a table, holding the cards.
				PdfPTable table = createTable();
				document.add(table);

				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private static Document createPDF() {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME));
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			}
			document.open();
			return document;
		}

		private static PdfPTable createTable() throws BadElementException {
			PdfPTable table = new PdfPTable(4);

			String[] cardPower = { "2", "3", "4", "5", "6", "7", "8", "9", "10",
					"J", "Q", "K", "A" };

			String[] cardPaint = { "♠", "♥", "♣", "♦" };

			boolean isRed = false;
			Font font = null;
			for (String currentCard : cardPower) {
				for (String currentPaint : cardPaint) {
					if (isRed) {
						font = red;
					} else {
						font = black;
					}
					isRed = !isRed;
					table.addCell(new Paragraph(currentCard + currentPaint + " ",
							font));
				}
			}
			return table;
		}
	}

