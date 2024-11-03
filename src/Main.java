import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu de Memoire");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        List<JButton> cards = createCards(16);
        for (JButton card : cards) {
            panel.add(card);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static List<JButton> createCards(int numCards) {
        List<JButton> cards = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (int i = 1; i <= numCards / 2; i++) {
            values.add(i);
            values.add(i);
        }

        Collections.shuffle(values);

        final JButton[] firstCard = {null};
        final int[] firstValue = {-1};

        for (int i = 0; i < numCards; i++) {
            JButton button = new JButton("?");
            int cardValue = values.get(i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setText(String.valueOf(cardValue));
                    button.setEnabled(false);

                    if (firstCard[0] == null) {
                        firstCard[0] = button;
                        firstValue[0] = cardValue;
                    } else {
                        if (cardValue == firstValue[0]) {
                            firstCard[0] = null;
                        } else {
                            Timer timer = new Timer(500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    button.setText("?");
                                    button.setEnabled(true);
                                    firstCard[0].setText("?");
                                    firstCard[0].setEnabled(true);
                                    firstCard[0] = null;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    }
                }
            });

            cards.add(button);
        }

        return cards;
    }
}
