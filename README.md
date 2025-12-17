# Produtor-Consumidor com Semáforos em JavaFX

## 📌 Descrição do Projeto

Este projeto tem como objetivo **estudar e demonstrar a solução do problema clássico de programação concorrente Produtor–Consumidor**, utilizando **semáforos** para controle de sincronização entre threads.

A aplicação foi desenvolvida **inteiramente em Java 1.8**, utilizando **JavaFX** para a interface gráfica. A escolha do Java 1.8 se deve ao fato de que essa versão já possui o JavaFX integrado nativamente, simplificando a configuração do ambiente.

O sistema simula produtores e consumidores que acessam um recurso compartilhado, garantindo:

* Exclusão mútua
* Sincronização correta entre threads
* Evitar condições de corrida
* Controle adequado do buffer compartilhado

Todo o comportamento concorrente pode ser visualizado em tempo real através da interface gráfica.

---

## 🎯 Objetivo Acadêmico

Este projeto foi desenvolvido como **trabalho proposto na disciplina de Programação Concorrente** da universidade, com fins **estritamente educacionais**, tendo como principal objetivo o estudo e a aplicação prática dos conceitos de concorrência.

O trabalho foca especialmente nos seguintes tópicos:

* Programação concorrente
* Uso de semáforos
* Sincronização de threads
* Região crítica
* Problema clássico Produtor–Consumidor
* Visualização do comportamento concorrente por meio de interface gráfica

---

## 🛠️ Tecnologias Utilizadas

* **Java 1.8**
* **JavaFX (UI)**
* **Semáforos** (`Semaphore`)
* **Threads**
* Programação concorrente

---

## 🧩 Estrutura do Projeto

```
controller/
model/
view/
utils/
resources/
img/
Principal.java
```

* **controller**: lógica de controle da aplicação
* **model**: regras de negócio e implementação do produtor, consumidor e buffer
* **view**: interface gráfica JavaFX
* **utils**: classes utilitárias
* **resources / img**: recursos visuais utilizados na UI
* **Principal.java**: ponto de entrada da aplicação

---

## ▶️ Execução da Aplicação

A seguir estão alguns **GIFs demonstrando a execução do sistema**, evidenciando o comportamento dos produtores e consumidores em tempo real.

### 🔁 Execução Geral

![Execução Geral](./img/gif-execucao-geral.gif)

---

### 🧵 Produtor em Ação

![Produtor](./img/gif-produtor.gif)

---

### 🧵 Consumidor em Ação

![Consumidor](./img/gif-consumidor.gif)

---

## 🚀 Como Executar o Projeto

1. Certifique-se de estar utilizando **Java 1.8**
2. Clone o repositório:

```bash
git clone https://github.com/MaickCross/producer-consumer-java.git
```

3. Abra o projeto em uma IDE compatível (IntelliJ, Eclipse, NetBeans)
4. Execute a classe:

```text
Principal.java
```

---

## 📚 Referência Teórica

O problema Produtor–Consumidor é um dos exemplos clássicos de programação concorrente, amplamente utilizado para demonstrar o uso de mecanismos de sincronização como **semáforos**.

Este projeto foi desenvolvido como parte do estudo desses conceitos, permitindo visualizar de forma prática o funcionamento da solução.

---

## 👨‍💻 Autor

Projeto desenvolvido por **Maick Vieira Alves** como trabalho acadêmico para estudo de programação concorrente.
