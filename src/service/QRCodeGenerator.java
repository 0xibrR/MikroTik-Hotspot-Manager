package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;

public class QRCodeGenerator {

    public static BufferedImage generate(
            String username,
            String password) throws Exception {

        String content =
                "http://10.10.0.1/login?u="
                        + username
                        + "&p="
                        + password;

        BitMatrix matrix =
                new MultiFormatWriter().encode(
                        content,
                        BarcodeFormat.QR_CODE,
                        200,
                        200);

        BufferedImage image =
                new BufferedImage(
                        200,
                        200,
                        BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {

                image.setRGB(
                        x,
                        y,
                        matrix.get(x, y)
                                ? 0x000000
                                : 0xFFFFFF);
            }
        }

        return image;
    }
}