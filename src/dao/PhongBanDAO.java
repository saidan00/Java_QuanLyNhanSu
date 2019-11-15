package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PhongBanDTO;
import util.MySqlDataAccessHelper;

public class PhongBanDAO {
	
	public ArrayList<PhongBanDTO> PhongBanAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<PhongBanDTO> arr = new ArrayList<PhongBanDTO>();

		String query = "SELECT * FROM phongban ";
		query += "pb LEFT JOIN nhanvien nv ON pb.matruongphong = nv.manv";

		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
			
				PhongBanDTO PhongBan = new PhongBanDTO();

				PhongBan.setMaPhong(rs.getInt("maphong"));
				PhongBan.setTenPhong(rs.getString("tenphong"));
				PhongBan.setMaTruongPhong(rs.getInt("matruongphong"));
				PhongBan.setSdtPhongBan(rs.getString("sdtphongban"));
				arr.add(PhongBan);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		
		conn.Close();
	
		return arr;
	}
	
	public void PhongBanAdd(PhongBanDTO PB) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		
		String sql = "INSERT INTO phongban (tenphong, matruongphong, sdtphongban) "
				+ "VALUES(?, ?, ?)";
		
		// prepare statement
		conn.prepare(sql);
		
		// bind values
		conn.bind(1, PB.getMaPhong());
		conn.bind(2, PB.getTenPhong());
		conn.bind(3, PB.getMaTruongPhong());
		conn.bind(4, PB.getSdtPhongBan());
		
		conn.executeUpdatePre();
		
		conn.Close();
	}
	
	
	public void PhongBanEdit(PhongBanDTO PB) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
        
        String  sql = "UPDATE phongban SET tenphong = ?, sdtphongban= ?, matruongphong = ?  WHERE maphg= ?";
     
 		conn.prepare(sql);

		conn.bind(1, PB.getTenPhong());
		conn.bind(2, PB.getSdtPhongBan());
		conn.bind(3, PB.getMaTruongPhong());
		
 		conn.bind(4, PB.getMaPhong());

 		conn.executeUpdatePre();
 		
 		conn.Close();
    }
	
	
    public void PhongBanDelete(int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		
		String sql = "DELETE FROM PhongBan WHERE maphg = ?";
		  
		conn.prepare(sql);
			
		conn.bind(1, maPb);
		
		conn.executeUpdatePre();
 		
 		conn.Close();
    }
}