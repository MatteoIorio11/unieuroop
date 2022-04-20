package unieuroop.controller.invoice;


import java.io.FileNotFoundException;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import unieuroop.model.sale.Sale;

public final class InvoicesFactory {

    private static final int SIZE_MARGIN = 30;
    private static final int SIZE_FONT = 30;
    private static final int BACKGROUND_RED_TONE = 111;
    private static final int BACKGROUND_GREEN_TONE = 92;
    private static final int BACKGROUND_BLUE_TONE = 194;
    private static final int FONT_RED_TONE = 255;
    private static final int FONT_GREEN_TONE = 255;
    private static final int FONT_BLUE_TONE = 255;

    private InvoicesFactory() {

    }

    public static void createInvoice(final Sale sale, final String path) throws FileNotFoundException {
        final var client = sale.getClient().get();
        final PdfWriter pdfWriter = new PdfWriter(path);
        final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        final Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        final float[] colWidth = {280, 280};
        final Table table = new Table(colWidth);
        table.setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
        .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE));
        table.addCell(new Cell().add(new Paragraph("INVOICE")
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setMarginTop(InvoicesFactory.SIZE_MARGIN)
        .setMarginBottom(InvoicesFactory.SIZE_MARGIN)
        .setFontSize(InvoicesFactory.SIZE_FONT)
        .setBorder(Border.NO_BORDER)));
        table.addCell(new Cell().add(new Paragraph("unieurOOP")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(InvoicesFactory.SIZE_MARGIN)
                .setMarginBottom(InvoicesFactory.SIZE_MARGIN)
                .setFontSize(InvoicesFactory.SIZE_FONT)
                .setBorder(Border.NO_BORDER)));

        final float[] colWidth1 = {80, 300, 100, 80};
        final Table customerInfo = new Table(colWidth1);
        customerInfo.addCell(new Cell(0, 4)
                .add(new Paragraph("Customer Informations"))
                .setBold()
                .setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Client Code")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(String.valueOf(client.getPerson().getCode()))).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Name")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(client.getPerson().getName())).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Surname")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(client.getPerson().getSurname())).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Date")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(sale.getDate().toString())).setBorder(Border.NO_BORDER));

        final float[] colWidth2 = {140, 140, 140, 140};
        final Table productsInfo = new Table(colWidth2);
        productsInfo.addCell(new Cell().add(new Paragraph("Product"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE)));
        productsInfo.addCell(new Cell().add(new Paragraph("Quantity"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE)));
        productsInfo.addCell(new Cell().add(new Paragraph("Price per item"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE)));
        productsInfo.addCell(new Cell().add(new Paragraph("Total price"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE)));

        sale.getProducts().stream().forEach(p -> {
            productsInfo.addCell(new Cell().add(new Paragraph(p.getName())));
            productsInfo.addCell(new Cell().add(new Paragraph(String.valueOf(sale.getQuantityOf(p)))));
            productsInfo.addCell(new Cell().add(new Paragraph(p.getSellingPrice() + "€")));
            productsInfo.addCell(new Cell().add(new Paragraph(sale.getQuantityOf(p) * p.getSellingPrice() + "€")));
        });

        productsInfo.addCell(new Cell().add(new Paragraph(""))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE))
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph(""))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE))
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph("Total Amount"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE))
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph(sale.getTotalSpent() + "€"))
                .setBackgroundColor(new DeviceRgb(InvoicesFactory.BACKGROUND_RED_TONE, InvoicesFactory.BACKGROUND_GREEN_TONE, InvoicesFactory.BACKGROUND_BLUE_TONE))
                .setFontColor(new DeviceRgb(InvoicesFactory.FONT_RED_TONE, InvoicesFactory.FONT_GREEN_TONE, InvoicesFactory.FONT_BLUE_TONE))
                .setBorder(Border.NO_BORDER));

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfo);
        document.add(new Paragraph("\n"));
        document.add(productsInfo);
        document.close();
    }
}
