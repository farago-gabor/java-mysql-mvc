-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Dec 01. 23:32
-- Kiszolgáló verziója: 10.4.32-MariaDB
-- PHP verzió: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `egyenisport`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `bajnoksag`
--

CREATE TABLE `bajnoksag` (
  `bId` int(11) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `kezdo_datum` date NOT NULL,
  `befejezo_datum` date NOT NULL CHECK (`befejezo_datum` >= `kezdo_datum`),
  `helyszin` varchar(100) NOT NULL,
  `meghivasos` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `bajnoksag`
--

INSERT INTO `bajnoksag` (`bId`, `nev`, `kezdo_datum`, `befejezo_datum`, `helyszin`, `meghivasos`) VALUES
(11, 'Nemzetközi Asztalitenisz Bajnokság', '2024-03-01', '2024-03-15', 'Budapest', 1),
(12, 'Téli Kupa', '2024-02-05', '2024-02-20', 'Debrecen', 0),
(13, 'Közép-Európai Labdarúgó Tornája', '2024-05-10', '2024-05-20', 'Székesfehérvár', 1),
(14, 'Asztalitenisz Világbajnoki Selejtező', '2024-06-01', '2024-06-30', 'Pécs', 1),
(15, 'Asztalitenisz Liga', '2024-04-01', '2024-04-10', 'Szeged', 0),
(16, 'Nyári Sportfesztivál', '2024-07-15', '2024-07-25', 'Veszprém', 0),
(17, 'Európa Kupa', '2024-08-01', '2024-08-15', 'Miskolc', 1),
(18, 'Kiskorú Asztalitenisz Kupa', '2024-09-05', '2024-09-12', 'Zalaegerszeg', 0),
(19, 'Nagy Nemzetközi Asztalitenisz Bajnokság', '2024-10-01', '2024-10-15', 'Győr', 1),
(20, 'Asztalitenisz Univerzum Kupa', '2024-11-01', '2024-11-10', 'Kecskemét', 0),
(21, 'VB', '2024-11-29', '2024-12-11', 'London', 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

CREATE TABLE `felhasznalo` (
  `felhasznalonev` varchar(50) NOT NULL,
  `jelszo` varchar(100) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `jogosultsag` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `felhasznalo`
--

INSERT INTO `felhasznalo` (`felhasznalonev`, `jelszo`, `nev`, `jogosultsag`) VALUES
('Admin', 'admin', 'Admin', 'admin'),
('Nezo', 'nezo', 'Nezo Nezo', 'nezo');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `fiokja`
--

CREATE TABLE `fiokja` (
  `vId` int(11) NOT NULL,
  `felhasznalonev` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `merkozesek`
--

CREATE TABLE `merkozesek` (
  `mId` int(11) NOT NULL,
  `eredmeny` varchar(100) DEFAULT NULL CHECK (`eredmeny` <> ''),
  `datum` date NOT NULL,
  `helyszin` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `merkozesek`
--

INSERT INTO `merkozesek` (`mId`, `eredmeny`, `datum`, `helyszin`) VALUES
(1, '2', '2024-03-02', 'Budapest'),
(2, '3', '2024-03-05', 'Budapest'),
(3, '5', '2024-02-06', 'Debrecen'),
(4, '8', '2024-02-10', 'Debrecen'),
(5, '9', '2024-05-11', 'Székesfehérvár'),
(6, '2', '2024-05-15', 'Székesfehérvár'),
(7, '4', '2024-06-03', 'Pécs'),
(8, '5', '2024-06-10', 'Pécs'),
(9, '7', '2024-07-16', 'Veszprém'),
(10, '10', '2024-07-20', 'Veszprém');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `resztvetel`
--

CREATE TABLE `resztvetel` (
  `vId` int(11) NOT NULL,
  `mId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `resztvetel`
--

INSERT INTO `resztvetel` (`vId`, `mId`) VALUES
(1, 1),
(1, 6),
(2, 1),
(2, 6),
(3, 2),
(3, 7),
(4, 2),
(4, 7),
(5, 3),
(5, 8),
(6, 3),
(6, 8),
(7, 4),
(7, 9),
(8, 4),
(8, 9),
(9, 5),
(9, 10),
(10, 5),
(10, 10);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tartozik`
--

CREATE TABLE `tartozik` (
  `mId` int(11) NOT NULL,
  `bId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `tartozik`
--

INSERT INTO `tartozik` (`mId`, `bId`) VALUES
(1, 11),
(2, 11),
(3, 12),
(4, 12),
(5, 13),
(6, 13),
(7, 14),
(8, 14),
(9, 15),
(10, 15);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `versenyzo`
--

CREATE TABLE `versenyzo` (
  `vId` int(11) NOT NULL,
  `nev` varchar(100) NOT NULL,
  `szuletesi_datum` date NOT NULL,
  `szuletesi_hely` varchar(100) NOT NULL,
  `allampolgarsag` varchar(50) NOT NULL,
  `gyozelmi_arany` decimal(5,2) DEFAULT NULL CHECK (`gyozelmi_arany` >= 0 and `gyozelmi_arany` <= 1),
  `aktiv` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `versenyzo`
--

INSERT INTO `versenyzo` (`vId`, `nev`, `szuletesi_datum`, `szuletesi_hely`, `allampolgarsag`, `gyozelmi_arany`, `aktiv`) VALUES
(1, 'Kovács Péter', '1990-04-12', 'Budapest', 'Magyar', 0.75, 1),
(2, 'Nagy László', '1985-07-22', 'Szeged', 'Magyar', 0.68, 1),
(3, 'Tóth Anna', '1993-03-15', 'Debrecen', 'Magyar', 0.80, 0),
(4, 'Szabó Márton', '2000-11-10', 'Győr', 'Magyar', 0.50, 1),
(5, 'Hegedűs Katalin', '1998-06-01', 'Pécs', 'Magyar', 0.65, 1),
(6, 'Horváth Tamás', '1995-01-25', 'Székesfehérvár', 'Magyar', 0.55, 1),
(7, 'Papp Ádám', '1997-09-15', 'Veszprém', 'Magyar', 0.77, 1),
(8, 'Kiss Edit', '2001-02-12', 'Zalaegerszeg', 'Magyar', 0.72, 0),
(9, 'Molnár Csaba', '1992-12-05', 'Miskolc', 'Magyar', 0.61, 1),
(10, 'Farkas Sándor', '1988-08-18', 'Kecskemét', 'Magyar', 0.70, 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `bajnoksag`
--
ALTER TABLE `bajnoksag`
  ADD PRIMARY KEY (`bId`);

--
-- A tábla indexei `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`felhasznalonev`);

--
-- A tábla indexei `fiokja`
--
ALTER TABLE `fiokja`
  ADD PRIMARY KEY (`vId`,`felhasznalonev`),
  ADD KEY `felhasznalonev` (`felhasznalonev`);

--
-- A tábla indexei `merkozesek`
--
ALTER TABLE `merkozesek`
  ADD PRIMARY KEY (`mId`);

--
-- A tábla indexei `resztvetel`
--
ALTER TABLE `resztvetel`
  ADD PRIMARY KEY (`vId`,`mId`),
  ADD KEY `mId` (`mId`);

--
-- A tábla indexei `tartozik`
--
ALTER TABLE `tartozik`
  ADD PRIMARY KEY (`mId`,`bId`),
  ADD KEY `bId` (`bId`);

--
-- A tábla indexei `versenyzo`
--
ALTER TABLE `versenyzo`
  ADD PRIMARY KEY (`vId`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `bajnoksag`
--
ALTER TABLE `bajnoksag`
  MODIFY `bId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT a táblához `merkozesek`
--
ALTER TABLE `merkozesek`
  MODIFY `mId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `versenyzo`
--
ALTER TABLE `versenyzo`
  MODIFY `vId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `fiokja`
--
ALTER TABLE `fiokja`
  ADD CONSTRAINT `fiokja_ibfk_1` FOREIGN KEY (`vId`) REFERENCES `versenyzo` (`vId`) ON DELETE CASCADE,
  ADD CONSTRAINT `fiokja_ibfk_2` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE CASCADE;

--
-- Megkötések a táblához `resztvetel`
--
ALTER TABLE `resztvetel`
  ADD CONSTRAINT `resztvetel_ibfk_1` FOREIGN KEY (`vId`) REFERENCES `versenyzo` (`vId`) ON DELETE CASCADE,
  ADD CONSTRAINT `resztvetel_ibfk_2` FOREIGN KEY (`mId`) REFERENCES `merkozesek` (`mId`) ON DELETE CASCADE;

--
-- Megkötések a táblához `tartozik`
--
ALTER TABLE `tartozik`
  ADD CONSTRAINT `tartozik_ibfk_1` FOREIGN KEY (`mId`) REFERENCES `merkozesek` (`mId`) ON DELETE CASCADE,
  ADD CONSTRAINT `tartozik_ibfk_2` FOREIGN KEY (`bId`) REFERENCES `bajnoksag` (`bId`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
