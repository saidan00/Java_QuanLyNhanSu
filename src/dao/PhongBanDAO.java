package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PhongBanDTO;
import util.MySqlDataAccessHelper;

public class PhongBanDAO {

	public ArrayList<PhongBanDTO> PhongBanAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<PhongBanDTO> arr = new ArrayList<PhongBanDTO>();

		String query = "SELECT * FROM phongban ";
		query += "pb LEFT JOIN nhanvien nv ON pb.matruongphong = nv.manv order by pb.maphong";

		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {

				PhongBanDTO PhongBan = new PhongBanDTO();

				PhongBan.setMaPhong(rs.getInt("maphong"));
				PhongBan.setTenPhong(rs.getString("tenphong"));
				PhongBan.setMaTruongPhong(rs.getInt("matruongphong"));
				arr.add(PhongBan);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	public void PhongBanAdd(PhongBanDTO PB) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO phongban (tenphong, matruongphong) " + "VALUES(?, ?)";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, PB.getTenPhong());
		conn.bind(2, PB.getMaTruongPhong());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void PhongBanEdit(PhongBanDTO PB) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE phongban SET tenphong = ?, matruongphong = ?  WHERE maphong = ?";

		conn.prepare(sql);

		conn.bind(1, PB.getTenPhong());
		conn.bind(2, PB.getMaTruongPhong());

		conn.bind(3, PB.getMaPhong());

		conn.executeUpdatePre();

		conn.Close();
	}

	public void PhongBanDelete(int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM PhongBan WHERE maphong = ?";

		conn.prepare(sql);

		conn.bind(1, maPb);

		conn.executeUpdatePre();

		conn.Close();
	}
	
	public void DoiTenPhong(int maPb, String tenPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE phongban SET tenphong = ? WHERE maphong = ?";

		conn.prepare(sql);

		conn.bind(1, tenPb);
		conn.bind(2, maPb);

		conn.executeUpdatePre();

		conn.Close();
	}

	public void CapNhatTruongPhong(int maPb, int maTruongPhong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "UPDATE phongban SET matruongphong = ? WHERE maphong = ?";

		conn.prepare(sql);

		conn.bind(1, maTruongPhong);
		conn.bind(2, maPb);

		conn.executeUpdatePre();

		conn.Close();
	}

	public PhongBanDTO PhongBanGet(int maPb) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
		String sql = "SELECT * FROM phongban WHERE maphong = ?";

		conn.prepare(sql);

		conn.bind(1, maPb);

		ArrayList<PhongBanDTO> arr = new ArrayList<PhongBanDTO>();

		try {
			ResultSet rs = conn.executeQueryPre();
			while (rs.next()) {
				PhongBanDTO PhongBan = new PhongBanDTO();

				PhongBan.setMaPhong(rs.getInt("maphong"));
				PhongBan.setTenPhong(rs.getString("tenphong"));
				PhongBan.setMaTruongPhong(rs.getInt("matruongphong"));
				arr.add(PhongBan);
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