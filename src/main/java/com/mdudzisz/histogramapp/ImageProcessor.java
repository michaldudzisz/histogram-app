package com.mdudzisz.histogramapp;

import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessor {

    public int[][] getHistogram(byte[] bytes) throws IOException {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        return calcHistogram(image);
    }

    private int[][] calcHistogram(BufferedImage image) {
        int[][] histogram = new int[3][256];
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < pixels.length; i = i + 3) {
            histogram[0][(int) pixels[i] & 0xff]++;        // R
            histogram[1][(int) pixels[i + 1] & 0xff]++;    // G
            histogram[2][(int) pixels[i + 2] & 0xff]++;    // B
        }

        return histogram;
    }
}
