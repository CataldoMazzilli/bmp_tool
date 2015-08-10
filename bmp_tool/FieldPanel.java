package bmp_tool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by cataldo.
 */
public class FieldPanel extends JPanel {

    private JLabel bfType;
    private JLabel bfSize;
    private JLabel bfReserved1;
    private JLabel bfReserved2;
    private JLabel bfOffBits;
    private JLabel biSize;
    private JLabel biWidth;
    private JLabel biHeight;
    private JLabel biPlanes;
    private JLabel biBitCount;
    private JLabel biCompression;
    private JLabel biSizeImage;
    private JLabel biXPelsPerMeter;
    private JLabel biYPelsPerMeter;
    private JLabel biClrUsed;
    private JLabel biClrImportant;

    JTextField jtf_bfType;
    JTextField jtf_bfSize;
    JTextField jtf_bfReserved1;
    JTextField jtf_bfReserved2;
    JTextField jtf_bfOffBits;
    JTextField jtf_biSize;
    JTextField jtf_biWidth;
    JTextField jtf_biHeight;
    JTextField jtf_biPlanes;
    JTextField jtf_biBitCount;
    JTextField jtf_biCompression;
    JTextField jtf_biSizeImage;
    JTextField jtf_biXPelsPerMeter;
    JTextField jtf_biYPelsPerMeter;
    JTextField jtf_biClrUsed;
    JTextField jtf_biClrImportant;




    public FieldPanel(){

        this.setLayout(new GridLayout(16,1));
        this.setBorder(new TitledBorder("FieldPanel"));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();
        JPanel panel14 = new JPanel();
        JPanel panel15 = new JPanel();
        JPanel panel16 = new JPanel();

        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel4.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel5.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel6.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel7.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel8.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel9.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel10.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel11.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel12.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel13.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel14.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel15.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        panel16.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));

        bfType = new JLabel("bfType");
        bfType.setToolTipText("I caratteri BM");
        bfSize = new JLabel("bfSize");
        bfSize.setToolTipText("La grandezza del file in bytes");
        bfReserved1 = new JLabel("bfReserved1");
        bfReserved1.setToolTipText("non usato - deve essere zero");
        bfReserved2 = new JLabel("bfReserved2");
        bfReserved2.setToolTipText("non usato - deve essere zero");
        bfOffBits  = new JLabel("bfOffBits");
        bfOffBits.setToolTipText("Offset dell’inizio della sezione Pixel Data");
        biSize = new JLabel("biSize");
        biSize.setToolTipText("Grandezza dell’intestazione - deve essere almeno 40");
        biWidth = new JLabel("biWidth");
        biWidth.setToolTipText("Larghezza dell’immagine in pixel");
        biHeight = new JLabel("biHeight");
        biHeight.setToolTipText("Altezza dell’immagine in pixel");
        biPlanes = new JLabel("biPlanes");
        biPlanes.setToolTipText("deve essere 1");
        biBitCount = new JLabel("biBitCount");
        biBitCount.setToolTipText("Bit per pixel");
        biCompression = new JLabel("biCompression");
        biCompression.setToolTipText("Tipo di compressione(0 = non compresso)");
        biSizeImage = new JLabel("biSizeImage");
        biSizeImage.setToolTipText("Grandezza dell’immagine");
        biXPelsPerMeter = new JLabel("biXPelsPerMeter");
        biXPelsPerMeter.setToolTipText("Risoluzione preferita in pixel per metro");
        biYPelsPerMeter = new JLabel("biYPelsPerMeter");
        biYPelsPerMeter.setToolTipText("Risoluzione preferita in pixel per metro");
        biClrUsed = new JLabel("biClrUsed");
        biClrUsed.setToolTipText("Il numero di colori definiti nella palette");
        biClrImportant = new JLabel("biClrImportant");
        biClrImportant.setToolTipText("Numero dei colori significativi");

        jtf_bfType          = new JTextField(30);
        jtf_bfSize          = new JTextField(30);
        jtf_bfReserved1     = new JTextField(30);
        jtf_bfReserved2     = new JTextField(30);
        jtf_bfOffBits       = new JTextField(30);
        jtf_biSize          = new JTextField(30);
        jtf_biWidth         = new JTextField(30);
        jtf_biHeight        = new JTextField(30);
        jtf_biPlanes        = new JTextField(30);
        jtf_biBitCount      = new JTextField(30);
        jtf_biCompression   = new JTextField(30);
        jtf_biSizeImage     = new JTextField(30);
        jtf_biXPelsPerMeter = new JTextField(30);
        jtf_biYPelsPerMeter = new JTextField(30);
        jtf_biClrUsed       = new JTextField(30);
        jtf_biClrImportant  = new JTextField(30);

        panel1.add(bfType             );
        panel1.add(jtf_bfType         );
        panel2.add(bfSize             );
        panel2.add(jtf_bfSize         );
        panel3.add(bfReserved1        );
        panel3.add(jtf_bfReserved1    );
        panel4.add(bfReserved2        );
        panel4.add(jtf_bfReserved2    );
        panel5.add(bfOffBits          );
        panel5.add(jtf_bfOffBits      );
        panel6.add(biSize             );
        panel6.add(jtf_biSize         );
        panel7.add(biWidth            );
        panel7.add(jtf_biWidth        );
        panel8.add(biHeight           );
        panel8.add(jtf_biHeight       );
        panel9.add(biPlanes           );
        panel9.add(jtf_biPlanes       );
        panel10.add(biBitCount         );
        panel10.add(jtf_biBitCount     );
        panel11.add(biCompression      );
        panel11.add(jtf_biCompression  );
        panel12.add(biSizeImage        );
        panel12.add(jtf_biSizeImage    );
        panel13.add(biXPelsPerMeter    );
        panel13.add(jtf_biXPelsPerMeter);
        panel14.add(biYPelsPerMeter    );
        panel14.add(jtf_biYPelsPerMeter);
        panel15.add(biClrUsed          );
        panel15.add(jtf_biClrUsed      );
        panel16.add(biClrImportant     );
        panel16.add(jtf_biClrImportant );

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
        this.add(panel7);
        this.add(panel8);
        this.add(panel9);
        this.add(panel10);
        this.add(panel11);
        this.add(panel12);
        this.add(panel13);
        this.add(panel14);
        this.add(panel15);
        this.add(panel16);
    }
}
