package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidad.Cliente;
import util.MySqlConectar;

public class MySqlCliente {
	public int addCliente(Cliente cli){
		int estado=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=new MySqlConectar().getConectar();
			String sql="insert into cliente values(null,?,?,?,?)";			
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, cli.getNombre());
			pstm.setString(2, cli.getApellido());
			pstm.setString(3, cli.getSexo());
			pstm.setString(4, cli.getFecnac());
			estado=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return estado;
	}
	
	public ArrayList<Cliente> listarCliente(){
		ArrayList<Cliente> data= new ArrayList<Cliente>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=new MySqlConectar().getConectar();
			String sql="select*from cliente";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			Cliente cli=null;
			
			while(rs.next()){
				cli=new Cliente();
				cli.setCodigo(rs.getInt(1));
				cli.setNombre(rs.getString(2));
				cli.setApellido(rs.getString(3));
				cli.setSexo(rs.getString(4));
				cli.setFecnac(rs.getString(5));
				data.add(cli);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
//FIN DE LA CLASE	
}
