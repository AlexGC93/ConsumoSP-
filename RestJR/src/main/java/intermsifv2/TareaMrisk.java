package intermsifv2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TareaMrisk {

	private static final Logger logger = Logger.getLogger(TareaMrisk.class.getName());
	
	private static final Map<String, String> codigoMensajes = new HashMap<>();

    static {
        // Agregar los códigos de retorno y sus mensajes asociados al Map
        codigoMensajes.put("100", "No existe la cuenta contable en catálogo");
        codigoMensajes.put("102", "El número de auxiliar es igual a 0 y la cuenta contable debe llevar auxiliar");
        codigoMensajes.put("106", "La póliza se encuentra descuadrada. Cargo y abonos no cuadran");
        codigoMensajes.put("118", "La cuenta contable no lleva auxiliar pero el valor campo es diferente de null");
        codigoMensajes.put("144", "La cuenta no es de detalle");
        codigoMensajes.put("145", "El valor de moneda no está permitido en la cuenta");
        codigoMensajes.put("148", "Cuenta no permitida para el Centro de costo origen");
        codigoMensajes.put("149", "Cuenta no permitida para el Centro de costo destino");
        codigoMensajes.put("163", "Fecha captura de integración incorrecta");
        codigoMensajes.put("164", "Fecha valor de integración incorrecta");
        codigoMensajes.put("165", "Campo Sucursal vacío o null que el valor no se encuentra en catálogo");
        codigoMensajes.put("167", "No cuadran los importes en las cuentas correlativa vs cuenta ordinaria");
        codigoMensajes.put("174", "No existe información en la tabla de trabajo dependiendo al valor del usuario ingresado");
        codigoMensajes.put("175", "El importe de la factura es igual a 0");
        codigoMensajes.put("666", "La póliza tiene diferentes fechas de captura, o la fecha es inválida o es día feriado");
    }


	public static void main(String[] args) throws ParseException {
		try {
			Class.forName("com.informix.jdbc.IfxDriver");
		} catch (Exception e) {
			System.out.println("ERROR: failed to load Informix JDBC driver.");
			e.printStackTrace();
			return;
		}
		BufferedReader obj = null;
		File doc = new File("C:\\BalanzaUDISp_20230413.txt");
		
		setupLogger();
		DatabaseConfigXML employees = readDatabaseConfigFromXML();
		
	

		String url = employees.getUrl();
		String usuario = employees.getUsername();
		String contraseñacifrada = employees.getPassword();

		String encryptedPassword = contraseñacifrada; // Contraseña cifrada con AES-256 en Base64
		String secretKey = "miClaveSecreta123456"; // Clave secreta para desencriptar

		AES256Decryptor decryptor = new AES256Decryptor();
		String contraseña = decryptor.decrypt(encryptedPassword, secretKey);
		// System.out.println("Contraseña desencriptada: " + contraseña);

		try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
			logger.info("Conexión a la base de datos establecida correctamente");

		
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(doc))) {
				String line;
				
                while ((line = bufferedReader.readLine()) != null) {
                	System.out.println("                       __\r\n"
                			+ "                     .'  '.\r\n"
                			+ "                 _.-'/  |  \\\r\n"
                			+ "    ,        _.-\"  ,|  /  0 `-.\r\n"
                			+ "    |\\    .-\"       `--\"\"-.__.'=====================-,\r\n"
                			+ "    \\ '-'`        .___.--._)=========================|\r\n"
                			+ "     \\            .'     |                           |\r\n"
                			+ "      |     /,_.-'       |        Aplicacion         |\r\n"
                			+ "    _/   _.'(            |      Desarrollada por:    |\r\n"
                			+ "   /  ,-' \\  \\           |Alejandro Gonzalez Canuto  |\r\n"
                			+ "   \\  \\    `-'           |                           |\r\n"
                			+ "    `-'                   '--------------------------'");
                    // Dividir la línea en partes
                    String[] parts = line.split(",");
                    String sql = "{call bdicont:\"informix\".sp_carga_poliza(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                    CallableStatement callableStatement = connection.prepareCall(sql);
                    callableStatement.setString(1, parts[0]);  // v_cempresa
                    callableStatement.setString(2, parts[1]);  // V_cccosto_orig
                    callableStatement.setString(3, parts[2]);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                    java.util.Date parsedDate = dateFormat.parse(parts[3]);
                    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                    callableStatement.setDate(4, sqlDate); // V_dfecha_captura
                    callableStatement.setString(5, parts[4]);  // V_ccuenta
                    callableStatement.setString(6, parts[5]);  // V_csubcta
                    callableStatement.setString(7, parts[6]);  // V_csubsubcta
                    callableStatement.setString(8, parts[7]);  // V_cssubsubcta
                    callableStatement.setString(9, parts[8]);  // V_csssubsubcta
                    callableStatement.setString(10, parts[9]); // V_csector
                    callableStatement.setString(11, parts[10]); // V_cregional
                    callableStatement.setString(12, parts[11]); // V_csucursal
                    callableStatement.setString(13, parts[12]); // V_cnro_auxiliar
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("ddMMyyyy");
                    java.util.Date parsedDate2 = dateFormat2.parse(parts[13]);
                    java.sql.Date sqlDate2 = new java.sql.Date(parsedDate2.getTime());
                    callableStatement.setDate(14, sqlDate2);
                    callableStatement.setString(15, parts[14]); // V_cmoneda
                    callableStatement.setString(16, parts[16]); // V_cimporte
                    callableStatement.setDouble(17, Double.parseDouble(parts[15])); // V_cnaturaleza
                    callableStatement.setString(18, parts[17]); // V_cconcepto
                    callableStatement.setString(19, "ctxpag"); // V_csysdynamics
                    callableStatement.setString(20, "1"); // V_csysdynamics
                    logger.info("Datos enviados al procedimiento almacenado:");
                    logger.info("v_cempresa: " + parts[0]);
                    logger.info("V_cccosto_orig: " + parts[1]);
                    logger.info("V_cusuario: " + parts[2]);
                    logger.info("V_dfecha_captura: " +  sqlDate);
                    logger.info("V_ccuenta: " + parts[4]);
                    logger.info("V_csubcta: " + parts[5]);
                    logger.info("V_csubsubcta: " + parts[6]);
                    logger.info("V_cssubsubcta: " + parts[7]);
                    logger.info("V_csssubsubcta: " + parts[8]);
                    logger.info("V_csector: " + parts[9]);
                    logger.info("V_cregional: " + parts[10]);
                    logger.info("V_csucursal: " + parts[11]);
                    logger.info("V_cnro_auxiliar: " + parts[12]);
                    logger.info("V_dfecha: " +  sqlDate2);
                    logger.info("V_cmoneda: " + parts[14]);
                    logger.info("V_cnaturaleza: " + parts[16]);//V_cimporte
                    logger.info("V_cimporte: " + parts[15]);//V_cnaturaleza
                    logger.info("V_cconcepto: " + parts[17]);
                    ResultSet rs = callableStatement.executeQuery();
		            while (rs.next()) {
		                String codigoRetorno = rs.getString(1);
		                String mensajeRetorno = rs.getString(2);
		                logger.info("Código de retorno: " + codigoRetorno);
		                logger.info("Mensaje de retorno: " + mensajeRetorno);
		                
		                if ("00000".equals(codigoRetorno)) {
		                	String sql2 = "{call bdicont:\"informix\".sp_integra_archivo_poliza(?, ?, ?, ?, ?, ?)}";
		                	CallableStatement callableStatement2 = connection.prepareCall(sql2);
		                	SimpleDateFormat dateFormatSP2 = new SimpleDateFormat("ddMMyyyy");
		                    java.util.Date parsedDateSP2 = dateFormatSP2.parse(parts[3]);
		                    java.sql.Date sqlDateSP2 = new java.sql.Date(parsedDateSP2.getTime());
		                    callableStatement2.setDate(1, sqlDateSP2); //V_dfecha_captura
		                    callableStatement2.setString(2, parts[0]);//Empresa
		                    callableStatement2.setString(3, "ctxpag"); // V_csysdynamics
		                    callableStatement2.setString(4, parts[2]); // V_usuario
		                    callableStatement2.setString(5, "*");
		                    callableStatement2.setString(6, "*");
		                    logger.info("V_dfecha_captura: " + sqlDateSP2);
		                    logger.info("v_cempresa: " + parts[0]);
		                    logger.info("V_cusuario: " + parts[2]);
		                    ResultSet rs2 = callableStatement2.executeQuery();
		                    while (rs2.next()) {
		                    	String codigoRetorno2 = rs2.getString(1);
				                String mensajeRetorno2 = rs2.getString(2);
				                logger.info("Código de retorno del Sp2: " + codigoRetorno2);
				                logger.info("Mensaje de retorno del Sp2: " + mensajeRetorno2);
				                
				                if (codigoMensajes.containsKey(codigoRetorno2.trim())) {
				                    String mensaje = codigoMensajes.get(codigoRetorno2.trim());
				                    System.out.println(mensaje);
				                } else {
				                    System.out.println("No se reconoce el codigo de error: " + codigoRetorno2);
				                }

							}
		                    rs2.close();	
						}
		            }
		            rs.close();
                    }
                
			} catch (IOException e) {
				handleException("Error al leer el archivo: " + e.getMessage(), e);
				return;
			}
			
			//Cerramos sesion para liberar recursos

			connection.close(); 

			
		} catch (SQLException e1) {
			handleException("Error en la conexion a la base de Datos: " + e1.getMessage(), e1);
			handleException("Error en la conexion a la base de Datos: " + e1.getStackTrace(), e1);
		} finally {
			try {
				if (obj != null)
					obj.close();
			} catch (IOException e2) {
				handleException("Error al cerrar el Documento : " + e2.getMessage(), e2);
			}
			// sendEmailWithLogs();
		}
	}

	private static void setupLogger() {
		try {
			// Configurar un FileHandler para el archivo de bitácora
			FileHandler fileHandler = new FileHandler("C:/log/tarea_mrisk.log");
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);

			// Configurar un ConsoleHandler para mostrar mensajes en la consola
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.INFO);
			consoleHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(consoleHandler);

			// Desactivar los ConsoleHandlers preexistentes
			for (Handler handler : logger.getHandlers()) {
				if (handler instanceof java.util.logging.ConsoleHandler) {
					handler.setLevel(Level.OFF);
				}
			}
		} catch (IOException e) {
			handleException("Error al configurar el FileHandler: " + e.getMessage(), e);
		}
	}

	private static void handleException(String message, Exception e) {
		System.err.println("Error: " + message);
		logger.log(Level.SEVERE, message, e);
		// sendEmail("Error", message + "\n\n" + e.getMessage());
	}

	

	private static DatabaseConfigXML readDatabaseConfigFromXML() {
		try {
			File configFile = new File("c://config.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(configFile);

			String url = document.getElementsByTagName("url").item(0).getTextContent();
			String username = document.getElementsByTagName("username").item(0).getTextContent();
			String password = document.getElementsByTagName("password").item(0).getTextContent();

			return new DatabaseConfigXML(url, username, password);
		} catch (Exception e) {
			// Manejar la excepción adecuadamente
			handleException("Error al leer la configuración de la base de datos desde XML: " + e.getMessage(), e);
			return null; // Devolver null si hay un error
		}
	}

}
