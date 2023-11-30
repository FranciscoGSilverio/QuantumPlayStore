CREATE SCHEMA IF NOT EXISTS `QuantumPlayStore` DEFAULT CHARACTER SET utf8 ;
USE `QuantumPlayStore` ;

CREATE TABLE IF NOT EXISTS `QuantumPlayStore`.`Usuario` (
  `idUsuario` INT AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `idade` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS `QuantumPlayStore`.`Endereco` (
  `idEndereco` INT AUTO_INCREMENT NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Numero` VARCHAR(45) NOT NULL,
  `CEP` VARCHAR(45) NOT NULL,
  `Cidade` VARCHAR(45) NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  `Complemento` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idEndereco`, `Usuario_idUsuario`),
  INDEX `fk_Endereco_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `QuantumPlayStore`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `QuantumPlayStore`.`Jogo` (
  `idJogo` INT AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `preco` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idJogo`));

CREATE TABLE IF NOT EXISTS `QuantumPlayStore`.`Carrinho` (
  `idCarrinho` INT AUTO_INCREMENT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCarrinho`, `Usuario_idUsuario`),
  INDEX `fk_Carrinho_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Carrinho_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `QuantumPlayStore`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `QuantumPlayStore`.`Carrinho_has_Jogo` (
  `Carrinho_idCarrinho` INT NOT NULL,
  `Carrinho_Usuario_idUsuario` INT NOT NULL,
  `Jogo_idJogo` INT NOT NULL,
  PRIMARY KEY (`Carrinho_idCarrinho`, `Carrinho_Usuario_idUsuario`, `Jogo_idJogo`),
  INDEX `fk_Carrinho_has_Jogo_Jogo1_idx` (`Jogo_idJogo` ASC) VISIBLE,
  INDEX `fk_Carrinho_has_Jogo_Carrinho1_idx` (`Carrinho_idCarrinho` ASC, `Carrinho_Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Carrinho_has_Jogo_Carrinho1`
    FOREIGN KEY (`Carrinho_idCarrinho` , `Carrinho_Usuario_idUsuario`)
    REFERENCES `QuantumPlayStore`.`Carrinho` (`idCarrinho` , `Usuario_idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Carrinho_has_Jogo_Jogo1`
    FOREIGN KEY (`Jogo_idJogo`)
    REFERENCES `QuantumPlayStore`.`Jogo` (`idJogo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    
INSERT INTO Jogo (nome, preco) VALUES ('God of War', 50);
INSERT INTO Jogo (nome, preco) VALUES ('God of War 2', 60);
INSERT INTO Jogo (nome, preco) VALUES ('God of War 3', 70);
INSERT INTO Jogo (nome, preco) VALUES ('God of War PS4', 100);
INSERT INTO Jogo (nome, preco) VALUES ('God of War Ragnarok', 150);


