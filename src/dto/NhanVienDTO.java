package dto;

public class NhanVienDTO {
	private int MaNV;
	private String HoNV;
	private String TenNV;
	private String SoCMND;
	private String NgaySinh;
	private String GioiTinh;
	private String SDT;
	private String DiaChi;
	private int MaLuong;

	// GETTERs
	public int getMaNV() {
		return MaNV;
	}

	public String getHoNV() {
		return HoNV;
	}

	public String getTenNV() {
		return TenNV;
	}

	public String getSoCMND() {
		return SoCMND;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	// SETTERs
	public void setMaNV(int maNV) {
		MaNV = maNV;
	}

	public void setHoNV(String hoNV) {
		HoNV = hoNV;
	}

	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}

	public void setSoCMND(String soCMND) {
		SoCMND = soCMND;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public int getMaLuong() {
		return MaLuong;
	}

	public void setMaLuong(int maLuong) {
		MaLuong = maLuong;
	}
}
