package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

public class PdfVoucherGenerator {

    public static void export(
            List<User> users,
            String pdfPath)
            throws Exception {

        if (users == null ||
                users.isEmpty()) {
            return;
        }

        Document document =
                new Document(
                        PageSize.A4,
                        5,
                        5,
                        5,
                        5);

        PdfWriter.getInstance(
                document,
                new FileOutputStream(
                        pdfPath));

        document.open();

        int columns =
                Math.clamp(
                        users.size(),
                        1,
                        10);

        PdfPTable table =
                new PdfPTable(
                        columns);

        table.setWidthPercentage(
                100);

        for (User user : users) {

            PdfPCell cell =
                    createVoucherCell(
                            user);

            table.addCell(
                    cell);
        }

        document.add(
                table);

        document.close();
    }

    private static PdfPCell createVoucherCell(
            User user)
            throws Exception {

        PdfPCell cell =
                new PdfPCell();

        cell.setPadding(2);

        cell.setFixedHeight(
                90);

        cell.setHorizontalAlignment(
                Element.ALIGN_CENTER);

        cell.setVerticalAlignment(
                Element.ALIGN_MIDDLE);

        Font titleFont =
                new Font(
                        Font.FontFamily.HELVETICA,
                        7,
                        Font.BOLD);

        Font smallFont =
                new Font(
                        Font.FontFamily.HELVETICA,
                        6);

        Paragraph title =
                new Paragraph(
                        "WIFI",
                        titleFont);

        title.setAlignment(
                Element.ALIGN_CENTER);

        Paragraph username =
                new Paragraph(
                        "U: " +
                                user.getUsername(),
                        smallFont);

        username.setAlignment(
                Element.ALIGN_CENTER);

        Paragraph password =
                new Paragraph(
                        "P: " +
                                user.getPassword(),
                        smallFont);

        password.setAlignment(
                Element.ALIGN_CENTER);

        Paragraph uptime =
                new Paragraph(
                        user.getUptime(),
                        smallFont);

        uptime.setAlignment(
                Element.ALIGN_CENTER);

        BufferedImage qrImage =
                QRCodeGenerator.generate(
                        user.getUsername(),
                        user.getPassword());

        ByteArrayOutputStream baos =
                new ByteArrayOutputStream();

        ImageIO.write(
                qrImage,
                "png",
                baos);

        Image qr =
                Image.getInstance(
                        baos.toByteArray());

        qr.scaleToFit(
                35,
                35);

        qr.setAlignment(
                Element.ALIGN_CENTER);

        cell.addElement(
                title);

        cell.addElement(
                username);

        cell.addElement(
                password);

        cell.addElement(
                uptime);

        cell.addElement(
                qr);

        return cell;
    }
}