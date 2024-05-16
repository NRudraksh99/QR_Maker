package org.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class App {
    private static void makeQR(String name,String link) throws WriterException,IOException{
        int width=500,height=500;
        Map<EncodeHintType, Object> hints=new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        QRCodeWriter qr_maker=new QRCodeWriter();
        BitMatrix bm=qr_maker.encode(link,BarcodeFormat.QR_CODE,width,height,hints);
        Path p = FileSystems.getDefault().getPath("QRCodes\\"+name);
        MatrixToImageWriter.writeToPath(bm,"PNG",p);
    }

    public static void main( String[] args ) {
        Scanner s=new Scanner(System.in);
        System.out.println("Hello, welcome to this program that generates QR codes for any entered link or text!");
        System.out.println("The entered link should contain 'https://'. Example of complete link: https://www.youtube.com");
        System.out.print("\nEnter the link for which the QR Code is to be generated: ");
        String qr_link=s.nextLine();
        System.out.print("Enter a complete name for the final image-file containing the QR-Code (with .png or .jpg extension): ");
        String qr_name=s.nextLine();

        try{
            makeQR(qr_name,qr_link);
            System.out.println("The required QR-Code was successfully generated");
        }
        catch (IOException | WriterException e){
            System.out.println("Unfortunately, some error occurred.....");
        }
    }
}
