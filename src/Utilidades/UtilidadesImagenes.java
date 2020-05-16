/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author DOLFHANDLER
 */
public class UtilidadesImagenes {

    public static final ArrayList<String> imagenesTemporales = new ArrayList<String>();
    
    public static byte[] getBytes(String urlImagen, int tipoCodificacion) {
        try {
            if (tipoCodificacion == TipoCodificacion.IMAGEN_URL_ABSOLUTA) {
                return Files.readAllBytes(new File(urlImagen).toPath());
            }
            else if (tipoCodificacion == TipoCodificacion.IMAGEN_BASE64) {
                BASE64Decoder decoder = new BASE64Decoder();
                return decoder.decodeBuffer(urlImagen);
            }else{
                return null;
            }
        } catch (IOException ex) {
            System.out.println("UtilidadesImagenes > getBytes(" + urlImagen + "):" + ex.getMessage());
            return null;
        }
    }

    public static Image getImagen(byte[] bytes) {
        try {
            Image imagen;
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(bytes));
            imagen = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
            return imagen;
        } catch (IOException ex) {
            System.out.println("UtilidadesImagenes > getImagen(byte[]):" + ex.getMessage());
            return null;
        }
    }

    public static boolean descargarImagen(String urlImagen, byte[] bytes) {
        try {
            Path path = Paths.get(urlImagen);
            Files.write(path, bytes, StandardOpenOption.CREATE);
            return Files.exists(path);
        } catch (IOException ex) {
            System.out.println("UtilidadesImagenes > descargarImagen(" + urlImagen + ", byte[]):" + ex.getMessage());
            return false;
        }
    }

    public static String getBase64(byte[] imagen) {
        String imageString = "";
        BASE64Encoder encoder = new BASE64Encoder();
        imageString = encoder.encode(imagen);
        return imageString;
    }
}
