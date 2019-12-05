package dao;

import dto.KhenThuongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class KhenThuongDAO {
	public ArrayList<KhenThuongDTO> KhenThuongAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<KhenThuongDTO> arr = new ArrayList<KhenThuongDTO>();
		String sql = "select * from khenthuong";
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				KhenThuongDTO khen = new KhenThuongDTO();
				khen.setMaKhenThuong(rs.getInt("makhenthuong"));
				khen.setNgayKhenThuong(rs.getString("ngaykhenthuong"));
				khen.setHinhThuc(rs.getString("hinhthuc"));
				khen.setLyDo(rs.getString("lydo"));
				khen.setTienThuong(rs.getInt("tienthuong"));
				khen.setMaNV(rs.getInt("manv"));

				arr.add(khen);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}

	public void KhenThuongAdd(KhenThuongDTO kt) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "INSERT INTO khenthuong(ngaykhenthuong, hinhthuc, lydo, tienthuong, manv)" + "VALUE(?, ?, ?, ?, ?)";
		conn.prepare(sql);

		conn.bind(1, kt.getNgayKhenThuong());
		conn.bind(2, kt.getHinhThuc());
		conn.bind(3, kt.getLyDo());
		conn.bind(4, kt.getTienThuong());
		conn.bind(5, kt.getMaNV());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void KhenThuongEdit(KhenThuongDTO kt) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE khenthuong SET ngaykhenthuong = ?, hinhthuc = ?, lydo = ?, tienthuong = ? WHERE makhenthuong = ?";

		conn.prepare(sql);

		conn.bind(1, kt.getNgayKhenThuong());
		conn.bind(2, kt.getHinhThuc());
		conn.bind(3, kt.getLyDo());
		conn.bind(4, kt.getTienThuong());
		conn.bind(5, kt.getMaKhenThuong());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void KhenThuongDelete(int makhenthuong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "DELETE FROM khenthuong WHERE makhenthuong = ?";

		conn.prepare(sql);

		conn.bind(1, makhenthuong);

		conn.executeUpdatePre();

		conn.Close();
	}
}
