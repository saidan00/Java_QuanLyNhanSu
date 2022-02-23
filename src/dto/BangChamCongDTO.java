/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class BangChamCongDTO {
	private int MaChamCong;
	private int NgayTrongThang;
	private String TrangThai;

	public BangChamCongDTO() {
	}

	public BangChamCongDTO(BangChamCongDTO bcc) {
		this.MaChamCong = bcc.MaChamCong;
		this.NgayTrongThang = bcc.NgayTrongThang;
		this.TrangThai = bcc.TrangThai;
	}

	// get
	public int getMaChamCong() {
		return MaChamCong;
	}

	public int getNgayTrongThang() {
		return NgayTrongThang;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	// set
	public void setMaChamCong(int machamcong) {
		MaChamCong = machamcong;
	}

	public void setNgayTrongThang(int ngaytrongthang) {
		NgayTrongThang = ngaytrongthang;
	}

	public void setTrangThai(String trangthai) {
		TrangThai = trangthai;
	}
}
