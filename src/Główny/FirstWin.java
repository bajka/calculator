
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FirstWin extends JPanel implements ActionListener {

    private static final Action Action = null;
    boolean przecinek = false, kropka = false;
    int special;
    JButton ToolPrzecinek = new JButton("Przecinek");
    JButton b0 = new JButton("0");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton bPlus = new JButton("+");
    JButton bMinus = new JButton("-");
    JButton bMnoznik = new JButton("*");
    JButton bDzielnik = new JButton("/");
    JButton bSum = new JButton("=");
    JButton bC = new JButton("C");
    JButton bB = new JButton("�?");
    JButton bK = new JButton("x²");
    JButton bP = new JButton(".");
    JTextField tekst = new JTextField("0", JTextField.RIGHT);
    String wartosc = "0";
    String pomoc = "";

    public static void main(String[] args) {
        new FirstWin();
    }

    ;

	public FirstWin() {
        //OKNO
        JFrame frame = new JFrame("Kalkulator");
        frame.setSize(new Dimension(220, 270));
		//-------------------//
        //test kontener//
        JPanel pasek = (JPanel) frame.getContentPane();
        pasek.setLayout(new BorderLayout());
        JToolBar tool = new JToolBar();
        tool.add(ToolPrzecinek);
        ToolPrzecinek.addActionListener(this);
        tool.setFloatable(false);
		//-----test-----//

        //Ustawianie Layoutu  BorderLayout 
        JPanel pane = new JPanel();
        pasek.add(pane, BorderLayout.CENTER);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
		//-------------------//	

        //Tekst do wprowadzania obliczen
        tekst.setHorizontalAlignment(JTextField.RIGHT);
        tekst.setEditable(false);
        tekst.setBackground(Color.WHITE);
        Font font = new Font("Segoe Script", Font.ITALIC, 20);
        tekst.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        pane.add(tekst, c);
		//-------------------//

        //Siatka z liczbami
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 0, 5, 11));

        b0.addActionListener(this);
        b1.addActionListener(this);

        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        bPlus.addActionListener(this);
        bMinus.addActionListener(this);
        bMnoznik.addActionListener(this);
        bDzielnik.addActionListener(this);
        bSum.addActionListener(this);
        bC.addActionListener(this);
        bB.addActionListener(this);
        bK.addActionListener(this);
        bP.addActionListener(this);

        panel.add(new JLabel("v 1.031"));
        panel.add(bK);
        panel.add(bC);
        panel.add(bB);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(bPlus);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(bMinus);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(bMnoznik);
        panel.add(bP);
        panel.add(b0);
        panel.add(bSum);
        panel.add(bDzielnik);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 200;
        c.weightx = 1.0;
        c.weighty = 3.0;
        c.gridwidth = 1;
        c.gridheight = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        pane.add(panel, c);
		//-------------------//

        //Nasluchiwacz
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
		//-------------------//

        //Po utworzeniu okna wskazuje pozycje i czy okno ma byc widoczne
        frame.setLocation(200, 150);
        frame.setVisible(true);
        frame.setResizable(false);
        //-------------------//
    }

    public static String checker(String stream, boolean UsunReszte) {
        String zmiana = "";
        boolean minus = false;
        if (stream.charAt(0) == '-') {
            minus = true;
            zmiana = stream;
            stream = "";
            for (int x = 1; x < zmiana.length(); x++) {
                stream += zmiana.charAt(x);
            }
        };

        if (stream.length() == 1) {
            return stream;
        } else if (stream.length() == 2) {
            return stream;
        } else if (48 > stream.charAt(stream.length() - 1)) {
            return stream;
        }
        int ster = 0, poprzednia = 0, ilosc, znaki = 0;
        boolean first = true;
        double potega;
        for (int zm = 0; zm < stream.length(); zm++) {
            if (stream.charAt(zm) == '*' || stream.charAt(zm) == '/' || stream.charAt(zm) == '+' || stream.charAt(zm) == '-' || stream.charAt(zm) == '^') {
                ster++;
            }
        }
        ilosc = ster;
        double[] liczba = new double[ster + 3];
        char[] dzialanie = new char[ster + 3];
        for (int z = 0; z < ster + 3; z++) {
            dzialanie[z] = '0';
        }
        for (int z = 0; z < ster + 3; z++) {
            liczba[z] = 0;
        }
        ster = 0;
        for (int zm = 0; zm < stream.length(); zm++) {
            zmiana = "";
            if (stream.charAt(zm) == '*' || stream.charAt(zm) == '/' || stream.charAt(zm) == '+' || stream.charAt(zm) == '-' || stream.charAt(zm) == '^') {
                dzialanie[ster] = stream.charAt(zm);
                if (first == false) {
                    for (int z = poprzednia + 1; z < zm; z++) {
                        zmiana += stream.charAt(z);
                    }
                    liczba[ster] = Double.parseDouble(zmiana);
                    ster++;
                } else {
                    first = false;
                    for (int z = 0; z < zm; z++) {
                        zmiana += stream.charAt(z);
                    }
                    liczba[ster] = Double.parseDouble(zmiana);
                    if (minus == true) {
                        liczba[ster] *= -1;
                        minus = false;
                    }
                    ster++;
                }
                poprzednia = zm;
            };
            if (stream.length() == zm + 1) {
                for (int z = poprzednia + 1; z < zm + 1; z++) {
                    zmiana += stream.charAt(z);
                }
                liczba[ster] = Double.parseDouble(zmiana);
                break;
            }
        };
        first = false;
        do {
            if (first == false) {
                for (znaki = 0; znaki < ilosc + 3; znaki++) {
                    if (dzialanie[znaki] == '^') {
                        potega = liczba[znaki];
                        if (liczba[znaki + 1] != 0) {
                            for (int z = 1; z < liczba[znaki + 1]; z++) {
                                liczba[znaki] = liczba[znaki] * potega;
                            }
                        } else {
                            liczba[znaki] = 1;
                        }
                        first = true;
                        break;
                    };
                }
            };
            if (first == false) {
                for (znaki = 0; znaki < ilosc + 3; znaki++) {
                    if (dzialanie[znaki] == '*') {
                        liczba[znaki] = liczba[znaki] * liczba[znaki + 1];
                        first = true;
                        break;
                    }
                    if (dzialanie[znaki] == '/') {
                        liczba[znaki] = liczba[znaki] / liczba[znaki + 1];
                        first = true;
                        break;
                    };
                }
            };
            if (first == false) {
                for (znaki = 0; znaki < ilosc + 3; znaki++) {
                    if (dzialanie[znaki] == '+') {
                        liczba[znaki] = liczba[znaki] + liczba[znaki + 1];
                        first = true;
                        break;
                    }
                    if (dzialanie[znaki] == '-') {
                        liczba[znaki] = liczba[znaki] - liczba[znaki + 1];
                        first = true;
                        break;
                    };
                }
            };

            if (first == true) {
                for (int f = znaki; f < ilosc; f++) {
                    liczba[f + 1] = liczba[f + 2];
                    dzialanie[f] = dzialanie[f + 1];
                    first = false;
                }
            };
        } while (dzialanie[0] != '0');

        if (UsunReszte == true) {
            stream = "";
            zmiana = Double.toString(Math.round(liczba[0]));
            for (int z = 0; z < zmiana.length() - 2; z++) {
                stream += zmiana.charAt(z);
            }
            return stream;
        } else {
            return Double.toString(liczba[0]);
        }
    }

    ;

	
	
	
	public void actionPerformed(ActionEvent evt) {
        if (wartosc == "'-' na pierwszej pozycji") {
            wartosc = "0";
        }
        if (wartosc.length() != 13) {

            for (int x = wartosc.length() - 1; x > 0; x--) {
                if (wartosc.charAt(x) <= 47) {
                    if (wartosc.charAt(x) == '.') {
                        przecinek = true;
                    } else {
                        przecinek = false;
                    }
                    break;
                } else {
                    przecinek = false;
                }
            }

            if (przecinek != true && wartosc.charAt(wartosc.length() - 1) > 47 && wartosc.charAt(wartosc.length() - 1) != '^') {
                if (evt.getSource() == bP) {
                    wartosc += ".";
                    special = wartosc.length() - 1;
                }
            }

            if (wartosc.charAt(wartosc.length() - 1) > 47 && wartosc.charAt(wartosc.length() - 1) < 58) {
                if (evt.getSource() == bPlus) {

                    wartosc += "+";
                } else if (evt.getSource() == bMinus) {
                    if (wartosc == "0") {
                        wartosc = "-";
                    } else {
                        wartosc += "-";
                    }
                } else if (evt.getSource() == bMnoznik) {
                    wartosc += "*";
                } else if (evt.getSource() == bDzielnik) {
                    wartosc += "/";
                } else if (evt.getSource() == bK) {
                    wartosc += "^";
                }
            }

            if (evt.getSource() == b0) {
                if (wartosc.charAt(wartosc.length() - 1) != '/') {
                    if (wartosc != "0") {
                        wartosc += "0";
                    }
                } else {
                    wartosc = "Idiota";
                }

            };
            if (wartosc == "0") {
                wartosc = "";
            }
            if (evt.getSource() == b1) {
                wartosc += "1";
            } else if (evt.getSource() == b2) {
                wartosc += "2";
            } else if (evt.getSource() == b3) {
                wartosc += "3";
            } else if (evt.getSource() == b4) {
                wartosc += "4";
            } else if (evt.getSource() == b5) {
                wartosc += "5";
            } else if (evt.getSource() == b6) {
                wartosc += "6";
            } else if (evt.getSource() == b7) {
                wartosc += "7";
            } else if (evt.getSource() == b8) {
                wartosc += "8";
            } else if (evt.getSource() == b9) {
                wartosc += "9";
            }
            if (wartosc == "") {
                wartosc = "0";
            }
        };

        if (evt.getSource() == bSum) {
            wartosc = checker(wartosc, false);
        }

        if (evt.getSource() == bC) {
            wartosc = "0";
            special = wartosc.length() - 1;
        } else if (evt.getSource() == bB) {
            pomoc = "";
            for (int z = 0; z < wartosc.length() - 1; z++) {
                pomoc += wartosc.charAt(z);
            }
            if (pomoc != "") {
                wartosc = pomoc;
            } else {
                wartosc = "0";
            }

        };

        if (evt.getSource() == ToolPrzecinek) {
            kropka = !kropka;
        }

        tekst.setText(wartosc);
    }
}
