/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BangChamCongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

/**
 *
 * @author ADMIN
 */
public class BangChamCongDAO {
	public ArrayList<BangChamCongDTO> BangChamCongAll(String tk) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<BangChamCongDTO> arr = new ArrayList<BangChamCongDTO>();

		String query = "SELECT * FROM bangchamcong WHERE machamcong LIKE '%" + tk + "%' OR ngaychamcong LIKE '%" + tk
				+ "%'";
		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				// khởi tạo
				BangChamCongDTO aBangChamCong = new BangChamCongDTO();

				// gán giá trị
				aBangChamCong.setMaChamCong(rs.getInt("machamcong"));
				aBangChamCong.setNgayTrongThang(rs.getInt("ngaytrongthang"));

				// thêm vào array list
				arr.add(aBangChamCong);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}

		conn.Close();

		return arr;
	}

	// Thêm
	public void BangChamCongAdd(BangChamCongDTO aBCC) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO bangchamcong (machamcong, ngaytrong, trangthai) " + "VALUES(?, ?, ?)";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, aBCC.getMaChamCong());
		conn.bind(2, aBCC.getNgayTrongThang());
		conn.bind(3, aBCC.getTrangThai());

		conn.executeUpdatePre();

		conn.Close();
	}

	// Sửa
	public void BangChamCongEdit(BangChamCongDTO aBCC) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE bangchamcong SET ngaytrongthang = ?, trangthai = ? WHERE machamcong = ?";
		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, aBCC.getNgayTrongThang());
		conn.bind(2, aBCC.getTrangThai());

		conn.bind(3, aBCC.getMaChamCong());

		conn.executeUpdatePre();

		conn.Close();
	}

	// xóa
	public void BangChamCongDelete(int machamcong) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM bangchamcong WHERE machamcong = ?";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, machamcong);

		conn.executeUpdatePre();

		conn.Close();
	}
}
