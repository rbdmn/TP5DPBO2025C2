-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 29, 2025 at 03:04 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int NOT NULL,
  `nim` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `nama` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `jenis_kelamin` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `prodi` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `prodi`) VALUES
(1, '2301920', 'Safirul', 'Laki Laki', 'Ilmu Komputer'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'Pendidikan Ilmu Komputer'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-Laki', 'Ilmu Komputer'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'Pendidikan Ilmu Komputer'),
(5, '2202046', 'Nurainun', 'Perempuan', 'Pendidikan Ilmu Komputer'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-Laki', 'Ilmu Komputer'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'Pendidikan Ilmu Komputer'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'Pendidikan Ilmu Komputer'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-Laki', 'Ilmu Komputer'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'Pendidikan Ilmu Komputer'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'Pendidikan Ilmu Komputer'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'Pendidikan Ilmu Komputer'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-Laki', 'Ilmu Komputer'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-Laki', 'Ilmu Komputer'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-Laki', 'Ilmu Komputer'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'Ilmu Komputer'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'Pendidikan Ilmu Komputer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
