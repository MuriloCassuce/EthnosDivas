# 🎤 EthnosDivas — Pop Divas

> **Jogo de tabuleiro musical baseado no sistema Ethnos**, implementado em Java.  
> Reúna suas divas, domine regiões e conquiste a glória da indústria musical!

---

## 📖 Sobre o Projeto

**EthnosDivas** é uma implementação digital do clássico sistema de jogo de tabuleiro **Ethnos**, reimaginado com uma temática pop e musical. Os jogadores recrutam "divas" de diferentes estilos musicais, formam bandas/grupos e disputam o controle de regiões para acumular pontos de glória ao longo de múltiplas eras.

O projeto foi desenvolvido em Java, utilizando Maven como gerenciador de dependências e build, com estrutura orientada a objetos.

---

## 🎮 Sobre o Sistema Ethnos

O sistema Ethnos é um jogo de controle de área com mecânicas de *drafting* aberto. A versão base funciona assim:

- Os jogadores **compram cartas** de um baralho ou de cartas viradas para cima na mesa.
- As cartas representam membros de diferentes **tribos/grupos** (aqui, estilos musicais ou divas).
- Em seu turno, o jogador pode **recrutar uma aliada** (comprar carta) ou **jogar uma banda** (um conjunto de cartas combinando por grupo ou cor/região).
- Ao jogar uma banda, o líder ativa um **poder especial** e o jogador coloca tokens de controle na **região correspondente**.
- Ao fim de cada era (quando o dragão/coringa aparece), calculam-se os **pontos de glória** por região.
- Vence quem acumular **mais glória** ao final de todas as eras.

---

## 🏗️ Estrutura do Projeto

```
EthnosDivas/
├── src/
│   └── main/
│       └── java/
│           └── EthnosDivas/       # Pacote principal do jogo
│               └── Main.java      # Ponto de entrada da aplicação
├── target/
│   └── classes/                   # Bytecode compilado
├── pom.xml                        # Configuração Maven
└── README.md
```

---

## 🛠️ Tecnologias

| Tecnologia | Versão  | Uso                        |
|------------|---------|----------------------------|
| Java       | 17      | Linguagem principal        |
| Maven      | 3.x     | Build e gerenciamento      |
| exec-maven-plugin | 3.1.0 | Execução via Maven  |
| maven-compiler-plugin | 3.11.0 | Compilação        |

---

## ⚙️ Pré-requisitos

- **Java 17+** instalado ([Download](https://adoptium.net/))
- **Maven 3.6+** instalado ([Download](https://maven.apache.org/download.cgi))

Verifique suas versões:

```bash
java -version
mvn -version
```

---

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/MuriloCassuce/EthnosDivas.git
cd EthnosDivas
```

### 2. Compile o projeto

```bash
mvn compile
```

### 3. Execute o jogo

```bash
mvn exec:java
```

Ou, se preferir gerar o `.jar` e executar diretamente:

```bash
mvn package
java -cp target/popdivas-1.0.0.jar EthnosDivas.Main
```

---

## 🎯 Funcionalidades

- [x] Estrutura base do jogo em Java
- [x] Sistema de turnos
- [x] Mecânica de recrutamento de divas (compra de cartas)
- [x] Formação de bandas/grupos
- [x] Controle de regiões
- [x] Cálculo de pontos de glória por era



---

> *"A glória pertence a quem domina os palcos — e as regiões."* 🎶
