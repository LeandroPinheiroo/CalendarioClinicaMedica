/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import conexao.Conexao;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author weth767
 */
public class RelatorioPorcentagemProcedimentos {

    public void gerarRelatorio(boolean visualizar, Connection conexao) throws Exception {
        InputStream jasperFile = getClass().getResourceAsStream("/relatoriosjasper/RelatorioPorcentagemProcedimentos.jasper");
        if (jasperFile == null) {
            throw new Exception("Relatório não encontrado");
        }
        Map<String,Object> parameters = new HashMap();
        JasperPrint print = JasperFillManager.fillReport(jasperFile, null, conexao);
        if (visualizar) {
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            viewer.setTitle("Relatório");
        } else {
            JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());//
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
                    new FileOutputStream("Relatorio_Porcentagem_Procedimentos"+".pdf")));
            SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
            exporter.setConfiguration(configuration);

            exporter.exportReport();
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso");
        }
    }
}
