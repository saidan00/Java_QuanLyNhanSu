package dto;

/**
 *
 * @author ASUS
 */
public class ChucVuDTO {
	private int MaCV;
	private String TenCV;
	private double PhuCap;
	private String GhiChu;

	// get
	public int getMaCV() {
		return MaCV;
	}

	public String getTenCV() {
		return TenCV;
	}

	public double getPhuCap() {
		return PhuCap;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	// set
	public void setMaCV(int macv) {
		MaCV = macv;
	}

	public void setTenCV(String tencv) {
		TenCV = tencv;
	}

	public void setPhuCap(double phucap) {
		PhuCap = phucap;
	}

	public void setGhiChu(String ghichu) {
		GhiChu = ghichu;
	}
}
