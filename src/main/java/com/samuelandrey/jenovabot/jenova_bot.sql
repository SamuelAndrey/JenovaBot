-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jul 2023 pada 01.32
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jenova_bot`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_admin`
--

CREATE TABLE `tb_admin` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_admin`
--

INSERT INTO `tb_admin` (`id_admin`, `username`, `password`) VALUES
(1, 'samuel', 'e6fb448feb2fa877aab63b3713027775'),
(3, 'admin', '0192023a7bbd73250516f069df18b500');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_broadcast`
--

CREATE TABLE `tb_broadcast` (
  `id_user` bigint(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_broadcast`
--

INSERT INTO `tb_broadcast` (`id_user`) VALUES
(1344122685);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `tb_broadcast_info`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `tb_broadcast_info` (
`id_user` bigint(14)
,`username` varchar(100)
,`firstname` varchar(100)
,`lastname` varchar(100)
,`token` smallint(6)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_history`
--

CREATE TABLE `tb_history` (
  `id_history` int(11) NOT NULL,
  `id_user` bigint(14) NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp(),
  `request` text NOT NULL,
  `response` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_history`
--

INSERT INTO `tb_history` (`id_history`, `id_user`, `time`, `request`, `response`) VALUES
(2, 1344122685, '2023-06-30 15:48:03', 'Halo', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(3, 1344122685, '2023-06-30 15:48:14', 'Halo', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(4, 1344122685, '2023-06-30 15:48:28', 'Apakabar dengan saya', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(5, 1344122685, '2023-06-30 15:48:37', 'Bla bla bla', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(6, 1344122685, '2023-06-30 15:48:46', 'THIS IS BROADCAST FROM ADMIN', 'Pengumuman'),
(7, 1344122685, '2023-06-30 16:16:58', 'history baru', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(8, 1878849884, '2023-06-30 22:09:13', 'Bayi perempuan', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(9, 1878849884, '2023-06-30 22:09:33', 'Oke', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(10, 1878849884, '2023-06-30 22:09:37', 'Okkkkk', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt \'space\' your prompt, _example : /gpt how to coding_\r\n/mytoken - your remaining tokens Chat GPT\r\n\r\n*Info*\r\n/myinfo - your account info\r\n\r\n*Other*\r\n/hello - menyapamu\r\n'),
(11, 1344122685, '2023-07-01 00:37:57', '/mytoken', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n\r\n*Chat GPT*\r\n/gpt - how to use fiture Chat GPT in Jenova Bot\n/mytoken - your remaining tokens for Chat GPT\r\n/myinfo - your account info\r\n\n'),
(12, 1344122685, '2023-07-01 00:39:01', '/mytoken', 'Hello samuelandrey your remaining token for Chat GPT is : 5'),
(13, 1344122685, '2023-07-01 00:40:31', '/mytoken', 'Hello Samuel your remaining token for Chat GPT is : count  5'),
(14, 1344122685, '2023-07-01 00:41:49', 'jkhjkl', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n*Chat GPT*\r\n/gpt - how to use fiture Chat GPT in Jenova Bot\n/mytoken - your remaining tokens for Chat GPT\r\n/myinfo - your account info\r\n\n'),
(15, 1344122685, '2023-07-01 00:49:06', '/myinfo', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n*Chat GPT*\r\n/gpt - how to use fiture Chat GPT in Jenova Bot\n/mytoken - your remaining tokens for Chat GPT\r\n/myinfo - your account info\r\n\n'),
(16, 1344122685, '2023-07-01 00:49:47', '/myinfo', 'UserID/ChatID	: 1344122685\nUsername	: samuelandrey\nFirstname	: Samuel\nLastname	: Andrey'),
(17, 1344122685, '2023-07-01 00:50:39', '/myinfo', 'UserID/ChatID	: 1344122685\nUsername		: samuelandrey\nFirstname		: Samuel\nLastname		: Andrey'),
(18, 1344122685, '2023-07-01 01:00:59', 'THIS IS BROADCAST FROM ADMIN', 'dsfa'),
(19, 1344122685, '2023-07-01 01:01:14', 'THIS IS BROADCAST FROM ADMIN', 'meowww');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `tb_history_info`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `tb_history_info` (
`id_history` int(11)
,`id_user` bigint(14)
,`username` varchar(100)
,`time` datetime
,`request` text
,`response` text
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_keyword`
--

CREATE TABLE `tb_keyword` (
  `id_keyword` int(11) NOT NULL,
  `command` varchar(100) NOT NULL,
  `response` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_keyword`
--

INSERT INTO `tb_keyword` (`id_keyword`, `command`, `response`) VALUES
(2, '/semua', 'halo semua manusia yang hidup'),
(3, '/setdefault', '*Jenova Bot*\r\nWelcome to jenova bot, you can control me by sending these commands:\r\n\r\n*Chat GPT*\r\n/gpt - how to use fiture Chat GPT in Jenova Bot\n/mytoken - your remaining tokens for Chat GPT\r\n/myinfo - your account info\r\n\n'),
(5, '/gpt', '*Cara menggunakan fitur Chat GPT pada Jenova Bot*\n\nketik /gpt spasi prompt anda\n\nBerikut ada beberapa contoh penggunaan:\n`/gpt cara membuat nasi goreng`\n`/gpt cara hello world di java`\n`/gpt jelaskan perbedaan antara kodok dan katak`\n`/gpt cara mendapatkan ipk diatas 4`\n\nSecara default, anda telah memiliki 2 token chat gpt, token digunakan untuk menjalankan fitur chat gpt pada Jenova Bot.\n\nUntuk melihat sisan token anda dapat melakukan command `/mytoken`. '),
(8, '/cara', 'Apa yang ingin kamu tanyakan?');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` bigint(14) NOT NULL,
  `username` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `token` smallint(6) NOT NULL DEFAULT 2
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `username`, `firstname`, `lastname`, `token`) VALUES
(1220637501, 'AyatullahMaarif', 'Ayatullah', 'Ma\'arif', 2),
(1344122685, 'samuelandrey', 'Samuel', 'Andrey', 5),
(1847285486, 'M_Roynaldi30', 'Roy', 'Aldi', 2),
(1878849884, '', 'Nadyanella', 'Riesya Aprilla', 2);

-- --------------------------------------------------------

--
-- Struktur untuk view `tb_broadcast_info`
--
DROP TABLE IF EXISTS `tb_broadcast_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`` SQL SECURITY DEFINER VIEW `tb_broadcast_info`  AS SELECT `tb_user`.`id_user` AS `id_user`, `tb_user`.`username` AS `username`, `tb_user`.`firstname` AS `firstname`, `tb_user`.`lastname` AS `lastname`, `tb_user`.`token` AS `token` FROM (`tb_user` join `tb_broadcast` on(`tb_user`.`id_user` = `tb_broadcast`.`id_user`))  ;

-- --------------------------------------------------------

--
-- Struktur untuk view `tb_history_info`
--
DROP TABLE IF EXISTS `tb_history_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`` SQL SECURITY DEFINER VIEW `tb_history_info`  AS SELECT `tb_history`.`id_history` AS `id_history`, `tb_user`.`id_user` AS `id_user`, `tb_user`.`username` AS `username`, `tb_history`.`time` AS `time`, `tb_history`.`request` AS `request`, `tb_history`.`response` AS `response` FROM (`tb_user` join `tb_history`) WHERE `tb_user`.`id_user` = `tb_history`.`id_user` ORDER BY `tb_history`.`time` AS `DESCdesc` ASC  ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `tb_broadcast`
--
ALTER TABLE `tb_broadcast`
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  ADD PRIMARY KEY (`id_history`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_keyword`
--
ALTER TABLE `tb_keyword`
  ADD PRIMARY KEY (`id_keyword`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_admin`
--
ALTER TABLE `tb_admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  MODIFY `id_history` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `tb_keyword`
--
ALTER TABLE `tb_keyword`
  MODIFY `id_keyword` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_broadcast`
--
ALTER TABLE `tb_broadcast`
  ADD CONSTRAINT `tb_broadcast_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_history`
--
ALTER TABLE `tb_history`
  ADD CONSTRAINT `tb_history_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
