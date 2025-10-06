# 🧠 PÁ-PUM! — Nova Versão

Mais uma versão do **Pá-Pum!** 🎯

Este projeto é uma releitura simplificada do nosso principal site desenvolvido no terceiro período pela equipe **Midup**: o **MIDUP**.  
Desta vez, a aplicação foi recriada em **Java**, com **armazenamento em memória** e **três funcionalidades principais**:

- 🔐 **Login**  
- ✅ **Gerenciamento de Tarefas**  
- 📖 **Registro do Diário**

---

## 🧩 Padrões de Projeto Utilizados

O projeto foi desenvolvido com base em **4 padrões de projeto** e na ideia de uma **arquitetura MVC simplificada (Model-View-Controller)**, visando boa organização e reutilização do código.

### 1. MVC Simplificado
Separação entre as camadas de **modelo**, **visão** e **controle**, estruturando melhor a lógica da aplicação.

### 2. Template Method
Implementado através de uma **classe abstrata** que define o fluxo comum de listagem, permitindo que as subclasses (como *Diário* e *Tarefa*) implementem seus próprios detalhes.

### 3. Decorator
Aplicado para **simular o envio de notificações** de forma padronizada, permitindo adicionar comportamentos sem alterar a estrutura base.

### 4. Singleton
Garante que exista **apenas uma instância** responsável por gerenciar a **sessão do usuário** durante a execução da aplicação.

---

**by Midup** 💡

---

## 🔗 Recursos

- 📊 **Diagrama UML:** [Acessar no Google Drive](https://drive.google.com/file/d/12mWgD814bXS7H_RHhJLC_MGmb8pKmWAO/view?usp=sharing)  
- 📄 **Documentação Completa:** [Acessar no Google Docs](https://docs.google.com/document/d/1wWUDStFI5bvCTeBT6xgDjHypNDBVhSDeHLoo-AaxtSU/edit?usp=sharing)
