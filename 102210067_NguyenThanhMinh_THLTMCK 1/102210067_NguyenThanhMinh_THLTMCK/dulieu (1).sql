-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 30, 2023 lúc 04:48 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dulieu`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `benh`
--

CREATE TABLE `benh` (
  `mabenh` varchar(5) NOT NULL,
  `tenbenh` text NOT NULL,
  `mota` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `benh`
--

INSERT INTO `benh` (`mabenh`, `tenbenh`, `mota`) VALUES
('1', 'corona', 'nguy hiem'),
('2a', 'covac', 'nguy hiem 2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` varchar(5) NOT NULL,
  `hotenkh` text NOT NULL,
  `sodienthoai` text NOT NULL,
  `diachikh` text NOT NULL,
  `ngaysinh` date NOT NULL,
  `gioitinh` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `hotenkh`, `sodienthoai`, `diachikh`, `ngaysinh`, `gioitinh`) VALUES
('1a', 'thanh minh', '0886403456', '62 hht', '2003-07-04', 1),
('2a', 'anh kha ', '0991133455', '583 hcm', '2000-03-05', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsutiemphong`
--

CREATE TABLE `lichsutiemphong` (
  `makh` varchar(5) NOT NULL,
  `mavacxin` varchar(5) NOT NULL,
  `sttmui` int(11) NOT NULL,
  `ngaytiemphong` date NOT NULL,
  `ngayhentieptheo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lichsutiemphong`
--

INSERT INTO `lichsutiemphong` (`makh`, `mavacxin`, `sttmui`, `ngaytiemphong`, `ngayhentieptheo`) VALUES
('1a', '1ac', 1, '2024-11-06', '2024-11-29');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongban`
--

CREATE TABLE `phongban` (
  `IDPB` varchar(50) NOT NULL,
  `Tenpb` varchar(50) NOT NULL,
  `Mota` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phongban`
--

INSERT INTO `phongban` (`IDPB`, `Tenpb`, `Mota`) VALUES
('1p', 'pb1', 'khong co'),
('1p1', 'avc', 'cc'),
('2p', 'pb2', 'co 2222'),
('aa', 'aa', 'aa1'),
('ccc', 'zxczxc', 'zxcxzc'),
('naruto', 'hello', '11'),
('pb1', 'phong1a', 'q1 trung'),
('pb2', '2b', 'q2 ria '),
('vbv', 'DFS', 'DFS'),
('zzz', 'zzz1', 'zzz');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongbenh`
--

CREATE TABLE `phongbenh` (
  `mavacxin` varchar(5) NOT NULL,
  `mabenh` varchar(5) NOT NULL,
  `ghichu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vacxin`
--

CREATE TABLE `vacxin` (
  `mavacxin` varchar(50) NOT NULL,
  `tenvacxin` text NOT NULL,
  `somui` int(11) NOT NULL,
  `mota` text NOT NULL,
  `giavacxin` int(11) NOT NULL,
  `tenhangsx` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vacxin`
--

INSERT INTO `vacxin` (`mavacxin`, `tenvacxin`, `somui`, `mota`, `giavacxin`, `tenhangsx`) VALUES
('1ac', 'lao', 3, 'ok lam', 32, 'pdf'),
('2acv', 'covac', 2, 'motacovac', 112, 'fa');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `benh`
--
ALTER TABLE `benh`
  ADD PRIMARY KEY (`mabenh`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`IDPB`);

--
-- Chỉ mục cho bảng `vacxin`
--
ALTER TABLE `vacxin`
  ADD PRIMARY KEY (`mavacxin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
