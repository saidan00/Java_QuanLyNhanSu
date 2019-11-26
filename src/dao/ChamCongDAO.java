package dao;

import dto.ChamCongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class ChamCongDAO {
	public ArrayList<ChamCongDTO> ChamCongAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<ChamCongDTO> arr = new ArrayList<ChamCongDTO>();

		String sql = "SELECT * FROM chamcong";
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				ChamCongDTO chamcong = new ChamCongDTO();

				chamcong.setMaChamCong(rs.getInt("machamcong"));
				chamcong.setMaNV(rs.getInt("manv"));
				chamcong.setThang(rs.getInt("thang"));
				chamcong.setSoNgayLam(rs.getFloat("songaylam"));
				chamcong.setSoNgayNghi(rs.getInt("songaynghi"));
				chamcong.setSoNgayChuNhat(rs.getInt("songaychunhat"));
				chamcong.setSoNgayLe(rs.getInt("songayle"));

				arr.add(chamcong);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}

	public void ChamCongAdd(ChamCongDTO chamcong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO chamcong (manv, thang, songaylam, songaynghi, songaychunhat, songayle) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		// prepare
		conn.prepare(sql);

		conn.bind(1, chamcong.getMaNV());
		conn.bind(2, chamcong.getThang());
		conn.bind(3, chamcong.getSoNgayLam());
		conn.bind(4, chamcong.getSoNgayNghi());
		conn.bind(5, chamcong.getSoNgayChuNhat());
		conn.bind(6, chamcong.getSoNgayLe());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void ChamCongEdit(ChamCongDTO chamcong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE chamcong SET manv = ?, thang = ?, songaylam = ?, songaynghi = ?, songaychunhat = ?, songayle = ? WHERE machamcong = ?";

		// prepare
		conn.prepare(sql);

		conn.bind(1, chamcong.getMaNV());
		conn.bind(2, chamcong.getThang());
		conn.bind(3, chamcong.getSoNgayLam());
		conn.bind(4, chamcong.getSoNgayNghi());
		conn.bind(5, chamcong.getSoNgayChuNhat());
		conn.bind(6, chamcong.getSoNgayLe());

		conn.bind(7, chamcong.getMaChamCong());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void ChamCongDelete(int machamcong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "DELETE FROM chamcong WHERE machamcong = ?";

		conn.prepare(sql);

		conn.bind(1, machamcong);

		conn.executeUpdatePre();

		conn.Close();
	}

}
