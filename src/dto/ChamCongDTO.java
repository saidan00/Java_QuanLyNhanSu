package dto;

public class ChamCongDTO {
	private int MaChamCong;
	private int MaNV;
	private int Thang;
	private float SoNgayLam;
	private int SoNgayNghi;
	private int SoNgayChuNhat;
	private int SoNgayLe;

	public ChamCongDTO() {
	}

	public ChamCongDTO(ChamCongDTO chamcong) {
		this.MaChamCong = chamcong.MaChamCong;
		this.MaNV = chamcong.MaNV;
		this.Thang = chamcong.Thang;
		this.SoNgayLam = chamcong.SoNgayLam;
		this.SoNgayNghi = chamcong.SoNgayNghi;
		this.SoNgayChuNhat = chamcong.SoNgayChuNhat;
		this.SoNgayLe = chamcong.SoNgayLe;
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

	public int getThang() {
		return Thang;
	}

	public void setThang(int Thang) {
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

	public int getSoNgayLe() {
		return SoNgayLe;
	}

	public void setSoNgayLe(int SoNgayLe) {
		this.SoNgayLe = SoNgayLe;
	}

}
