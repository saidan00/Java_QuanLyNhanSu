-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2019 at 12:36 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlynhansu`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangchamcong`
--

CREATE TABLE `bangchamcong` (
  `machamcong` int(11) NOT NULL,
  `ngaytrongthang` int(11) NOT NULL,
  `trangthai` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bangchamcong`
--

INSERT INTO `bangchamcong` (`machamcong`, `ngaytrongthang`, `trangthai`) VALUES
(5, 1, 'null'),
(5, 2, 'x'),
(5, 3, 'x'),
(5, 4, 'x'),
(5, 5, 'x'),
(5, 6, '1/2'),
(5, 7, 'n'),
(5, 8, 'null'),
(5, 9, 'x'),
(5, 10, '1/2'),
(5, 11, 'x'),
(5, 12, 'x'),
(5, 13, 'x'),
(5, 14, 'x'),
(5, 15, 'null'),
(5, 16, 'x'),
(5, 17, 'x'),
(5, 18, 'x'),
(5, 19, 'x'),
(5, 20, 'x'),
(5, 21, 'x'),
(5, 22, 'null'),
(5, 23, 'x'),
(5, 24, 'x'),
(5, 25, 'x'),
(5, 26, 'x'),
(5, 27, 'x'),
(5, 28, 'x'),
(5, 29, 'null'),
(5, 30, 'x'),
(5, 31, 'x'),
(9, 1, 'null'),
(9, 2, 'x'),
(9, 3, 'x'),
(9, 4, 'x'),
(9, 5, 'x'),
(9, 6, 'x'),
(9, 7, 'x'),
(9, 8, 'null'),
(9, 9, 'n'),
(9, 10, 'x'),
(9, 15, 'null'),
(9, 29, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `chamcong`
--

CREATE TABLE `chamcong` (
  `machamcong` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `thang` date NOT NULL,
  `songaylam` float DEFAULT NULL,
  `songaynghi` int(11) DEFAULT NULL,
  `songaychunhat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chamcong`
--

INSERT INTO `chamcong` (`machamcong`, `manv`, `thang`, `songaylam`, `songaynghi`, `songaychunhat`) VALUES
(5, 15, '2017-01-01', NULL, NULL, NULL),
(9, 16, '2017-01-01', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chitietluong`
--

CREATE TABLE `chitietluong` (
  `maluong` int(11) NOT NULL,
  `luongcoban` int(11) NOT NULL,
  `tongsongay` float NOT NULL,
  `songaynghi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `macv` int(11) NOT NULL,
  `tencv` varchar(256) NOT NULL,
  `phucap` double NOT NULL,
  `ghichu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chucvu`
--

INSERT INTO `chucvu` (`macv`, `tencv`, `phucap`, `ghichu`) VALUES
(1, 'Nhân Viên', 0.1, 'nv'),
(2, 'Trưởng Phòng', 0.2, 'tp');

-- --------------------------------------------------------

--
-- Table structure for table `hopdonglaodong`
--

CREATE TABLE `hopdonglaodong` (
  `mahd` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `tungay` date NOT NULL,
  `denngay` date NOT NULL,
  `diadiemlamviec` varchar(256) NOT NULL,
  `thoigianlamviec` int(11) NOT NULL,
  `hesoluong` double NOT NULL,
  `macv` int(11) NOT NULL,
  `maphong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hopdonglaodong`
--

INSERT INTO `hopdonglaodong` (`mahd`, `manv`, `tungay`, `denngay`, `diadiemlamviec`, `thoigianlamviec`, `hesoluong`, `macv`, `maphong`) VALUES
(39, 15, '2019-11-20', '2019-11-20', 'Tp HCM', 8, 1, 1, NULL),
(40, 16, '2019-11-20', '2019-11-20', 'Tp HCM', 8, 1, 1, NULL),
(41, 15, '2019-11-20', '2019-11-20', 'Tp HCM', 8, 1, 1, 1),
(42, 16, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, 2),
(43, 15, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, NULL),
(44, 15, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, 1),
(45, 16, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, NULL),
(46, 16, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, 1),
(47, 16, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 2, 1),
(48, 16, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, 1),
(49, 15, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 2, 1),
(50, 15, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, 1),
(51, 16, '2019-11-20', '2019-11-26', 'Tp HCM', 8, 1, 2, 1),
(52, 15, '2019-11-20', '2019-11-21', 'Tp HCM', 8, 1, 1, NULL),
(53, 15, '2019-11-20', '2019-11-26', 'Tp HCM', 8, 1, 1, 1),
(54, 17, '2019-11-22', '2099-12-31', 'Tp HCM', 8, 1, 1, NULL),
(55, 16, '2019-11-20', '2019-11-29', 'Tp HCM', 8, 1, 1, 1),
(56, 15, '2019-11-20', '2099-12-31', 'Tp HCM', 8, 1, 2, 1),
(57, 18, '2019-11-26', '2099-12-31', 'Tp HCM', 8, 1, 1, NULL),
(58, 16, '2019-11-20', '2019-11-29', 'Tp HCM', 8, 1, 1, NULL),
(59, 16, '2019-11-20', '2099-12-31', 'Tp HCM', 8, 1, 1, 1),
(60, 19, '2019-12-02', '2099-12-31', 'Tp HCM', 8, 1, 1, NULL),
(61, 20, '2019-12-02', '2099-12-31', 'Tp HCM', 8, 1, 1, NULL),
(62, 21, '2019-12-07', '2099-12-31', 'Tp HCM', 8, 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `khenthuong`
--

CREATE TABLE `khenthuong` (
  `makhenthuong` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `ngaykhenthuong` date NOT NULL,
  `hinhthuc` varchar(256) NOT NULL,
  `lydo` varchar(256) NOT NULL,
  `tienthuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khenthuong`
--

INSERT INTO `khenthuong` (`makhenthuong`, `manv`, `ngaykhenthuong`, `hinhthuc`, `lydo`, `tienthuong`) VALUES
(1, 15, '2017-01-05', 'TM', 'test', 500000),
(3, 16, '2019-12-05', 'CK', 'test lần 2', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `luong`
--

CREATE TABLE `luong` (
  `maluong` int(11) NOT NULL,
  `luongcoban` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `luong`
--

INSERT INTO `luong` (`maluong`, `luongcoban`) VALUES
(2, 2000),
(1, 500000);

-- --------------------------------------------------------

--
-- Table structure for table `luongthang`
--

CREATE TABLE `luongthang` (
  `maluongthang` int(11) NOT NULL,
  `manhanvien` int(11) NOT NULL,
  `thang` datetime NOT NULL,
  `tongluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `honv` varchar(256) NOT NULL,
  `tennv` varchar(256) NOT NULL,
  `socmnd` varchar(256) NOT NULL,
  `ngaysinh` date NOT NULL,
  `gioitinh` varchar(256) NOT NULL,
  `sdt` varchar(256) NOT NULL,
  `diachi` varchar(256) NOT NULL,
  `maluong` int(11) DEFAULT NULL,
  `isdeleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `honv`, `tennv`, `socmnd`, `ngaysinh`, `gioitinh`, `sdt`, `diachi`, `maluong`, `isdeleted`) VALUES
(15, 'Nguyễn Văn', 'D', '123456789', '1999-01-01', 'Nam', '0909090909', '123 Abc', 1, 0),
(16, 'Nguyễn Thị', 'B', '159874236', '1999-01-01', 'Nữ', '0909090908', '123 Abc', 1, 0),
(17, 'abc', 'def', '1234567897', '1999-01-01', 'abc', '123567890', '123 abc', 1, 1),
(18, 'hello', 'hello', '123456798', '1999-01-01', 'nữ', '0909090807', '123 ghj', 1, 1),
(19, 'Nguyễn Văn', 'FFF', '123456789', '1999-01-01', 'Nam', '0909090909', '123 Abc', 2, 0),
(20, 'Nguyễn Văn', 'Xyz', '123456789', '1999-01-01', 'Nam', '0909090909', '123 Abc', 1, 0),
(21, 'lương', 'lương', '036985214', '1999-11-11', 'Nam', '0123456789', '123 Abc', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `maphanquyen` int(11) NOT NULL,
  `tenphanquyen` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `phongban`
--

CREATE TABLE `phongban` (
  `maphong` int(11) NOT NULL,
  `tenphong` varchar(256) NOT NULL,
  `matruongphong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phongban`
--

INSERT INTO `phongban` (`maphong`, `tenphong`, `matruongphong`) VALUES
(1, 'Nhân Sự', 15),
(2, 'Tài chính', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `tendangnhap` varchar(64) NOT NULL,
  `matkhau` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`tendangnhap`, `matkhau`) VALUES
('dieu', '$2a$12$uXHT1Zn5MO4Znc9.qiMn0utM/E2OUzQfZSytB0GC.VczLvjtwsnEm'),
('huy', '$2a$12$RWpBarCYvcx77n6JzbI8E.84kZ1DE5GPWXCbDlq1P9VGccSrfYO.G'),
('thu', '$2a$12$Vojdl5a9AMFMXecilDWIQ.ZK9pXBRQoMrLE2A3z772PJ20GGbWCMW'),
('thuy', '$2a$12$cpKOc2WxxmgXbPXvmR74muwwIYraccoN8SnJjsDbQoRTU7/athALu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangchamcong`
--
ALTER TABLE `bangchamcong`
  ADD UNIQUE KEY `UC_bangchamcong` (`machamcong`,`ngaytrongthang`);

--
-- Indexes for table `chamcong`
--
ALTER TABLE `chamcong`
  ADD PRIMARY KEY (`machamcong`),
  ADD UNIQUE KEY `UC_chamcong` (`manv`,`thang`);

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`macv`);

--
-- Indexes for table `hopdonglaodong`
--
ALTER TABLE `hopdonglaodong`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `FK_PhongBan_HD` (`maphong`),
  ADD KEY `FK_ChucVu_HD` (`macv`),
  ADD KEY `FK_NhanVien_HD` (`manv`);

--
-- Indexes for table `khenthuong`
--
ALTER TABLE `khenthuong`
  ADD PRIMARY KEY (`makhenthuong`),
  ADD KEY `FK_NhanVien` (`manv`);

--
-- Indexes for table `luong`
--
ALTER TABLE `luong`
  ADD PRIMARY KEY (`maluong`),
  ADD UNIQUE KEY `unq_luong` (`luongcoban`);

--
-- Indexes for table `luongthang`
--
ALTER TABLE `luongthang`
  ADD PRIMARY KEY (`maluongthang`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `fk_luong_nv` (`maluong`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`maphanquyen`);

--
-- Indexes for table `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`maphong`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`tendangnhap`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chamcong`
--
ALTER TABLE `chamcong`
  MODIFY `machamcong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `macv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `hopdonglaodong`
--
ALTER TABLE `hopdonglaodong`
  MODIFY `mahd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `khenthuong`
--
ALTER TABLE `khenthuong`
  MODIFY `makhenthuong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `luong`
--
ALTER TABLE `luong`
  MODIFY `maluong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `luongthang`
--
ALTER TABLE `luongthang`
  MODIFY `maluongthang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `maphanquyen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phongban`
--
ALTER TABLE `phongban`
  MODIFY `maphong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hopdonglaodong`
--
ALTER TABLE `hopdonglaodong`
  ADD CONSTRAINT `FK_ChucVu_HD` FOREIGN KEY (`macv`) REFERENCES `chucvu` (`macv`),
  ADD CONSTRAINT `FK_NhanVien_HD` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  ADD CONSTRAINT `FK_PhongBan_HD` FOREIGN KEY (`maphong`) REFERENCES `phongban` (`maphong`);

--
-- Constraints for table `khenthuong`
--
ALTER TABLE `khenthuong`
  ADD CONSTRAINT `FK_NhanVien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_luong_nv` FOREIGN KEY (`maluong`) REFERENCES `luong` (`maluong`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
