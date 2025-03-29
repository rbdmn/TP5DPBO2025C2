import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(1024,768);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(new Color(0, 60, 0));

        window.setForeground(Color.white);

        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel prodiLabel;
    private JRadioButton pendidikanIlmuKomputerRadioButton;
    private JRadioButton ilmuKomputerRadioButton;
    private JTextField prodiField;
    private String nimLama;

    // Fungsi validasi input kosong
    private boolean CekInputValid() {
        if (nimField.getText().trim().isEmpty() ||
                namaField.getText().trim().isEmpty() ||
                jenisKelaminComboBox.getSelectedItem() == null ||
                jenisKelaminComboBox.getSelectedItem().toString().trim().isEmpty() ||
                (!ilmuKomputerRadioButton.isSelected() && !pendidikanIlmuKomputerRadioButton.isSelected())) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Fungsi validasi NIM unik
    private boolean CekNilaiNim(String nim) {
        String sql = "SELECT * FROM mahasiswa WHERE nim = '" + nim + "'";
        ResultSet rs = database.selectQuery(sql);

        try {
            if (rs.next()) {
                return false; // NIM sudah ada
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();

        nimLabel.setForeground(Color.white);
        namaLabel.setForeground(Color.white);
        jenisKelaminLabel.setForeground(Color.white);
        prodiLabel.setForeground(Color.white);
        //asdsda
        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.white);

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-Laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        ButtonGroup prodiGroup = new ButtonGroup();
        prodiGroup.add(ilmuKomputerRadioButton);
        prodiGroup.add(pendidikanIlmuKomputerRadioButton);
        prodiGroup.clearSelection();

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1)
                {
                    insertData();
                }
                else
                {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Apakah Anda yakin ingin menghapus data ini?",
                            "Konfirmasi Hapus",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteData();
                    }
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                nimLama = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedProdi = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                if (prodiField != null) prodiField.setText(selectedProdi);

                // Ubah isi radio button sesuai data yang dipilih
                if (selectedProdi.equals("Ilmu Komputer")) {
                    ilmuKomputerRadioButton.setSelected(true);
                } else if (selectedProdi.equals("Pendidikan Ilmu Komputer")) {
                    pendidikanIlmuKomputerRadioButton.setSelected(true);
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Prodi"};


        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try
        {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while(resultSet.next())
            {
                Object[] row = new Object[5];
                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("Prodi");

                temp.addRow(row);
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mahasiswaTable.setModel(temp);

        mahasiswaTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        mahasiswaTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        mahasiswaTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        mahasiswaTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        mahasiswaTable.getColumnModel().getColumn(4).setPreferredWidth(200);

        mahasiswaTable.setFont(new Font("Arial", Font.PLAIN, 14));

        mahasiswaTable.setForeground(Color.BLACK);

        mahasiswaTable.setBackground(Color.LIGHT_GRAY);

        mahasiswaTable.setSelectionBackground(Color.BLUE);
        mahasiswaTable.setSelectionForeground(Color.WHITE);

        mahasiswaTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        mahasiswaTable.getTableHeader().setBackground(Color.DARK_GRAY);

        mahasiswaTable.getTableHeader().setForeground(Color.WHITE);

        return temp; // return juga harus diganti
    }

    public void insertData() {
        if (!CekInputValid()) return; // Validasi input kosong

        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String prodi = (prodiField != null) ? prodiField.getText() : "";

        if (ilmuKomputerRadioButton.isSelected()) {
            prodi = "Ilmu Komputer";
        } else if (pendidikanIlmuKomputerRadioButton.isSelected()) {
            prodi = "Pendidikan Ilmu Komputer";
        }

        // Validasi NIM unik
        if (!CekNilaiNim(nim)) {
            JOptionPane.showMessageDialog(null, "NIM sudah ada, gunakan NIM lain!", "Error Duplikasi Nilai NIM", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert data
        String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, prodi) VALUES ('" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + prodi + "');";
        database.insertUpdateDeleteQuery(sql);

        mahasiswaTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        if (!CekInputValid()) return; // Validasi input kosong

        String nimBaru = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String prodi = ilmuKomputerRadioButton.isSelected() ? "Ilmu Komputer" : "Pendidikan Ilmu Komputer";

        // Validasi NIM unik saat update
        if (!nimLama.equals(nimBaru) && !CekNilaiNim(nimBaru)) {
            JOptionPane.showMessageDialog(null, "NIM sudah ada, gunakan NIM lain!", "Error Duplikasi Nilai NIM", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update data jika nimLama tidak kosong
        if (!nimLama.isEmpty()) {
            String sql = "UPDATE mahasiswa SET nim = '" + nimBaru +
                    "', nama = '" + nama +
                    "', jenis_kelamin = '" + jenisKelamin +
                    "', prodi = '" + prodi +
                    "' WHERE nim = '" + nimLama + "';";

            database.insertUpdateDeleteQuery(sql);

            mahasiswaTable.setModel(setTable());
            clearForm();
            nimLama = ""; // Reset nimLama
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diubah terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void deleteData() {
        // Ambil NIM dari form
        String nim = nimField.getText();

        // Buat query SQL
        String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "';";
        database.insertUpdateDeleteQuery(sql);

        // Update tabel
        mahasiswaTable.setModel(setTable());

        // Bersihkan form
        clearForm();

        // Feedback
        System.out.println("Delete Berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        if (prodiField != null) {
            ilmuKomputerRadioButton.setSelected(false);
            pendidikanIlmuKomputerRadioButton.setSelected(false);
        }


        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}
