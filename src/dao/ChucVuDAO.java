package dao;

import dto.ChucVuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class ChucVuDAO {
	public ArrayList<ChucVuDTO> ChucVuAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<ChucVuDTO> arr = new ArrayList<ChucVuDTO>();
		String sql = "select * from chucvu";
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				ChucVuDTO chucvu = new ChucVuDTO();
				chucvu.setMaCV(rs.getInt("macv"));
				chucvu.setTenCV(rs.getString("tencv"));
				chucvu.setPhuCap(rs.getInt("phucap"));
				chucvu.setGhiChu(rs.getString("ghichu"));

				arr.add(chucvu);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}
	
	public ChucVuDTO ChucVuGet(int maCv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<ChucVuDTO> arr = new ArrayList<ChucVuDTO>();
		String sql = "select * from chucvu where macv = " + maCv;
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				ChucVuDTO chucvu = new ChucVuDTO();
				chucvu.setMaCV(rs.getInt("macv"));
				chucvu.setTenCV(rs.getString("tencv"));
				chucvu.setPhuCap(rs.getDouble("phucap"));
				chucvu.setGhiChu(rs.getString("ghichu"));

				arr.add(chucvu);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr.get(0);
	}

	public void ChucVuAdd(ChucVuDTO cv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "INSERT INTO chucvu(tencv, phucap, ghichu)" + "VALUE(?, ?, ?)";

		conn.prepare(sql);

		conn.bind(1, cv.getTenCV());
		conn.bind(2, cv.getPhuCap());
		conn.bind(3, cv.getGhiChu());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void ChucVuEdit(ChucVuDTO cv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE chucvu SET tencv = ?, phucap = ?, ghichu = ? WHERE macv = ?";

		conn.prepare(sql);

		conn.bind(1, cv.getTenCV());
		conn.bind(2, cv.getPhuCap());
		conn.bind(3, cv.getGhiChu());
		conn.bind(4, cv.getMaCV());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void ChucVuDelete(int macv) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "DELETE FROM chucvu WHERE macv = ?";

		conn.prepare(sql);

		conn.bind(1, macv);

		conn.executeUpdatePre();

		conn.Close();
	}

}
