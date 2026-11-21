import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThreeEventListeners {
    public static void main(String[] args) {

        // 1. TOP-LEVEL CONTAINER: JFrame
        JFrame frame = new JFrame("ActionListener, KeyListener, dan MouseListener");

        // 3. ATOMIC COMPONENT: JLabel, JTextField, JButton
        JLabel labelNama = new JLabel("Masukkan Nama: ");
        JTextField textField = new JTextField(15);
        JButton button = new JButton("Sapa saya!");
        JLabel infoKarakter = new JLabel("Jumlah karakter: 0", SwingConstants.CENTER);
        JLabel infoKlik = new JLabel("Jumlah klik panel: 0", SwingConstants.CENTER);
        JLabel infoPanel = new JLabel("Arahkan mouse ke sini");

        // 2. INTERMEDIATE CONTAINER: JPanel (tiga panel)

        // Panel input memakai FlowLayout
        JPanel panelInput = new JPanel(new FlowLayout());
        panelInput.add(labelNama);
        panelInput.add(textField);
        panelInput.add(button);

        // Panel mouse (kotak abu-abu) memakai FlowLayout bawaan JPanel
        JPanel panelMouse = new JPanel();
        panelMouse.setPreferredSize(new Dimension(200, 200));
        panelMouse.setBackground(Color.lightGray);
        panelMouse.add(infoPanel);

        // Panel info memakai GridLayout (1 baris, 2 kolom)
        JPanel panelInfo = new JPanel(new GridLayout(1, 2));
        panelInfo.add(infoKarakter);
        panelInfo.add(infoKlik);

        // 5. EVENT LISTENER (interface): ActionListener untuk tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText().trim();
                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nama belum diisi!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Halo, " + nama + "! Selamat belajar GUI.");
                }
            }
        });

        // 5. EVENT LISTENER (adapter class): KeyAdapter untuk text field
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                infoKarakter.setText("Jumlah karakter: " + textField.getText().length());
            }
        });

        // 5. EVENT LISTENER (adapter class): MouseAdapter untuk panel
        int[] jumlahKlik = {0};
        panelMouse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelMouse.setBackground(Color.cyan);
                infoPanel.setText("Mouse masuk ke panel");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelMouse.setBackground(Color.lightGray);
                infoPanel.setText("Mouse keluar dari panel");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                jumlahKlik[0]++;
                infoKlik.setText("Jumlah klik panel: " + jumlahKlik[0]);
                JOptionPane.showMessageDialog(frame, "Anda mengklik Panel");
            }
        });

        // 4. LAYOUT: frame memakai BorderLayout
        frame.setLayout(new BorderLayout());
        frame.add(panelInput, BorderLayout.NORTH);
        frame.add(panelMouse, BorderLayout.CENTER);
        frame.add(panelInfo, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 340);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
