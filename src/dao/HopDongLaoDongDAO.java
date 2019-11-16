package dao;

import dto.HopDongLaoDongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.MySqlDataAccessHelper;

public class HopDongLaoDongDAO {
    public ArrayList<HopDongLaoDongDTO> HopDongLaoDongAll(){
        MySqlDataAccessHelper conn = new MySqlDataAccessHelper();
        
        ArrayList<HopDongLaoDongDTO> arr = new ArrayList<HopDongLaoDongDTO>();
        
        String  query = "SELECT * FROM hopdonglaodong";
        try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				// khởi tạo
				HopDongLaoDongDTO aHopDongLaoDong = new HopDongLaoDongDTO();

				// gán giá trị
				aHopDongLaoDong.setMaHD(rs.getInt("mahd"));
                aHopDongLaoDong.setMaNV(rs.getInt("manv"));
                aHopDongLaoDong.setTuNgay(rs.getString("tungay"));
                aHopDongLaoDong.setDenNgay(rs.getString("denngay"));
                aHopDongLaoDong.setDiaDiemLamViec(rs.getString("diadiemlamviec"));
                aHopDongLaoDong.setThoiGianLamViec(rs.getInt("thoigianlamviec"));
                aHopDongLaoDong.setHeSoLuong(rs.getDouble("hesoluong"));
                aHopDongLaoDong.setMaCV(rs.getInt("macv"));
                aHopDongLaoDong.setMaPhong(rs.getInt("maphong"));
				
				// thêm vào array list
				arr.add(aHopDongLaoDong);
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
		
		String sql = "INSERT hopdonglaodong SET manv = ?, tungay = ?, denngay = ?, diadiemlamviec =?, thoigianlamviec = ?, hesoluong = ?, macv = ?, maphong =? "
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
}
