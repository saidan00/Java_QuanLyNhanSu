package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NhanVienDTO;
import util.MySqlDataAccessHelper;

public class NhanVienDAO {
	// Lấy danh sách tất cả nhân viên
	public ArrayList<NhanVienDTO> NhanVienAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien";

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
		
		String sql = "INSERT INTO nhanvien SET('manv', 'honv', 'tennv', 'socmnd', 'ngaysinh', 'gioitinh', 'sdt', 'diachi') "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// prepare statement
		conn.prepare(sql);
		
		// bind values
		conn.bind(1, aNV.getMaNV());
		conn.bind(2, aNV.getHoNV());
		conn.bind(3, aNV.getTenNV());
		conn.bind(4, aNV.getSoCMND());
		conn.bind(5, aNV.getNgaySinh());
		conn.bind(6, aNV.getGioiTinh());
		conn.bind(7, aNV.getSDT());
		conn.bind(8, aNV.getDiaChi());
		
		conn.executeQueryPre();
		
		conn.Close();
	}
}
