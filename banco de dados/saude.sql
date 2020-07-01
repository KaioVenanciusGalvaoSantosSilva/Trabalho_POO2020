-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 01-Jul-2020 às 09:50
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `saude`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nomeCliente` varchar(50) NOT NULL,
  `CPFCliente` varchar(14) NOT NULL,
  `emailCliente` varchar(35) NOT NULL,
  `telefoneCliente` int(11) NOT NULL,
  `CEPCliente` int(11) NOT NULL,
  `enderecoCliente` varchar(40) NOT NULL,
  `numeroEndereco` int(11) NOT NULL,
  `complementoEndereco` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nomeCliente`, `CPFCliente`, `emailCliente`, `telefoneCliente`, `CEPCliente`, `enderecoCliente`, `numeroEndereco`, `complementoEndereco`) VALUES
(1, '111111111111', '111111111', '1111111111', 1111111, 111111111, '1111111111', 11111111, '111111111111'),
(2, 'ze', '222', 'kkk', 111, 111, 'kkk', 111, 'kkk');

-- --------------------------------------------------------

--
-- Estrutura da tabela `entrada`
--

CREATE TABLE `entrada` (
  `idEntrada` int(11) NOT NULL,
  `nomeProd` varchar(50) NOT NULL,
  `nomeFornecedor` varchar(30) NOT NULL,
  `lote` varchar(15) NOT NULL,
  `valorTotal` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `dataEntrada` date NOT NULL,
  `dataValidade` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `entrada`
--

INSERT INTO `entrada` (`idEntrada`, `nomeProd`, `nomeFornecedor`, `lote`, `valorTotal`, `quantidade`, `dataEntrada`, `dataValidade`) VALUES
(1, 'Agua', 'lindona', '00001', 2000, 10000, '2020-06-30', '2022-06-30'),
(2, 'Agua', 'minaalba', '00003', 2000, 10000, '2020-06-30', '2022-06-30'),
(3, 'Advil', 'Bayer é Bom', '00001', 4000, 4000, '2020-06-30', '2023-06-30'),
(4, 'Resfrenol', 'Bayer', '00001', 2000, 4000, '2020-06-30', '2023-06-30'),
(5, 'testeEntrada', 'buier', '00001', 1, 1, '2020-06-30', '2020-07-31');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE `estoque` (
  `idEstoque` int(11) NOT NULL,
  `idProd` int(11) NOT NULL,
  `idEnt` int(11) NOT NULL,
  `quantidadeAtual` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`idEstoque`, `idProd`, `idEnt`, `quantidadeAtual`) VALUES
(1, 1, 1, 20),
(2, 2, 2, 50),
(3, 3, 3, 10),
(7, 5, 4, 54);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `idFornecedor` int(11) NOT NULL,
  `nomeFornecedor` varchar(50) NOT NULL,
  `CNPJFornecedor` varchar(14) NOT NULL,
  `emailFornecedor` varchar(35) NOT NULL,
  `telefoneFornecedor` int(11) NOT NULL,
  `CEPFornecedor` int(11) NOT NULL,
  `enderecoFornecedor` varchar(40) NOT NULL,
  `numeroEndereco` int(11) NOT NULL,
  `complementoEndereco` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`idFornecedor`, `nomeFornecedor`, `CNPJFornecedor`, `emailFornecedor`, `telefoneFornecedor`, `CEPFornecedor`, `enderecoFornecedor`, `numeroEndereco`, `complementoEndereco`) VALUES
(1, '00', '00', '00', 0, 0, '00', 0, '00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `idFuncionario` int(11) NOT NULL,
  `nomeFuncionario` varchar(50) NOT NULL,
  `CPFFuncionario` varchar(11) NOT NULL,
  `emailFuncionario` varchar(35) NOT NULL,
  `telefoneFuncionario` int(11) NOT NULL,
  `CEPFuncionario` int(11) NOT NULL,
  `enderecoFuncionario` varchar(40) NOT NULL,
  `numeroEndereco` int(11) NOT NULL,
  `complementoEndereco` varchar(40) NOT NULL,
  `area` varchar(30) NOT NULL,
  `funcao` varchar(30) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `dataDesligamento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`idFuncionario`, `nomeFuncionario`, `CPFFuncionario`, `emailFuncionario`, `telefoneFuncionario`, `CEPFuncionario`, `enderecoFuncionario`, `numeroEndereco`, `complementoEndereco`, `area`, `funcao`, `dataAdmissao`, `dataDesligamento`) VALUES
(1, 'Kaio', '44444444444', 'kaio@saude.com', 111111111, 444444444, 'av H', 44, 'Mercado shibata', 'Saúde', 'Gerente', '2020-07-01', '2100-01-01'),
(2, 'Bruno', '666666', 'bruno@saude.com', 222222, 666666, 'av B', 22, 'Mercado judite', 'Gestão', 'Gerente', '2020-07-01', '2100-01-01'),
(3, 'Luan', '888', 'luan@saude.com', 11111, 2222, 'av L', 11, 'Mercado C&C', 'Saúde', 'Farmacêutico', '2020-07-01', '2100-01-01'),
(4, 'Luccas', '777', 'luccas@saude.com', 238555, 99955, 'av ACC', 88, 'Mercado Pedrão', 'Administração', 'Atendente', '2020-07-01', '2100-01-01'),
(5, 'admin', '0', 'admin@saude.com', 0, 0, '0', 0, '0', 'Gestão', 'Gerente', '2020-07-01', '2100-01-01'),
(6, 'admin2', '0', 'admin@saude.com', 0, 0, '0', 0, '0', 'Gestão', 'Gerente', '2020-07-01', '2100-01-01');

-- --------------------------------------------------------

--
-- Estrutura da tabela `login`
--

CREATE TABLE `login` (
  `idUsuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `cargo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`idUsuario`, `usuario`, `senha`, `cargo`) VALUES
(1, 'kaio', '123', 'estoquista'),
(2, 'luccas', '123', 'atendente'),
(3, 'bruno', '123', 'gerente'),
(4, 'luan', '123', 'farmaceutico'),
(5, '', '', ''),
(6, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL,
  `nomeProduto` varchar(50) NOT NULL,
  `valorUnitario` double NOT NULL,
  `tipoProduto` varchar(50) NOT NULL,
  `principioAtivo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`idProduto`, `nomeProduto`, `valorUnitario`, `tipoProduto`, `principioAtivo`) VALUES
(1, 'agua', 2, 'Bebida', 'Nenhum'),
(2, 'Refrigerante', 4, 'Bebida', 'Nenhum'),
(3, 'Energetico r9', 8, 'Bebida', 'Nenhum'),
(4, 'Agua de Coco', 5, 'Bebida', 'Nenhum'),
(5, 'Advil', 5, 'Remedio', 'Paracetamol');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Índices para tabela `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`idEntrada`) USING BTREE;

--
-- Índices para tabela `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`idEstoque`) USING BTREE,
  ADD UNIQUE KEY `idEnt` (`idEnt`),
  ADD KEY `idProd` (`idProd`);

--
-- Índices para tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`idFornecedor`);

--
-- Índices para tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`idFuncionario`);

--
-- Índices para tabela `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`idProduto`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `entrada`
--
ALTER TABLE `entrada`
  MODIFY `idEntrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `estoque`
--
ALTER TABLE `estoque`
  MODIFY `idEstoque` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `idFornecedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `idFuncionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `login`
--
ALTER TABLE `login`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `idProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `estoque_fk_entrada` FOREIGN KEY (`idEnt`) REFERENCES `entrada` (`idEntrada`),
  ADD CONSTRAINT `estoque_fk_produto` FOREIGN KEY (`idProd`) REFERENCES `produto` (`idProduto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
