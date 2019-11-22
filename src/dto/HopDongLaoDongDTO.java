package dto;

public class HopDongLaoDongDTO {
	private int MaHD;
	private int MaNV;
	private String TuNgay;
	private String DenNgay;
	private String DiaDiemLamViec;
	private int ThoiGianLamViec;
	private double HeSoLuong;
	private int MaCV;
	private Integer MaPhong;

	public HopDongLaoDongDTO() {
	}

	public HopDongLaoDongDTO(HopDongLaoDongDTO hd) {
		this.MaHD = hd.MaHD;
		this.MaNV = hd.MaNV;
		this.TuNgay = hd.TuNgay;
		this.DenNgay = hd.DenNgay;
		this.DiaDiemLamViec = hd.DiaDiemLamViec;
		this.ThoiGianLamViec = hd.ThoiGianLamViec;
		this.HeSoLuong = hd.HeSoLuong;
		this.MaCV = hd.MaCV;
		this.MaPhong = hd.MaPhong;
	}

	// get
	public int getMaHD() {
		return MaHD;
	}

	public int getMaNV() {
		return MaNV;
	}

	public String getTuNGay() {
		return TuNgay;
	}

	public String getDenNgay() {
		return DenNgay;
	}

	public String getDiaDiemLamViec() {
		return DiaDiemLamViec;
	}

	public int getThoiGianLamViec() {
		return ThoiGianLamViec;
	}

	public double getHeSoLuong() {
		return HeSoLuong;
	}

	public int getMaCV() {
		return MaCV;
	}

	public Integer getMaPhong() {
		return MaPhong;
	}

	// set
	public void setMaHD(int mahd) {
		MaHD = mahd;
	}

	public void setMaNV(int manv) {
		MaNV = manv;
	}

	public void setTuNgay(String tungay) {
		TuNgay = tungay;
	}

	public void setDenNgay(String denngay) {
		DenNgay = denngay;
	}

	public void setDiaDiemLamViec(String diadiemlamviec) {
		DiaDiemLamViec = diadiemlamviec;
	}

	public void setThoiGianLamViec(int thoigianlamviec) {
		ThoiGianLamViec = thoigianlamviec;
	}

	public void setHeSoLuong(double hesoluong) {
		HeSoLuong = hesoluong;
	}

	public void setMaCV(int macv) {
		MaCV = macv;
	}

	public void setMaPhong(Integer maphong) {
		MaPhong = maphong;
	}
}
