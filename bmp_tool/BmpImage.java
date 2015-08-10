package bmp_tool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Math.pow;

/**
 * Created by cataldo.
 */

public class BmpImage {

    public static final int BIN = 1;
    public static final int DEC = 2;
    public static final int HEX = 3;

    private int[] arrayByteFile;//array contenente i byte dell'immagine ogni byte è rappresentato da un intero da 0 a 255

    private int bfSize;//dimensione del file in byte
    private int bfOffBits;//Scostamento (offset) in bytes dove inizia l'immagine bitmap vera e propria
    private int biSize;//Grandezza dell’intestazione - deve essere almeno 40
    private int biWidth;//Larghezza dell’immagine in pixel
    private int biHeight;//Altezza dell’immagine in pixel
    private int biBitCount;//Bit per pixel
    private int biSizeImage;//Grandezza dell’immagine
    private int biClrUsed;//Il numero di colori definiti nella palette

    public BmpImage(String nomeFile) throws IOException {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(nomeFile));
        try {
            System.out.println("disponibili alla lettura: " + input.available() + " byte");
            input.mark(Integer.MAX_VALUE);
            int value;
            int bfSize = 0;
            for (int i = 0; i < 2; i++) {
                value = input.read();
            }
            for (int i = 0; i < 4; i++) {
                value = input.read();
                bfSize = bfSize + (value * ((int) pow((16 * 16), i)));
            }
            System.out.println("dimensione dichiarata della intestazione: " + bfSize + " byte\n");
            // avvaloro bfSize variabile privata
            this.bfSize = bfSize;
            this.arrayByteFile = new int[this.bfSize];

            if (input.markSupported()) {
                input.reset();

                //riempimento dell' array
                int index = 0;
                while ((value = input.read()) != -1) {
                    arrayByteFile[index] = value;
                    index++;
                }
                bfOffBits = byteToIntValue(arrayByteFile[10],arrayByteFile[11],arrayByteFile[12],arrayByteFile[13]);
                biSize = byteToIntValue(arrayByteFile[14],arrayByteFile[15],arrayByteFile[16],arrayByteFile[17]);
                biWidth = byteToIntValue(arrayByteFile[18],arrayByteFile[19],arrayByteFile[20],arrayByteFile[21]);
                biHeight = byteToIntValue(arrayByteFile[22],arrayByteFile[23],arrayByteFile[24],arrayByteFile[25]);
                biBitCount = byteToIntValue(arrayByteFile[28],arrayByteFile[29]);
                biSizeImage = byteToIntValue(arrayByteFile[34],arrayByteFile[35],arrayByteFile[36],arrayByteFile[37]);
                biClrUsed = byteToIntValue(arrayByteFile[46],arrayByteFile[47],arrayByteFile[48],arrayByteFile[49]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
    }

    public int leggiByte(int byteIndex) {
        return arrayByteFile[byteIndex];
    }

    /**
     * vista dei primi 54 bytes in esadecimale.
     */
    public void hexByteView() {
        int offset = 0;
        for (int element : this.arrayByteFile) {
            if (offset == 0) System.out.print("tipo:");
            if (offset == 2) System.out.print("\ndimensione del file:");
            if (offset == 6) System.out.print("\nriservato:");
            if (offset == 10) System.out.print("\nScostamento (offset) in bytes dove inizia l'immagine bitmap vera e propria:");
            if (offset == 14) System.out.print("\n\nlunghezza in byte di questo header:");
            if (offset == 18) System.out.print("\nLarghezza in pixel del bitmap:");
            if (offset == 22) System.out.print("\nAltezza in pixel del bitmap:");
            if (offset == 26) System.out.print("\nnumero di piani - vale sempre 1:");
            if (offset == 28) System.out.print("\nNumero di bit (colore) per pixel:");
            if (offset == 30) System.out.print("\ncompressione:");
            if (offset == 34) System.out.print("\nDimensione in byte dell'immagine bitmap senza le 3 sezioni iniziali:");
            if (offset == 38) System.out.print("\nRisoluzione orizzontale in px al mt:");
            if (offset == 42) System.out.print("\nRisoluzione verticale in px al mt:");
            if (offset == 46) System.out.print("\nNumero di colori da considerare nella palette (0 indica tutti):");
            if (offset == 50) System.out.print("\nzero indica che tutti i colori sono importanti:");
            if (offset == 54) System.out.print("\n");
            if (offset == bfOffBits) System.out.print("\ninizio dei colori:\n");
            if (offset > bfOffBits && ((offset-bfOffBits)%(biWidth%4==0?biWidth:biWidth-biWidth%4+4))==0) System.out.print("\n") ;

            String s = Integer.toHexString(element);
            System.out.print(s + " ");
            offset++;
        }

        System.out.print("\n\n");


        System.out.print( bfSize + " dimensione del file in byte\n");
        System.out.print(  bfOffBits+ " Scostamento (offset) in bytes dove inizia l'immagine bitmap vera e propria\n");
        System.out.print(biSize+" Grandezza dell’intestazione - deve essere almeno 40\n");
        System.out.print(biWidth+" Larghezza dell’immagine in pixel\n");
        System.out.print(biHeight+" Altezza dell’immagine in pixel\n");
        System.out.print(biBitCount+" Bit per pixel\n");
        System.out.print(biSizeImage+" Grandezza dell’immagine\n");
        System.out.print(biClrUsed+" Il numero di colori definiti nella palette\n");

        System.out.print("\n");
        System.out.print("\n");
    }


    /**
     * vista dei primi 54 bytes in binario.
     */
    public void binaryByteView() {
        int offset = 0;
        for (int element : this.arrayByteFile) {
            if (offset == 0) System.out.print("tipo:");
            if (offset == 2) System.out.print("\ndimensione del file:");
            if (offset == 6) System.out.print("\nriservato:");
            if (offset == 10) System.out.print("\nScostamento (offset) in bytes dove inizia l'immagine bitmap vera e propria:");
            if (offset == 14) System.out.print("\n\nlunghezza in byte di questo header:");
            if (offset == 18) System.out.print("\nLarghezza in pixel del bitmap:");
            if (offset == 22) System.out.print("\nAltezza in pixel del bitmap:");
            if (offset == 26) System.out.print("\nnumero di piani - vale sempre 1:");
            if (offset == 28) System.out.print("\nNumero di bit (colore) per pixel:");
            if (offset == 30) System.out.print("\ncompressione:");
            if (offset == 34)
                System.out.print("\nDimensione in byte dell'immagine bitmap senza le 3 sezioni iniziali:");
            if (offset == 38) System.out.print("\nRisoluzione orizzontale in px al mt:");
            if (offset == 42) System.out.print("\nRisoluzione verticale in px al mt:");
            if (offset == 46) System.out.print("\nNumero di colori da considerare nella palette (0 indica tutti):");
            if (offset == 50) System.out.print("\nzero indica che tutti i colori sono importanti:");
            if (offset == 54) System.out.print("\n");
            String s = String.format("%8s", Integer.toBinaryString(element & 0xFF)).replace(' ', '0');
            System.out.print(s + " ");
            offset++;
        }
    }

    /**
     * vista dei primi 54 bytes in decimale.
     */
    public void decByteView() {
        int offset = 0;
        for (int element : this.arrayByteFile) {
            if (offset == 0) System.out.print("tipo:");
            if (offset == 2) System.out.print("\ndimensione del file:");
            if (offset == 6) System.out.print("\nriservato:");
            if (offset == 10) System.out.print("\nScostamento (offset) in bytes dove inizia l'immagine bitmap vera e propria:");
            if (offset == 14) System.out.print("\n\nlunghezza in byte di questo header:");
            if (offset == 18) System.out.print("\nLarghezza in pixel del bitmap:");
            if (offset == 22) System.out.print("\nAltezza in pixel del bitmap:");
            if (offset == 26) System.out.print("\nnumero di piani - vale sempre 1:");
            if (offset == 28) System.out.print("\nNumero di bit (colore) per pixel:");
            if (offset == 30) System.out.print("\ncompressione:");
            if (offset == 34) System.out.print("\nDimensione in byte dell'immagine bitmap senza le 3 sezioni iniziali:");
            if (offset == 38) System.out.print("\nRisoluzione orizzontale in px al mt:");
            if (offset == 42) System.out.print("\nRisoluzione verticale in px al mt:");
            if (offset == 46) System.out.print("\nNumero di colori da considerare nella palette (0 indica tutti):");
            if (offset == 50) System.out.print("\nzero indica che tutti i colori sono importanti:");
            if (offset == 54) System.out.print("\n");
            System.out.print(element + " ");
            offset++;
        }
    }

    /*
    * input: sequenza di bytes definti come interi da 0 a 255
    * output: stringa che contiene la conversione desiderata dei bytes passati come parametri
    * */
    public String bytesToWantedFormat (int formato, int... bytes) {
        String output = "";
        if(formato==BIN){
            for (int _byte : bytes) {
                output = output + String.format("%8s", Integer.toBinaryString(arrayByteFile[_byte] & 0xFF)).replace(' ', '0') + " ";
            }
        }
        if(formato==DEC){
            for (int _byte : bytes) {
                output = output + arrayByteFile[_byte] + " ";
            }
        }
        if (formato==HEX)
        for (int _byte : bytes) {
            output = output + Integer.toHexString(arrayByteFile[_byte]) + " ";
        }
        return output;
    }
    /*
    * input: sequenza di bytes definti come interi da 0 a 255 aventi base 256
    * output: valore intero
    * */
    public int byteToIntValue(int... bytes) {
        int output = 0;
        int index = 0;
        for (int _byte : bytes) {
            output = output + (_byte * ((int) pow((16 * 16), index)));
            index++;
        }
        return output;
    }

    public int getBfSize() {
        return bfSize;
    }

    public int getBfOffBits() {
        return bfOffBits;
    }

    public int getBiSize() {
        return biSize;
    }

    public int getBiWidth() {
        return biWidth;
    }

    public int getBiHeight() {
        return biHeight;
    }

    public int getBiBitCount() {
        return biBitCount;
    }

    public int getBiSizeImage() {
        return biSizeImage;
    }

    public int getBiClrUsed() {
        return biClrUsed;
    }
}
