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
		
		return arr;
	}
	
	// Thêm nhân viên
	public static void NhanVienAdd(NhanVienDTO aNV) {
		String sql = "INSERT INTO nhanvien "
				+ "SET('manv', 'honv', 'tennv', 'socmnd', 'ngaysinh', 'gioitinh', 'sdt') "
				+ "VALUES("
				+ aNV.getMaNV() + ","
				+ aNV.getHoNV() + ","
				+ aNV.getTenNV() + ","
				+ aNV.getSoCMND() + ","
				+ aNV.getNgaySinh() + ","
				+ aNV.getGioiTinh() + ","
				+ aNV.getSDT() + ","
				+ ")";
	}
}
