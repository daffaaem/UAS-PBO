-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Jun 2024 pada 09.19
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_anggota`
--

CREATE TABLE `tbl_anggota` (
  `id_anggota` varchar(15) NOT NULL,
  `npm` varchar(15) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `jk` varchar(20) NOT NULL,
  `id_jurusan` varchar(10) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_anggota`
--

INSERT INTO `tbl_anggota` (`id_anggota`, `npm`, `nama`, `jk`, `id_jurusan`, `no_hp`, `status`) VALUES
('A00001', '22081010189', 'Daffa Ammar', 'PRIA', 'UPN1', '081252197274', 'Aktif'),
('A00002', '22081010125', 'Ahmad Adib', 'PRIA', 'UPN2', '081288889999', 'Aktif'),
('A00005', '22081010198', 'dipa', 'PRIA', 'UPN5', '081262729276', 'Aktif'),
('A00006', '22081010175', 'Fajar', 'PRIA', 'UPN3', '081263489082', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_buku`
--

CREATE TABLE `tbl_buku` (
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `pengarang` varchar(30) NOT NULL,
  `tahun_terbit` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_buku`
--

INSERT INTO `tbl_buku` (`id_buku`, `judul`, `kategori`, `penerbit`, `pengarang`, `tahun_terbit`) VALUES
('B00001', 'Teknologi Informasi', 'Teknologi', 'Gramedia', 'Raihan Pratama', '2004'),
('B00003', 'dada didi', 'dada', 'dada', 'dada', '2004'),
('B00004', 'baba baba baba', 'baba', 'baba', 'baba', '2003'),
('B00005', 'Alam', 'Sains', 'Gramedia', 'James Bond', '1920');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_jurusan`
--

CREATE TABLE `tbl_jurusan` (
  `id_jurusan` varchar(10) NOT NULL,
  `jurusan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_jurusan`
--

INSERT INTO `tbl_jurusan` (`id_jurusan`, `jurusan`) VALUES
('UPN1', 'Informatika'),
('UPN2', 'Teknik Industri'),
('UPN3', 'Sistem Informasi'),
('UPN4', 'Kedokteran'),
('UPN5', 'Hukum'),
('UPN6', 'Manajemen'),
('UPN7', 'Hubungan Internasional'),
('UPN8', 'Teknik Pangan'),
('UPN9', 'Agribisnis');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_kembali`
--

CREATE TABLE `tbl_kembali` (
  `id_peminjam` varchar(15) NOT NULL,
  `id_anggota` varchar(20) NOT NULL,
  `id_buku` varchar(20) NOT NULL,
  `tanggal_kembali` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_kembali`
--

INSERT INTO `tbl_kembali` (`id_peminjam`, `id_anggota`, `id_buku`, `tanggal_kembali`) VALUES
('P00001', 'A00001', 'B00001', '2024-06-11');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_peminjam`
--

CREATE TABLE `tbl_peminjam` (
  `id_peminjam` varchar(20) NOT NULL,
  `id_anggota` varchar(15) NOT NULL,
  `id_buku` varchar(30) NOT NULL,
  `tanggal_pinjam` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_peminjam`
--

INSERT INTO `tbl_peminjam` (`id_peminjam`, `id_anggota`, `id_buku`, `tanggal_pinjam`) VALUES
('P00001', 'A00001', 'B00001', '2024-06-14');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_tingkat`
--

CREATE TABLE `tbl_tingkat` (
  `id_tingkat` int(1) NOT NULL,
  `tingkat` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_tingkat`
--

INSERT INTO `tbl_tingkat` (`id_tingkat`, `tingkat`) VALUES
(1, 'X'),
(2, 'XI'),
(3, 'XII');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `username`, `password`, `level`) VALUES
(1, 'Daffa Ammar', 'daffa', 'daffa', 'kepala'),
(2, 'adib', 'adib', 'adib', 'pustakawan'),
(3, 'Chasetyo Ivan', 'ipan', 'ipan', 'pustakawan');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_anggota`
--
ALTER TABLE `tbl_anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indeks untuk tabel `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indeks untuk tabel `tbl_jurusan`
--
ALTER TABLE `tbl_jurusan`
  ADD PRIMARY KEY (`id_jurusan`);

--
-- Indeks untuk tabel `tbl_kembali`
--
ALTER TABLE `tbl_kembali`
  ADD PRIMARY KEY (`id_peminjam`);

--
-- Indeks untuk tabel `tbl_peminjam`
--
ALTER TABLE `tbl_peminjam`
  ADD PRIMARY KEY (`id_peminjam`);

--
-- Indeks untuk tabel `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  ADD PRIMARY KEY (`id_tingkat`);

--
-- Indeks untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  MODIFY `id_tingkat` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
