package dao;

import dto.LuongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class LuongDAO {
	public static Object arr;

	// lay ds luong
	public ArrayList<LuongDTO> LuongAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<LuongDTO> arr = new ArrayList<LuongDTO>();
		String sql = "Select * from luong";
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				// khoi tao
				LuongDTO luong = new LuongDTO();
				// gan gt
				luong.setMaLuong(rs.getInt("MaLuong"));
				luong.setHeSoLuong(rs.getFloat("HeSoLuong"));
				luong.setLuongCB(rs.getInt("LuongCB"));
				luong.setHeSoPhuCap(rs.getFloat("HeSoPhuCap"));
				// them vao arraylist
				arr.add(luong);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}

	// them
	public void LuongAdd(LuongDTO luongadd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "INSERT INTO luong (hesoluong, luongcb, hesophucap)" + "VALUES(?, ?, ?)";
		// prepare statement
		conn.prepare(sql);
		// bind values
		conn.bind(1, luongadd.getHeSoLuong());
		conn.bind(2, luongadd.getLuongCB());
		conn.bind(3, luongadd.getHeSoPhuCap());

		conn.executeUpdatePre();

		conn.Close();
	}

	// sua
	public void LuongEdit(LuongDTO luongadd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE luong SET hesoluong = ?, luongcb = ?, hesophucap = ? WHERE maluong = ?";
		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, luongadd.getHeSoLuong());
		conn.bind(2, luongadd.getLuongCB());
		conn.bind(3, luongadd.getHeSoPhuCap());
		conn.bind(4, luongadd.getMaLuong());

		conn.executeUpdatePre();

		conn.Close();
	}

	// xoa
	public void LuongDelete(int maluong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM luong WHERE maluong = ?";

		conn.prepare(sql);

		conn.bind(1, maluong);

		conn.executeUpdatePre();

		conn.Close();
	}

}
