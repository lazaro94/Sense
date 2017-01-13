package publicidad;

import javax.swing.JPanel;

import org.omg.CORBA.portable.InputStream;

import com.data.sponsor.FactoryConnection;

import entidades.Contrato;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Publicidades extends JPanel {

	private ViewContratos vc;
	private JPanel panelFooter;
	private RegistrarPago rp;
	
	public Publicidades() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		addPanels();
		
		JPanel panelButtons = new JPanel();
		add(panelButtons);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(" ");
		panelButtons.add(label);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		panelButtons.add(btnNuevo);
		
		JLabel label_1 = new JLabel(" ");
		panelButtons.add(label_1);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		panelButtons.add(btnModificar);
		
		JLabel label_2 = new JLabel(" ");
		panelButtons.add(label_2);
		
		JButton btnAnular = new JButton("Anular");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anular();
			}
		});
		panelButtons.add(btnAnular);
		
		JLabel label_3 = new JLabel(" ");
		panelButtons.add(label_3);
		
		JButton btnRegistroPagos = new JButton("Registro Pagos");
		btnRegistroPagos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPago();
			}
		});
		panelButtons.add(btnRegistroPagos);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickPDF();
			}
		});
		add(btnPdf);
		
		panelFooter = new JPanel();
		add(panelFooter);
	}
	
	private void addPanels(){
		vc = new ViewContratos();
		this.removeAll();
		this.add(vc);
		this.revalidate();
		this.repaint();
	}
	
	private void nuevo(){
		vc.nuevo();
	}
	
	private void editar(){
		vc.editar();
	}
	
	private void anular(){
		vc.anular();
	}
	
	private void registrarPago(){
		Contrato c = null;
		c=vc.getContrato();
		rp = new RegistrarPago();
		if(c==null){
			return; // Si viene NULL es porque no había un sponsor seleccionado en el otro pane.
		}
		rp.setContrato(c);
		panelFooter.removeAll();
		panelFooter.add(rp);
		panelFooter.revalidate();
		panelFooter.repaint();
	}
	
	private void clickPDF(){
		
		JasperReport report = null;
		//InputStream path = (InputStream)this.getClass().getResourceAsStream("C:/Users/Lazaro/git/Sense/src/reportsComprobantePago.jrxml");
		JasperPrint print = null;
		String path = "C:/Users/Lazaro/git/Sense/src/reportsComprobantePago.jrxml";

		try{
			report = JasperCompileManager.compileReport(path);
			HashMap parameters = new HashMap();
			print = JasperFillManager.fillReport(report, parameters);
			JasperViewer.viewReport(print, false);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
