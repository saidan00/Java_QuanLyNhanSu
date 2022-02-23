package dao;

import dto.HopDongLaoDongDTO;
import gui.MyProps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.MySqlDataAccessHelper;

public class HopDongLaoDongDAO {
	private MyProps myProps = new MyProps();

	public ArrayList<HopDongLaoDongDTO> HopDongLaoDongAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<HopDongLaoDongDTO> arr = new ArrayList<HopDongLaoDongDTO>();

		String query = "SELECT * FROM hopdonglaodong";

		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				// khởi tạo
				HopDongLaoDongDTO hd = new HopDongLaoDongDTO();

				// gán giá trị
				hd.setMaHD(rs.getInt("mahd"));
				hd.setMaNV(rs.getInt("manv"));
				hd.setTuNgay(rs.getString("tungay"));
				hd.setDenNgay(rs.getString("denngay"));
				hd.setDiaDiemLamViec(rs.getString("diadiemlamviec"));
				hd.setThoiGianLamViec(rs.getInt("thoigianlamviec"));
				hd.setHeSoLuong(rs.getDouble("hesoluong"));
				hd.setMaCV(rs.getInt("macv"));
				hd.setMaPhong(rs.getInt("maphong"));

				// thêm vào array list
				arr.add(hd);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	/// Thêm hợp dồng lao động
	public void HopDongLaoDongAdd(HopDongLaoDongDTO aHDLD) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO hopdonglaodong (manv, tungay, denngay, diadiemlamviec, thoigianlamviec, hesoluong, macv, maphong) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, aHDLD.getMaNV());
		conn.bind(2, aHDLD.getTuNGay());
		conn.bind(3, aHDLD.getDenNgay());
		conn.bind(4, aHDLD.getDiaDiemLamViec());
		conn.bind(5, aHDLD.getThoiGianLamViec());
		conn.bind(6, aHDLD.getHeSoLuong());
		conn.bind(7, aHDLD.getMaCV());
		conn.bind(8, aHDLD.getMaPhong());

		conn.executeUpdatePre();

		conn.Close();
	}

	// sửa hợp đồng lao dộng
	public void HopDongLaoDongEdit(HopDongLaoDongDTO aHDLD) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE hopdonglaodong SET manv = ?, tungay = ?, denngay = ?, diadiemlamviec = ?, thoigianlamviec = ?, hesoluong = ?, macv = ?, maphong = ? ";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, aHDLD.getMaNV());
		conn.bind(2, aHDLD.getTuNGay());
		conn.bind(3, aHDLD.getDenNgay());
		conn.bind(4, aHDLD.getDiaDiemLamViec());
		conn.bind(5, aHDLD.getThoiGianLamViec());
		conn.bind(6, aHDLD.getHeSoLuong());
		conn.bind(7, aHDLD.getMaCV());
		conn.bind(8, aHDLD.getMaPhong());

		conn.bind(9, aHDLD.getMaHD());

		conn.executeUpdatePre();

		conn.Close();
	}

	// xóa hợp đồng lao động
	public void HopDongLaoDongDelete(int maHDLD) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM hopdonglaodong WHERE maHDLD = ?";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, maHDLD);

		conn.executeUpdatePre();

		conn.Close();
	}

	public void ThemNhanVienPhongBan(HopDongLaoDongDTO hd, int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		// thêm hợp đồng mới
		HopDongLaoDongDTO hdMoi = new HopDongLaoDongDTO(hd);

		// gán kết thúc hợp đồng cũ
		KetThucHopDong(hd);

		hdMoi.setMaPhong(maPb);
		this.HopDongLaoDongAdd(hdMoi);

		conn.Close();
	}

	public void CapNhatTruongPhong(int maPb, int maNv, HopDongLaoDongDTO hd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "";

		if (hd.getMaCV() != 2) {
			// gán kết thúc hợp đồng cũ
			KetThucHopDong(hd);

			// lấy hd của tp hiện tại
			HopDongLaoDongDTO hdTpHienTai = TruongPhongHienTai(maPb);

			if (hdTpHienTai != null) {
				// gán kết thúc hợp đồng cũ của tp hiện tại
				KetThucHopDong(hdTpHienTai);

				// thêm hd mới gán tp cũ thành nv
				HopDongLaoDongDTO hdTpCu = new HopDongLaoDongDTO(hdTpHienTai);
				hdTpCu.setMaCV(1);
				this.HopDongLaoDongAdd(hdTpCu);
			}

			// thêm hợp đồng mới
			HopDongLaoDongDTO hdMoi = new HopDongLaoDongDTO(hd);
			hdMoi.setMaCV(2);
			this.HopDongLaoDongAdd(hdMoi);

			sql = "UPDATE phongban SET matruongphong = ? WHERE maphong = ?";
			conn.prepare(sql);
			conn.bind(1, maNv);
			conn.bind(2, maPb);
			conn.executeUpdatePre();
		}

		conn.Close();
	}

	public HopDongLaoDongDTO HopDongMoiNhat(int maNv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<HopDongLaoDongDTO> arr = new ArrayList<HopDongLaoDongDTO>();

		// chọn hd mới nhất
		String sql = "SELECT * FROM hopdonglaodong WHERE manv = ? AND denngay = ?";

		conn.prepare(sql);

		conn.bind(1, maNv);
		conn.bind(2, "2099-12-31");

		try {
			ResultSet rs = conn.executeQueryPre();
			while (rs.next()) {
				// khởi tạo
				HopDongLaoDongDTO hd = new HopDongLaoDongDTO();

				// gán giá trị
				hd.setMaHD(rs.getInt("mahd"));
				hd.setMaNV(rs.getInt("manv"));
				hd.setTuNgay(rs.getString("tungay"));
				hd.setDenNgay(rs.getString("denngay"));
				hd.setDiaDiemLamViec(rs.getString("diadiemlamviec"));
				hd.setThoiGianLamViec(rs.getInt("thoigianlamviec"));
				hd.setHeSoLuong(rs.getDouble("hesoluong"));
				hd.setMaCV(rs.getInt("macv"));
				hd.setMaPhong(rs.getInt("maphong"));

				// thêm vào array list
				arr.add(hd);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr.get(0);
	}

	public HopDongLaoDongDTO TruongPhongHienTai(int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<HopDongLaoDongDTO> arr = new ArrayList<HopDongLaoDongDTO>();

		String sql = "SELECT * FROM hopdonglaodong WHERE maphong = ? AND macv = ? AND denngay = ?";

		conn.prepare(sql);

		conn.bind(1, maPb);
		conn.bind(2, 2);
		conn.bind(3, "2099-12-31");

		try {
			ResultSet rs = conn.executeQueryPre();
			while (rs.next()) {
				// khởi tạo
				HopDongLaoDongDTO hd = new HopDongLaoDongDTO();

				// gán giá trị
				hd.setMaHD(rs.getInt("mahd"));
				hd.setMaNV(rs.getInt("manv"));
				hd.setTuNgay(rs.getString("tungay"));
				hd.setDenNgay(rs.getString("denngay"));
				hd.setDiaDiemLamViec(rs.getString("diadiemlamviec"));
				hd.setThoiGianLamViec(rs.getInt("thoigianlamviec"));
				hd.setHeSoLuong(rs.getDouble("hesoluong"));
				hd.setMaCV(rs.getInt("macv"));
				hd.setMaPhong(rs.getInt("maphong"));

				// thêm vào array list
				arr.add(hd);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		if (arr.size() != 0) {
			return arr.get(0);
		} else {
			return null;
		}
	}

	public String ChucVuCuaNhanVien(int maNv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String cv = "Nhân Viên";

		String sql = "SELECT * FROM hopdonglaodong hd JOIN chucvu cv ON hd.macv = cv.macv WHERE hd.manv = ? ORDER BY denngay DESC LIMIT 1";

		conn.prepare(sql);

		conn.bind(1, maNv);

		try {
			ResultSet rs = conn.executeQueryPre();
			while (rs.next()) {
				// khởi tạo
				cv = rs.getString("tencv");
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return cv;
	}

	public void XoaNhanVienKhoiPhongBan(int maNv, int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		HopDongLaoDongDTO hdHienTai = HopDongMoiNhat(maNv);

		HopDongLaoDongDTO hdMoi = new HopDongLaoDongDTO(hdHienTai);

		KetThucHopDong(hdHienTai);

		hdMoi.setMaPhong(null);

		HopDongLaoDongAdd(hdMoi);

		conn.Close();
	}

	public void KetThucHopDong(HopDongLaoDongDTO hd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE hopdonglaodong SET denngay = ? WHERE mahd = ?";
		conn.prepare(sql);
		conn.bind(1, myProps.currentDate());
		conn.bind(2, hd.getMaHD());
		conn.executeUpdatePre();

		conn.Close();
	}
	
	public ArrayList<HopDongLaoDongDTO> hoDongLaoDongGet(int maNv, String thang1, String thang2) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<HopDongLaoDongDTO> arr = new ArrayList<HopDongLaoDongDTO>();

		String query = "SELECT * FROM hopdonglaodong WHERE manv = ? AND (tungay BETWEEN ? AND ?) AND (denngay BETWEEN ? AND ?) ORDER BY denngay";
		
		conn.prepare(query);
		
		conn.bind(1, maNv);
		conn.bind(2, thang1);
		conn.bind(3, thang2);
		conn.bind(4, thang1);
		conn.bind(5, thang2);

		try {
			ResultSet rs = conn.executeQueryPre();
			while (rs.next()) {
				// khởi tạo
				HopDongLaoDongDTO hd = new HopDongLaoDongDTO();

				// gán giá trị
				hd.setMaHD(rs.getInt("mahd"));
				hd.setMaNV(rs.getInt("manv"));
				hd.setTuNgay(rs.getString("tungay"));
				hd.setDenNgay(rs.getString("denngay"));
				hd.setDiaDiemLamViec(rs.getString("diadiemlamviec"));
				hd.setThoiGianLamViec(rs.getInt("thoigianlamviec"));
				hd.setHeSoLuong(rs.getDouble("hesoluong"));
				hd.setMaCV(rs.getInt("macv"));
				hd.setMaPhong(rs.getInt("maphong"));

				// thêm vào array list
				arr.add(hd);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}
}
