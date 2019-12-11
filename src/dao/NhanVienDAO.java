package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NhanVienDTO;
import util.MySqlDataAccessHelper;

public class NhanVienDAO {
	// Lấy danh sách tất cả nhân viên
	public ArrayList<NhanVienDTO> NhanVienAll(String tk) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien WHERE isdeleted = false AND (tennv LIKE '%" + tk + "%' OR honv LIKE '%"
				+ tk + "%' OR sdt LIKE '%" + tk + "%') ORDER BY manv";

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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	public ArrayList<NhanVienDTO> NhanVienTheoPhongBan(Integer maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		if (maPb == null) {
			String sql = "SELECT * FROM nhanvien nv JOIN hopdonglaodong hd ON nv.manv = hd.manv WHERE maphong IS NULL AND denngay = ? AND isdeleted = false ORDER BY nv.manv";

			conn.prepare(sql);
			conn.bind(1, "2099-12-31");
		} else {
			String sql = "SELECT * FROM nhanvien nv JOIN hopdonglaodong hd ON nv.manv = hd.manv WHERE maphong = ? AND denngay = ? AND isdeleted = false ORDER BY nv.manv";

			conn.prepare(sql);
			conn.bind(1, maPb);
			conn.bind(2, "2099-12-31");
		}

		try {
			ResultSet rs = conn.executeQueryPre();
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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	public ArrayList<NhanVienDTO> NhanVienTheoLuong(int maLg) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String sql = "SELECT * FROM nhanvien nv WHERE maluong = ? AND isdeleted = false ORDER BY manv";

		conn.prepare(sql);
		conn.bind(1, maLg);

		try {
			ResultSet rs = conn.executeQueryPre();
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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	public NhanVienDTO NhanVienGetCMND(String cmnd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien WHERE socmnd = ?";
		conn.prepare(query);
		conn.bind(1, cmnd);

		try {
			ResultSet rs = conn.executeQueryPre();
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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		if (arr.size() == 0) {
			return null;
		}
		return arr.get(0);
	}

	public NhanVienDTO NhanVienMoiNhat() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien ORDER BY manv DESC LIMIT 1";

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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr.get(0);
	}

	// Thêm nhân viên
	public void NhanVienAdd(NhanVienDTO aNV) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO nhanvien (honv, tennv, socmnd, ngaysinh, gioitinh, sdt, diachi, maluong) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

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
		conn.bind(8, 1); // ma luong 1

		conn.executeUpdatePre();

		conn.Close();
	}

	// Sửa nhân viên
	public void NhanVienEdit(NhanVienDTO aNV) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE nhanvien SET honv = ?, tennv = ?, socmnd =?, ngaysinh = ?, gioitinh = ?, sdt = ?, diachi =?, maluong = ? WHERE manv = ?";
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
		conn.bind(8, aNV.getMaLuong());

		conn.bind(9, aNV.getMaNV());

		conn.executeUpdatePre();

		conn.Close();
	}

	// xóa nhân viên
	public void NhanVienDelete(int maNv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE nhanvien SET isdeleted = true WHERE manv = ?";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, maNv);

		conn.executeUpdatePre();

		conn.Close();
	}

	// lấy nhân viên theo id
	public NhanVienDTO NhanVienGet(int maNv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<NhanVienDTO> arr = new ArrayList<NhanVienDTO>();

		String query = "SELECT * FROM nhanvien WHERE manv = ?";
		conn.prepare(query);
		conn.bind(1, maNv);

		try {
			ResultSet rs = conn.executeQueryPre();
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
				aNhanVien.setMaLuong(rs.getInt("maluong"));

				// thêm vào array list
				arr.add(aNhanVien);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		if (arr.size() == 0) {
			return null;
		}
		return arr.get(0);
	}
}
