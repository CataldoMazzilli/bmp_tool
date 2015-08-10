package bmp_tool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.pow;


/**
 * Created by cataldo.
 */
public class MyUI extends JFrame {


    private BmpImage primaBmp;

    private FieldPanel fieldPanel;// pannello dei campi
    private JPanel formatPanel;//pannello della scelta del formato
    private JPanel buttonPanel;//pannello dei bottoni
    private JScrollPane textPanel;//pannelo dei pixel data
    private JScrollPane palettePanel;//pannello della palette

    private JPanel panel1;//pannello che racchiude fieldPanel e textPanel
    private JPanel panel2;//pannello che racchiude formatPanel e buttonPanel

    private JButton jbtSELECT;

    private JRadioButton jrbDEC;
    private JRadioButton jrbBIN;
    private JRadioButton jrbHEX;
    private final ButtonGroup groupRadio;

    private JTextArea jtaNote;
    private int formato;

    public MyUI() {
        this.setLayout(new BorderLayout(5, 5));
        this.setTitle("bmptools");

        panel1 = new JPanel(new GridLayout(1, 2));
        panel2 = new JPanel(new GridLayout(1, 2));

        fieldPanel = new FieldPanel();
        panel1.add(fieldPanel);

        jrbBIN = new JRadioButton("BIN", false);
        jrbDEC = new JRadioButton("DEC", false);
        jrbHEX = new JRadioButton("HEX", true);
        formato = BmpImage.HEX;
        formatPanel = new JPanel(new GridLayout(1, 3));
        formatPanel.add(jrbBIN);
        formatPanel.add(jrbDEC);
        formatPanel.add(jrbHEX);
        groupRadio = new ButtonGroup();
        groupRadio.add(jrbBIN);
        groupRadio.add(jrbDEC);
        groupRadio.add(jrbHEX);
        formatPanel.setBorder(new TitledBorder("Format"));
        panel2.add(formatPanel);

        jbtSELECT = new JButton("Load Image");
        jbtSELECT.setToolTipText("Seleziona l'immagine BMP da leggere");
        buttonPanel = new JPanel();
        buttonPanel.add(jbtSELECT);
        buttonPanel.setBorder(new TitledBorder("Button Panel"));
        panel2.add(buttonPanel);

        jtaNote = new JTextArea();
        jtaNote.setLineWrap(true);
        jtaNote.setWrapStyleWord(true);
        textPanel = new JScrollPane(jtaNote);
        textPanel.setBorder(new TitledBorder("Pixel Data"));
        panel1.add(textPanel);


        palettePanel = new JScrollPane();
        palettePanel.setVisible(false);

        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.NORTH);
        this.add(palettePanel, BorderLayout.CENTER);

        jrbBIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formato = BmpImage.BIN;
                refresh();
            }
        });

        jrbDEC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formato = BmpImage.DEC;
                refresh();
            }
        });

        jrbHEX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formato = BmpImage.HEX;
                refresh();
            }
        });

        jbtSELECT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter bmpFilter = new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        if (file.getName().toLowerCase().endsWith(".bmp")) {
                            return true;
                        }
                        if (file.isDirectory())
                            return true;
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "Bitmap Files (.bmp)";
                    }
                };
                fileChooser.addChoosableFileFilter(bmpFilter);
                fileChooser.setFileFilter(bmpFilter);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    try {
                        primaBmp = new BmpImage(file.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    refresh();
                }
            }
        });

        this.pack();
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void refresh() {
        fieldPanel.jtf_bfType.setText(primaBmp.bytesToWantedFormat(formato, 0, 1) + "(BM)");
        fieldPanel.jtf_bfSize.setText(primaBmp.bytesToWantedFormat(formato, 2, 3, 4, 5) + "(" + primaBmp.getBfSize() + ")");
        fieldPanel.jtf_bfReserved1.setText(primaBmp.bytesToWantedFormat(formato, 6, 7));
        fieldPanel.jtf_bfReserved2.setText(primaBmp.bytesToWantedFormat(formato, 8, 9));
        fieldPanel.jtf_bfOffBits.setText(primaBmp.bytesToWantedFormat(formato, 10, 11, 12, 13) + "(" + primaBmp.getBfOffBits() + ")");
        fieldPanel.jtf_biSize.setText(primaBmp.bytesToWantedFormat(formato, 14, 15, 16, 17) + "(" + primaBmp.getBiSize() + ")");
        fieldPanel.jtf_biWidth.setText(primaBmp.bytesToWantedFormat(formato, 18, 19, 20, 21) + "(" + primaBmp.getBiWidth() + ")");
        fieldPanel.jtf_biHeight.setText(primaBmp.bytesToWantedFormat(formato, 22, 23, 24, 25) + "(" + primaBmp.getBiHeight() + ")");
        fieldPanel.jtf_biPlanes.setText(primaBmp.bytesToWantedFormat(formato, 26, 27));
        fieldPanel.jtf_biBitCount.setText(primaBmp.bytesToWantedFormat(formato, 28, 29) + "(" + primaBmp.getBiBitCount() + ")");
        fieldPanel.jtf_biCompression.setText(primaBmp.bytesToWantedFormat(formato, 30, 31, 32, 33));
        fieldPanel.jtf_biSizeImage.setText(primaBmp.bytesToWantedFormat(formato, 34, 35, 36, 37) + "(" + primaBmp.getBiSizeImage() + ")");
        fieldPanel.jtf_biXPelsPerMeter.setText(primaBmp.bytesToWantedFormat(formato, 38, 39, 40, 41));
        fieldPanel.jtf_biYPelsPerMeter.setText(primaBmp.bytesToWantedFormat(formato, 42, 43, 44, 45));
        fieldPanel.jtf_biClrUsed.setText(primaBmp.bytesToWantedFormat(formato, 46, 47, 48, 49) + "(" + primaBmp.getBiClrUsed() + ")");
        fieldPanel.jtf_biClrImportant.setText(primaBmp.bytesToWantedFormat(formato, 50, 51, 52, 53));

        displayPixelData(formato);
        creaPalette(formato);
    }

    private void creaPalette(int formato) {
        if (primaBmp.getBiBitCount() == 4 || primaBmp.getBiBitCount() == 8 || primaBmp.getBiBitCount() == 1) {
            JPanel panel1 = new JPanel(new GridLayout((int) pow(2, primaBmp.getBiBitCount()), 1));
            int offset = 54;
            for (int i = 0; i < (int) pow(2, primaBmp.getBiBitCount()); i++) {
                JTextField jtf = new JTextField("["+i+"] "+primaBmp.bytesToWantedFormat(formato, offset, offset + 1, offset + 2, offset + 3));
                Color colore = new Color(primaBmp.leggiByte(offset+2), primaBmp.leggiByte(offset + 1), primaBmp.leggiByte(offset));
                jtf.setBackground(colore);
                panel1.add(jtf);
                offset = offset + 4;
            }
            palettePanel.setBorder(new TitledBorder("Color Table"));
            palettePanel.setViewportView(panel1);
            palettePanel.setVisible(true);
            SwingUtilities.updateComponentTreeUI(this);
        } else if (primaBmp.getBiBitCount() == 24) {
            palettePanel.setVisible(false);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    private void displayPixelData(int formato) {
        if (primaBmp.getBiBitCount() == 1) {
            jtaNote.setText("");
            int split = ((primaBmp.getBiWidth()-(primaBmp.getBiWidth()%32))+32)/8;
            for (int i = primaBmp.getBfOffBits(); i < primaBmp.getBfSize(); i++) {
                if (i > primaBmp.getBfOffBits() && (((i-primaBmp.getBfOffBits())%split)==0)) jtaNote.append("\n") ;
                jtaNote.append(primaBmp.bytesToWantedFormat(formato, i) + " ");
            }
        } else if (primaBmp.getBiBitCount() == 4) {
            jtaNote.setText("");
            int split = (((primaBmp.getBiWidth()*4)-((primaBmp.getBiWidth()*4)%32))+32)/8;
            for (int i = primaBmp.getBfOffBits(); i < primaBmp.getBfSize(); i++) {
                if (i > primaBmp.getBfOffBits() && (((i-primaBmp.getBfOffBits())%split)==0)) jtaNote.append("\n") ;
                jtaNote.append(primaBmp.bytesToWantedFormat(formato, i) + " ");
            }
        } else if (primaBmp.getBiBitCount() == 8) {
            jtaNote.setText("");
            int split = (((primaBmp.getBiWidth()*8)-((primaBmp.getBiWidth()*8)%32))+32)/8;
            for (int i = primaBmp.getBfOffBits(); i < primaBmp.getBfSize(); i++) {
                if (i > primaBmp.getBfOffBits() && (((i-primaBmp.getBfOffBits())%split)==0)) jtaNote.append("\n") ;
                jtaNote.append(primaBmp.bytesToWantedFormat(formato, i) + " ");
            }
        } else if (primaBmp.getBiBitCount() == 24) {
            jtaNote.setText("");
            int split = (((primaBmp.getBiWidth()*24)-((primaBmp.getBiWidth()*24)%32))+32)/8;
            for (int i = primaBmp.getBfOffBits(); i < primaBmp.getBfSize(); i++) {
                if (i > primaBmp.getBfOffBits() && (((i-primaBmp.getBfOffBits())%split)==0)) jtaNote.append("\n") ;
                jtaNote.append(primaBmp.bytesToWantedFormat(formato, i) + " ");
            }
        } else {
            jtaNote.setText("");
        }
    }

}
