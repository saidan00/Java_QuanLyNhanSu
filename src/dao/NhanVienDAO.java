package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NhanVienDTO;
import util.MySqlDataAccessHelper;

public class NhanVienDAO {
	// Lấy danh sách tất cả nhân viên
	public ArrayList<NhanVienDTO> NhanVienAll(Integer maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien ";
		
		if (maPb != null) {
			query += "nv JOIN hopdonglaodong hd ON nv.manv = hd.manv WHERE hd.maphong = " + maPb;
		}

		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				// khởi tạo
				NhanVienDTO aNhanVien = new NhanVienDTO();

				// gán giá trị
				aNhanVien.setMaNV(rs.getInt("manv"));
				aNhanVien.setHoNV(rs.getString("honv"));
				aNhanVien.setTenNV(rs.getString("tennv"));
				aNhanVien.setSoCMND(rs.getString("socmnd"));
				aNhanVien.setNgaySinh(rs.getString("ngaysinh"));
				aNhanVien.setGioiTinh(rs.getString("gioitinh"));
				aNhanVien.setSDT(rs.getString("sdt"));
				aNhanVien.setDiaChi(rs.getString("diachi"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		
		conn.Close();
	
		return arr;
	}
	
	// Thêm nhân viên
	public void NhanVienAdd(NhanVienDTO aNV) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		
		String sql = "INSERT INTO nhanvien (honv, tennv, socmnd, ngaysinh, gioitinh, sdt, diachi) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		// prepare statement
		conn.prepare(sql);
		
		// bind values
		conn.bind(1, aNV.getHoNV());
		conn.bind(2, aNV.getTenNV());
		conn.bind(3, aNV.getSoCMND());
		conn.bind(4, aNV.getNgaySinh());
		conn.bind(5, aNV.getGioiTinh());
		conn.bind(6, aNV.getSDT());
		conn.bind(7, aNV.getDiaChi());
		
		conn.executeUpdatePre();
		
		conn.Close();
	}
	
	// Sửa nhân viên
	public void NhanVienEdit(NhanVienDTO aNV) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
        
        String  sql = "UPDATE nhanvien SET honv = ?, tennv = ?, socmnd =?, ngaysinh = ?, gioitinh = ?, sdt = ?, diachi =? WHERE manv = ?";
        // prepare statement
 		conn.prepare(sql);
     		
 		// bind values
 		conn.bind(1, aNV.getHoNV());
 		conn.bind(2, aNV.getTenNV());
 		conn.bind(3, aNV.getSoCMND());
 		conn.bind(4, aNV.getNgaySinh());
 		conn.bind(5, aNV.getGioiTinh());
 		conn.bind(6, aNV.getSDT());
 		conn.bind(7, aNV.getDiaChi());
 		
 		conn.bind(8, aNV.getMaNV());
 		
 		conn.executeUpdatePre();
 		
 		conn.Close();
    }
	
	// xóa nhân viên
    public void NhanVienDelete(int maNv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		
		String sql = "DELETE FROM nhanvien WHERE manv = ?";
		   
		// prepare statement
		conn.prepare(sql);
			
		// bind values
		conn.bind(1, maNv);
		
		conn.executeUpdatePre();
 		
 		conn.Close();
    }
}
