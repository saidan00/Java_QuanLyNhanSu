package dao;

import dto.ChiTietLuongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class ChiTietLuongDAO {
	public static Object arr;

	// lay ds chitietluong
	public ArrayList<ChiTietLuongDTO> ChiTietLuongAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		ArrayList<ChiTietLuongDTO> arr = new ArrayList<ChiTietLuongDTO>();
		String sql = "Select * from chitietluong";
		try {
			ResultSet rs = conn.executeQuery(sql);
			while (rs.next()) {
				// khoi tao
				ChiTietLuongDTO ct = new ChiTietLuongDTO(null);
				// gan gt
				ct.setMaLuong(rs.getInt("MaLuong"));
				ct.setLuongCB(rs.getInt("LuongCB"));
				ct.setTongSoNgay(rs.getInt("TongSoNgay"));
				ct.setSoNgayNghi(rs.getInt("SoNgayNghi"));
				// them vao arraylist
				arr.add(ct);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}

	// them
	public void ChiTietLuongAdd(ChiTietLuongDTO chitietluongadd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "INSERT INTO luong (tongsongay, luongcb, songaynghi)" + "VALUES(?, ?, ?)";
		// prepare statement
		conn.prepare(sql);
		// bind values
		conn.bind(1, chitietluongadd.getTongSoNgay());
		conn.bind(2, chitietluongadd.getLuongCB());
		conn.bind(3, chitietluongadd.getSoNgayNghi());

		conn.executeUpdatePre();

		conn.Close();
	}

	// sua
	public void ChiTietLuongEdit(ChiTietLuongDTO chitietluongadd) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE luong SET tongsongay = ?, luongcb = ?, songaynghi = ? WHERE maluong = ?";
		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, chitietluongadd.getTongSoNgay());
		conn.bind(2, chitietluongadd.getLuongCB());
		conn.bind(3, chitietluongadd.getSoNgayNghi());
		conn.bind(4, chitietluongadd.getMaLuong());

		conn.executeUpdatePre();

		conn.Close();
	}

	// xoa
	public void ChiTietLuongDelete(int maluong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM luong WHERE maluong = ?";

		conn.prepare(sql);

		conn.bind(1, maluong);

		conn.executeUpdatePre();

		conn.Close();
	}
}