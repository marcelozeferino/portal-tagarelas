-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Dez 15, 2012 as 06:44 PM
-- Versão do Servidor: 5.0.51
-- Versão do PHP: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `modulos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tab_upload`
--

CREATE TABLE `tab_upload` (
  `id` int(11) NOT NULL auto_increment,
  `img` varchar(350) default NULL,
  `temp` varchar(350) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `tab_upload`
--

INSERT INTO `tab_upload` (`id`, `img`, `temp`) VALUES
(1, 'avatar.jpg', 'avatar.jpg');
