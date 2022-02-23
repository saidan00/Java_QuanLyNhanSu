/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class PhongBanDTO {
	private int MaPhong;
	private String TenPhong;
	private Integer MaTruongPhong;

	public PhongBanDTO() {

	}

	public PhongBanDTO(int maphg, String tenphg, int trgphg, String sdtphongban) {
		this.MaPhong = maphg;
		this.TenPhong = tenphg;
		this.MaTruongPhong = trgphg;
	}

	public String getTenPhong() {
		return TenPhong;
	}

	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}

	public Integer getMaTruongPhong() {
		return MaTruongPhong;
	}

	public void setMaTruongPhong(Integer truongPhong) {
		MaTruongPhong = truongPhong;
	}

	public int getMaPhong() {
		return MaPhong;
	}

	public void setMaPhong(int maPhong) {
		MaPhong = maPhong;
	}
}