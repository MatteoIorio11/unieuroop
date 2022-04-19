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
    private InvoicesFactory() {
    }

    public static void createInvoice(final Sale sale, final String path) throws FileNotFoundException {
        final var rCol = 111;
        final var gCol = 92;
        final var bCol = 194;
        final float margin = 30;
        final var color = new DeviceRgb(rCol, gCol, bCol);
        final var client = sale.getClient().get();
        final PdfWriter pdfWriter = new PdfWriter(path);
        final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        final Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        final float col = 280;
        final float [] colWidth = {col, col};
        final Table table = new Table(colWidth);
        table.setBackgroundColor(color).setFontColor(DeviceRgb.WHITE);
        table.addCell(new Cell().add(new Paragraph("INVOICE")
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setMarginTop(margin)
        .setMarginBottom(margin)
        .setFontSize(margin)
        .setBorder(Border.NO_BORDER)));
        table.addCell(new Cell().add(new Paragraph("unieurOOP")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(margin)
                .setMarginBottom(margin)
                .setFontSize(margin)
                .setBorder(Border.NO_BORDER)));

        final float [] colWidth1 = {80, 300, 100, 80};
        final Table customerInfo = new Table(colWidth1);
        customerInfo.addCell(new Cell(0, 4)
                .add(new Paragraph("Customer Informations"))
                .setBold()
                .setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Client Code")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(String.valueOf(client.getClientCode()))).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Name")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(client.getName())).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Surname")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(client.getSurname())).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph("Date")).setBorder(Border.NO_BORDER));
        customerInfo.addCell(new Cell().add(new Paragraph(sale.getDate().toString())).setBorder(Border.NO_BORDER));

        final float [] colWidth2 = {140, 140, 140, 140};
        final Table productsInfo = new Table(colWidth2);
        productsInfo.addCell(new Cell().add(new Paragraph("Product"))
                .setBackgroundColor(color).setFontColor(DeviceRgb.WHITE));
        productsInfo.addCell(new Cell().add(new Paragraph("Quantity"))
                .setBackgroundColor(color).setFontColor(DeviceRgb.WHITE));
        productsInfo.addCell(new Cell().add(new Paragraph("Price per item"))
                .setBackgroundColor(color).setFontColor(DeviceRgb.WHITE));
        productsInfo.addCell(new Cell().add(new Paragraph("Total price"))
                .setBackgroundColor(color).setFontColor(DeviceRgb.WHITE));

        sale.getProducts().stream().forEach(p -> {
            productsInfo.addCell(new Cell().add(new Paragraph(p.getName())));
            productsInfo.addCell(new Cell().add(new Paragraph(String.valueOf(sale.getQuantityOf(p)))));
            productsInfo.addCell(new Cell().add(new Paragraph(p.getSellingPrice() + "€")));
            productsInfo.addCell(new Cell().add(new Paragraph(sale.getQuantityOf(p) * p.getSellingPrice() + "€")));
        });

        productsInfo.addCell(new Cell().add(new Paragraph(""))
                .setBackgroundColor(color)
                .setFontColor(DeviceRgb.WHITE)
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph(""))
                .setBackgroundColor(color)
                .setFontColor(DeviceRgb.WHITE)
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph("Total Amount"))
                .setBackgroundColor(color)
                .setFontColor(DeviceRgb.WHITE)
                .setBorder(Border.NO_BORDER));
        productsInfo.addCell(new Cell().add(new Paragraph(sale.getTotalSpent() + "€"))
                .setBackgroundColor(color)
                .setFontColor(DeviceRgb.WHITE)
                .setBorder(Border.NO_BORDER));

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfo);
        document.add(new Paragraph("\n"));
        document.add(productsInfo);
        document.close();
    }
}
