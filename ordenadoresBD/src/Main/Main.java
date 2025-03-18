package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





class Ordenador{
	
	private String codOrdenador;
	private List<Alumno> alumnos;
	
	
	public Ordenador(String codOrdenador) {
		
		this.setCodOrdenador(codOrdenador);
		

	}
	
	public Ordenador(String codOrdenador, List<Alumno> alumnos) {
		super();
		this.setCodOrdenador(codOrdenador);
		this.setAlumnos(alumnos);
		

	}

	public String getCodOrdenador() {
		return codOrdenador;
	}

	public void setCodOrdenador(String codOrdenador) {
		this.codOrdenador = codOrdenador;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/

class Alumno{
	
	private String codAlumno;
	private Aula aula;
	private Ordenador ord;
	
	public Alumno(String codAlumno ) {
		super();
		this.codAlumno = codAlumno;
		
	}
	
	public Alumno(String codAlumno, Aula aula, Ordenador ord) {
		super();
		this.codAlumno = codAlumno;
		this.aula = aula;
		this.ord = ord;
	}
	
	
	public String getCodAlumno() {
		return codAlumno;
	}
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	public Aula getAula() {
		return aula;
	}
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	public Ordenador getOrd() {
		return ord;
	}
	public void setOrd(Ordenador ord) {
		this.ord = ord;
	}
	
}
/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
class AlumnoBd{
	
	Connection conn =null;
	
	public AlumnoBd (Conexion conexion) {
		this.conn=conexion.getConnection();
	}
	
	public Aula ObtenerAlumnos() throws SQLException{
		
		 String sql ="select * from alumnos";
		 String codAl, codAula,codOrd;
		 Alumno alumno;
		 Ordenador ord;
		 Aula aula=null;
		 
		PreparedStatement ps= this.conn.prepareStatement(sql);
		
		try {
			
			ResultSet rs =ps.executeQuery();
			
				while(rs.next()) {
					
					codAl= rs.getString("codAl");
					codAula = rs.getString("codAula");
					codOrd = rs.getString("codOrd");
					
					if(aula==null)
					aula= new Aula(codAula);
					
					
					ord=new Ordenador(codOrd);
					alumno = new Alumno(codAl,aula,ord);
					
					//añadir el alumno a la lista del aula.
					
					if(!aula.getAlumnos().contains(alumno))
					aula.getAlumnos().add(alumno);
					
					if(!aula.getOrds().contains(ord))
						aula.getOrds().add(ord);
					//actualizarListaAlumnosOrd(aula)
					//buscar rellenar la lista de alumnos en ordenador
					
					
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return aula;
	}
	
	//buscar rellenar la lista de alumnos en ordenador paraa utilizar con el de arriba
	public void buscarAlumnos() {
		
	}
}
	
	
/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/	
	
	


class Aula{
	
	private String codAula;
	private List<Alumno> alumnos;
	private List<Ordenador> ords;
	
	private HashMap <Ordenador,List<Alumno>>  ord_Alumnos;

	
	public Aula(String codAula, List<Alumno> alumnos, List<Ordenador> ords,
			HashMap<Ordenador, List<Alumno>> ord_Alumnos) {
		super();
		this.codAula = null;
		this.alumnos = new ArrayList<Alumno>();
		this.ords = new ArrayList<Ordenador>();
		this.ord_Alumnos = new HashMap<Ordenador,List<Alumno>>();
	}
	
	public Aula(String codAula) {
		
		this.codAula = null;
	}	


	public String getCodAula() {
		return codAula;
	}


	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}


	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	public List<Ordenador> getOrds() {
		return ords;
	}


	public void setOrds(List<Ordenador> ords) {
		this.ords = ords;
	}


	public HashMap<Ordenador, List<Alumno>> getOrd_Alumnos() {
		return ord_Alumnos;
	}


	public void setOrd_Alumnos(HashMap<Ordenador, List<Alumno>> ord_Alumnos) {
		this.ord_Alumnos = ord_Alumnos;
	}
	
	
	
	
/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/	
	
}

class Conexion{
		
	String bd="OrdenadorPr";
	String usuario ="root";
	String passw = "";
	String servidor="localhost";
	String puerto ="3306";
	
	String url="jdbc:mysql://" + servidor + ":" + puerto + "/" + bd;
	Connection conn=null;
	
	
		public Conexion() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				try {
					conn=DriverManager.getConnection(url,usuario,passw);
					
				} catch (SQLException e) {
					System.out.println("Error. Clase no encontrada.");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error en el SQL.");
			}
		}

		public Connection getConnection() {
			return conn;
		}
	
}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/


class Entrada{
	
	
}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/

public class Main {

	public static void main(String []args) {
		
	}
}
