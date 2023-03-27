import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ParkingForm extends JFrame implements ActionListener {
    
    private final JLabel plateLabel = new JLabel("Placa");
    private final JLabel vehicleTypeLabel = new JLabel("Tipo de vehículo");
    private final JLabel entryTimeLabel = new JLabel("Hora de ingreso");
    private final JLabel vehicleNumberLabel = new JLabel("Número de vehículo");
    private final JTextField plateTextField = new JTextField();
    private final JComboBox<String> vehicleTypeComboBox = new JComboBox<>(new String[]{"Bicicleta", "Ciclomotor", "Motocicleta", "Carro"});
    private final JTextField entryTimeTextField = new JTextField();
    private final JTextField vehicleNumberTextField = new JTextField();
    private final JButton addButton = new JButton("Agregar");
    private final JButton clearButton = new JButton("Limpiar");
    private final JTextArea priceTextArea = new JTextArea();
    private final JTable vehicleTable = new JTable();
    private final DefaultTableModel model = new DefaultTableModel();
    
    private int vehicleNumber = 1;
    private final List<Vehicle> vehicles = new ArrayList<>();
    
    public ParkingForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Formulario de parqueo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        
        model.addColumn("Placa");
        model.addColumn("Tipo de vehículo");
        model.addColumn("Hora de ingreso");
        model.addColumn("Número de vehículo");
        model.addColumn("Precio");
        vehicleTable.setModel(model);
        
        
        JPanel componentsPanel = new JPanel();
        componentsPanel.setBorder(BorderFactory.createTitledBorder("Ingresar vehículo"));
        componentsPanel.setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(plateLabel);
        box.add(plateTextField);
        box.add(vehicleTypeLabel);
        box.add(vehicleTypeComboBox);
        box.add(entryTimeLabel);
        box.add(entryTimeTextField);
        box.add(vehicleNumberLabel);
        box.add(vehicleNumberTextField);
        box.add(Box.createVerticalStrut(10));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(clearButton);
        box.add(buttonsPanel);
        componentsPanel.add(box, BorderLayout.CENTER);
        
        
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vehículos ingresados"));
        tablePanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        
        JPanel pricePanel = new JPanel();
        pricePanel.setBorder(BorderFactory.createTitledBorder("Precios"));
        priceTextArea.setEditable(false);
        pricePanel.add(priceTextArea);
        
       
        add(componentsPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(pricePanel, BorderLayout.EAST);
        
        
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        setVisible(true);
    }
    
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addButton) {
       
        String plate = plateTextField.getText();
        String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
        Date entryTime = new Date();
        try {
            entryTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entryTimeTextField.getText());
        } catch (Exception ex) {
            entryTimeTextField.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entryTime));
        }
        
       
        int pricePerMinute = 0;
        switch (vehicleType) {
            case "Bicicleta":
            case "Ciclomotor":
                pricePerMinute = 20;
                break;
            case "Motocicleta":
                pricePerMinute = 30;
                break;
            case "Carro":
                pricePerMinute = 60;
                break;
        }
        long durationInMinutes = (new Date().getTime() - entryTime.getTime()) / (1000 * 60);
        int price = (int) (durationInMinutes * pricePerMinute);
        
        
        vehicles.add(new Vehicle(plate, vehicleType, entryTime, vehicleNumber, price));
        model.addRow(new Object[]{plate, vehicleType, entryTime, vehicleNumber, price});
        
      
        vehicleNumber++;
        plateTextField.setText("");
        vehicleTypeComboBox.setSelectedIndex(0);
        entryTimeTextField.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        vehicleNumberTextField.setText(String.valueOf(vehicleNumber));
        
        
        priceTextArea.setText(String.format("Bicicletas y Ciclomotores $20 COP minuto%nMotocicletas $30 COP minuto%nCarros $60 COP minuto"));
        
    } else if (e.getSource() == clearButton) {
       
        plateTextField.setText("");
        vehicleTypeComboBox.setSelectedIndex(0);
        entryTimeTextField.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        vehicleNumberTextField.setText(String.valueOf(vehicleNumber));
    }
}
public Vehiculo(String placa, String tipoVehiculo, Date horaEntrada, int numeroCarro, int precio) {
    this.placa = placa;
    this.tipoVehiculo = tipoVehiculo;
    this.horaEntrada = horaEntrada;
    this.numeroCarro = numeroCarro;
    this.precio = precio;
}

public String getPlaca() {
    return placa;
}

public String getTipoVehiculo() {
    return tipoVehiculo;
}

public Date getHoraEntrada() {
    return horaEntrada;
}

public int getNumeroCarro() {
    return numeroCarro;
}

public int getPrecio() {
    return precio;
}