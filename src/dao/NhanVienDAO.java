package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NhanVienDTO;
import util.MySqlDataAccessHelper;

public class NhanVienDAO {
	// Lấy danh sách tất cả nhân viên
	public static ArrayList<NhanVienDTO> NhanVienAll() {

		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String sql = "SELECT * FROM nhanvien";

		try {
			ResultSet rs = conn.executeQuery(sql);
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

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		
		return arr;
	}
}
