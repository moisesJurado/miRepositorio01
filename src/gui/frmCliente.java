package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.MySqlCliente;
import entidad.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCliente extends JFrame {
	MySqlCliente objeto = new MySqlCliente();
	DefaultTableModel modelo = new DefaultTableModel();

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFecNac;
	private JTable tblCliente;
	private JComboBox cboFiltrar;
	private JComboBox cboSexo;
	private JButton btnGrabar;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCliente frame = new frmCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(27, 36, 93, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 59, 93, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(27, 81, 93, 14);
		contentPane.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(27, 103, 93, 14);
		contentPane.add(lblSexo);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(27, 128, 93, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtCodigo = new JTextField();
		txtCodigo.setToolTipText("");
		txtCodigo.setBounds(126, 33, 201, 17);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(126, 56, 201, 17);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(126, 78, 201, 17);
		contentPane.add(txtApellido);
		
		txtFecNac = new JTextField();
		txtFecNac.setColumns(10);
		txtFecNac.setBounds(126, 126, 201, 17);
		contentPane.add(txtFecNac);
		
		cboSexo = new JComboBox();
		cboSexo.setModel(new DefaultComboBoxModel(new String[] {"masculino", "femenino"}));
		cboSexo.setBounds(126, 100, 201, 17);
		contentPane.add(cboSexo);
		
		cboFiltrar = new JComboBox();
		cboFiltrar.setModel(new DefaultComboBoxModel(new String[] {"masculino", "femenino"}));
		cboFiltrar.setBounds(228, 154, 99, 17);
		contentPane.add(cboFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 190, 521, 192);
		contentPane.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTblCliente(arg0);
			}
		});
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedTblCliente(e);
			}
		});
		scrollPane.setViewportView(tblCliente);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Sexo");
		modelo.addColumn("Fecha de Nac.");
		tblCliente.setModel(modelo);
		listar();
		
		btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnGrabar(arg0);
			}
		});
		btnGrabar.setBounds(399, 36, 89, 23);
		contentPane.add(btnGrabar);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(399, 150, 89, 23);
		contentPane.add(btnFiltrar);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(244, 0, 99, 14);
		contentPane.add(lblCliente);
	}
	
	
	
	
	void mostrar(int fila){
		String codigo,nombre,apellido,sexo,fecnac;
		codigo=tblCliente.getValueAt(fila, 0).toString();
		nombre=tblCliente.getValueAt(fila, 1).toString();
		apellido=tblCliente.getValueAt(fila, 2).toString();
		sexo=tblCliente.getValueAt(fila, 3).toString();
		fecnac=tblCliente.getValueAt(fila, 4).toString();
		txtCodigo.setText(codigo);
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		cboSexo.setSelectedItem(sexo);
		txtFecNac.setText(fecnac);
	}

	void listar(){
		modelo.setRowCount(0);
		ArrayList<Cliente> info = objeto.listarCliente();
		for (Cliente cli : info) {
			Object fila[]={ cli.getCodigo(),cli.getNombre(),cli.getApellido(),cli.getSexo(),cli.getFecnac()};
			modelo.addRow(fila);
		}
	}

	protected void mouseClickedTblCliente(MouseEvent e) {
		int fila=tblCliente.getSelectedRow();
		mostrar(fila);
	}
	protected void keyReleasedTblCliente(KeyEvent arg0) {
		int fila=tblCliente.getSelectedRow();
		mostrar(fila);
	}
	
	protected void actionPerformedBtnGrabar(ActionEvent arg0) {
		String /*codigo,*/nombre,apellido,sexo,fecnac;
		//codigo=txtCodigo.getText();
		nombre=txtNombre.getText();
		apellido=txtApellido.getText();
		sexo=cboSexo.getSelectedItem().toString();
		fecnac=txtFecNac.getText();
		
		Cliente cli=new Cliente();
		
		//cli.setCodigo(Integer.parseInt(codigo));
		cli.setNombre(nombre);
		cli.setApellido(apellido);
		cli.setSexo(sexo);
		cli.setFecnac(fecnac);
		int estado = objeto.addCliente(cli);
		if (estado==1) {
			JOptionPane.showMessageDialog(null, "OK");
			listar();
		} else {
			JOptionPane.showMessageDialog(null, "ERROR");
			listar();
		}
		
	}
//FIN DE CLASE	
}
