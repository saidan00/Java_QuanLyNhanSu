package dto;

public class ChamCongDTO {
	private int MaChamCong;
	private int MaNV;
	private String Thang;
	private float SoNgayLam;
	private int SoNgayNghi;
	private int SoNgayChuNhat;

	public ChamCongDTO() {
	}

	public ChamCongDTO(ChamCongDTO chamcong) {
		this.MaChamCong = chamcong.MaChamCong;
		this.MaNV = chamcong.MaNV;
		this.Thang = chamcong.Thang;
		this.SoNgayLam = chamcong.SoNgayLam;
		this.SoNgayNghi = chamcong.SoNgayNghi;
		this.SoNgayChuNhat = chamcong.SoNgayChuNhat;
	}

	public int getMaChamCong() {
		return MaChamCong;
	}

	public void setMaChamCong(int MaChamCong) {
		this.MaChamCong = MaChamCong;
	}

	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int MaNV) {
		this.MaNV = MaNV;
	}

	public String getThang() {
		return Thang;
	}

	public void setThang(String Thang) {
		this.Thang = Thang;
	}

	public float getSoNgayLam() {
		return SoNgayLam;
	}

	public void setSoNgayLam(float SoNgayLam) {
		this.SoNgayLam = SoNgayLam;
	}

	public int getSoNgayNghi() {
		return SoNgayNghi;
	}

	public void setSoNgayNghi(int SoNgayNghi) {
		this.SoNgayNghi = SoNgayNghi;
	}

	public int getSoNgayChuNhat() {
		return SoNgayChuNhat;
	}

	public void setSoNgayChuNhat(int SoNgayChuNhat) {
		this.SoNgayChuNhat = SoNgayChuNhat;
	}
}
