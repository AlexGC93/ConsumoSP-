package intermsifv2;



public class Operacion {
	
    private String v_cempresa;
    private String v_cccosto_orig;
    private String v_cusuario;
    private String v_dfecha_captura;
    private String v_ccuenta;
    private String v_csubcta;
    private String v_csubsubcta;
    private String v_cssubsubcta;
    private String v_csssubsubcta;
    private String v_csector;
    private String v_cregional;
    private String v_csucursal;
    private String v_cnro_auxiliar;
    private String v_dfecha;
    private String v_cmoneda;
    private String v_cnaturaleza;
    private String v_cimporte;
    private String v_cconcepto;
   /* private String v_csysdynamics;
    private String v_ctipoproceso;*/

    // Constructor
    public Operacion(String v_cempresa, String v_cccosto_orig, String v_cusuario, String v_dfecha_captura,
                     String v_ccuenta, String v_csubcta, String v_csubsubcta, String v_cssubsubcta,
                     String v_csssubsubcta, String v_csector, String v_cregional, String v_csucursal,
                     String v_cnro_auxiliar, String v_dfecha, String v_cmoneda, String v_cnaturaleza,
                     String v_cimporte, String v_cconcepto/*, String v_csysdynamics, String v_ctipoproceso*/) {
        this.v_cempresa = v_cempresa;
        this.v_cccosto_orig = v_cccosto_orig;
        this.v_cusuario = v_cusuario;
        this.v_dfecha_captura = v_dfecha_captura;
        this.v_ccuenta = v_ccuenta;
        this.v_csubcta = v_csubcta;
        this.v_csubsubcta = v_csubsubcta;
        this.v_cssubsubcta = v_cssubsubcta;
        this.v_csssubsubcta = v_csssubsubcta;
        this.v_csector = v_csector;
        this.v_cregional = v_cregional;
        this.v_csucursal = v_csucursal;
        this.v_cnro_auxiliar = v_cnro_auxiliar;
        this.v_dfecha = v_dfecha;
        this.v_cmoneda = v_cmoneda;
        this.v_cnaturaleza = v_cnaturaleza;
        this.v_cimporte = v_cimporte;
        this.v_cconcepto = v_cconcepto;
       /* this.v_csysdynamics = "ctxpag"; // Valor fijo para v_csysdynamics
        this.v_ctipoproceso = "1"; */     // Valor fijo para v_ctipoproceso
    }

   

    // ... (omitiendo para brevedad)

    // toString para imprimir la representación de cadena de la operación
    @Override
    public String toString() {
        return "Operacion{" +
                "v_cempresa='" + v_cempresa + '\'' +
                ", v_cccosto_orig='" + v_cccosto_orig + '\'' +
                ", v_cusuario='" + v_cusuario + '\'' +
                ", v_dfecha_captura=" + v_dfecha_captura +
                ", v_ccuenta='" + v_ccuenta + '\'' +
                ", v_csubcta='" + v_csubcta + '\'' +
                ", v_csubsubcta='" + v_csubsubcta + '\'' +
                ", v_cssubsubcta='" + v_cssubsubcta + '\'' +
                ", v_csssubsubcta='" + v_csssubsubcta + '\'' +
                ", v_csector='" + v_csector + '\'' +
                ", v_cregional='" + v_cregional + '\'' +
                ", v_csucursal='" + v_csucursal + '\'' +
                ", v_cnro_auxiliar='" + v_cnro_auxiliar + '\'' +
                ", v_dfecha=" + v_dfecha +
                ", v_cmoneda='" + v_cmoneda + '\'' +
                ", v_cnaturaleza='" + v_cnaturaleza + '\'' +
                ", v_cimporte=" + v_cimporte +
                ", v_cconcepto='" + v_cconcepto + '\'' +
               /*", v_csysdynamics='" + v_csysdynamics + '\'' +
                ", v_ctipoproceso='" + v_ctipoproceso + '\'' +*/
                '}';
    }

	public String getV_cempresa() {
		return v_cempresa;
	}

	public void setV_cempresa(String v_cempresa) {
		this.v_cempresa = v_cempresa;
	}

	public String getV_cccosto_orig() {
		return v_cccosto_orig;
	}

	public void setV_cccosto_orig(String v_cccosto_orig) {
		this.v_cccosto_orig = v_cccosto_orig;
	}

	public String getV_cusuario() {
		return v_cusuario;
	}

	public void setV_cusuario(String v_cusuario) {
		this.v_cusuario = v_cusuario;
	}

	public String getV_dfecha_captura() {
		return v_dfecha_captura;
	}

	public void setV_dfecha_captura(String v_dfecha_captura) {
		this.v_dfecha_captura = v_dfecha_captura;
	}

	public String getV_ccuenta() {
		return v_ccuenta;
	}

	public void setV_ccuenta(String v_ccuenta) {
		this.v_ccuenta = v_ccuenta;
	}

	public String getV_csubcta() {
		return v_csubcta;
	}

	public void setV_csubcta(String v_csubcta) {
		this.v_csubcta = v_csubcta;
	}

	public String getV_csubsubcta() {
		return v_csubsubcta;
	}

	public void setV_csubsubcta(String v_csubsubcta) {
		this.v_csubsubcta = v_csubsubcta;
	}

	public String getV_cssubsubcta() {
		return v_cssubsubcta;
	}

	public void setV_cssubsubcta(String v_cssubsubcta) {
		this.v_cssubsubcta = v_cssubsubcta;
	}

	public String getV_csssubsubcta() {
		return v_csssubsubcta;
	}

	public void setV_csssubsubcta(String v_csssubsubcta) {
		this.v_csssubsubcta = v_csssubsubcta;
	}

	public String getV_csector() {
		return v_csector;
	}

	public void setV_csector(String v_csector) {
		this.v_csector = v_csector;
	}

	public String getV_cregional() {
		return v_cregional;
	}

	public void setV_cregional(String v_cregional) {
		this.v_cregional = v_cregional;
	}

	public String getV_csucursal() {
		return v_csucursal;
	}

	public void setV_csucursal(String v_csucursal) {
		this.v_csucursal = v_csucursal;
	}

	public String getV_cnro_auxiliar() {
		return v_cnro_auxiliar;
	}

	public void setV_cnro_auxiliar(String v_cnro_auxiliar) {
		this.v_cnro_auxiliar = v_cnro_auxiliar;
	}

	public String getV_dfecha() {
		return v_dfecha;
	}

	public void setV_dfecha(String v_dfecha) {
		this.v_dfecha = v_dfecha;
	}

	public String getV_cmoneda() {
		return v_cmoneda;
	}

	public void setV_cmoneda(String v_cmoneda) {
		this.v_cmoneda = v_cmoneda;
	}

	public String getV_cnaturaleza() {
		return v_cnaturaleza;
	}

	public void setV_cnaturaleza(String v_cnaturaleza) {
		this.v_cnaturaleza = v_cnaturaleza;
	}

	public String getV_cimporte() {
		return v_cimporte;
	}

	public void setV_cimporte(String v_cimporte) {
		this.v_cimporte = v_cimporte;
	}

	public String getV_cconcepto() {
		return v_cconcepto;
	}

	public void setV_cconcepto(String v_cconcepto) {
		this.v_cconcepto = v_cconcepto;
	}



	public void setV_csysdynamics(String string) {
		// TODO Auto-generated method stub
		
	}

	/*public String getV_csysdynamics() {
		return v_csysdynamics;
	}

	public void setV_csysdynamics(String v_csysdynamics) {
		this.v_csysdynamics = "ctxpag";
	}

	public String getV_ctipoproceso() {
		return v_ctipoproceso;
	}

	public void setV_ctipoproceso(String v_ctipoproceso) {
		this.v_ctipoproceso = "1";
	}*/
	
}
