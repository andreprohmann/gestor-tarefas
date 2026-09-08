# Gestor de Tarefas

Projeto Java de exemplo para gestão de tarefas, com regras de negócio, notificações e persistência em SQLite.

## Visão geral

Este projeto simula um sistema simples de acompanhamento de atividades em um ambiente de desenvolvimento, onde cada tarefa pode passar por diferentes estágios e exige validações antes de ser concluída. A ideia principal é demonstrar o uso de alguns conceitos de arquitetura orientada a objetos, como:

- Entidades do domínio
- Regras de transição de status
- Serviços de aplicação
- Interfaces para abstrações
- Persistência em banco de dados
- Notificações

O sistema foi construído em Java 17 com Maven e usa SQLite como armazenamento local.

## Objetivo do projeto

O objetivo é gerenciar o ciclo de vida de tarefas, permitindo:

- criar tarefas
- atribuir responsável
- acompanhar o status atual
- registrar horas trabalhadas
- impedir a conclusão de tarefas sem apontamento de horas
- notificar o responsável quando a tarefa muda de status
- salvar e atualizar os dados em um banco SQLite

## Fluxo de funcionamento

O fluxo principal do sistema é ilustrado na classe `Main`:

1. Inicializa os serviços e dependências.
2. Cria um usuário e um projeto.
3. Cria uma tarefa.
4. Salva a tarefa no banco.
5. Tenta concluir a tarefa sem apontar nenhuma hora.
6. A regra de negócio bloqueia essa transição.
7. Move a tarefa para `FAZENDO`.
8. Registra horas na tarefa.
9. Conclui a tarefa com sucesso.

Esse fluxo deixa claro como as regras de negócio ficam centralizadas em serviços e validadores.

## Estrutura do projeto

```text
Gestor_terefas/
├── pom.xml
├── gestor_tarefas.db
├── README.md
└── src/
    └── main/
        └── java/
            └── org/example/
                ├── Main.java
                ├── Entidade/
                │   ├── ApontamentoHora.java
                │   ├── Projeto.java
                │   ├── StatusTarefas.java
                │   ├── Tarefa.java
                │   └── Usuario.java
                ├── Interface/
                │   ├── Inotificador.java
                │   ├── ItarefaRepositorio.java
                │   └── ItransicaoStatus.java
                ├── Repositorio/
                │   └── TarefasSQLite.java
                └── Servicos/
                    ├── ApontamentoTrabalho.java
                    ├── MovimentoTarefas.java
                    ├── NotificarEmaiil.java
                    └── RegraHorasApontadas.java
```

## Pacotes e responsabilidades

### Entidade

No pacote `org.example.Entidade` ficam as classes centrais do domínio:

- `Usuario`: representa a pessoa responsável ou executora da tarefa.
- `Projeto`: agrupa tarefas e possui um responsável.
- `Tarefa`: encapsula o título, status atual e apontamentos de horas.
- `ApontamentoHora`: armazena o tempo gasto e a descrição do trabalho.
- `StatusTarefas`: enum com os estados possíveis da tarefa.

A estrutura deixa a tarefa responsável por seu próprio estado e por regras simples relacionadas a ela, como impedir apontamento em tarefa concluída.

### Interface

As interfaces definem contratos que ajudam a manter o código desacoplado:

- `ItarefaRepositorio`: define o comportamento esperado para um repositório de tarefas.
- `Inotificador`: abstrai o mecanismo de envio de avisos.
- `ItransicaoStatus`: representa uma regra de validação para mudança de status.

Essas interfaces permitem trocar implementações sem alterar o restante do sistema.

### Repositório

A classe `TarefasSQLite` implementa a persistência usando JDBC e SQLite.

Ela é responsável por:

- criar a tabela `tarefas` quando necessário
- salvar uma nova tarefa
- atualizar o status de uma tarefa existente

O banco é salvo localmente no arquivo `gestor_tarefas.db`, na raiz do projeto.

### Serviços

Os serviços encapsulam a lógica de aplicação:

- `MovimentoTarefas`: gerencia a criação e movimentação das tarefas; executa regras, atualiza o banco e dispara notificações.
- `ApontamentoTrabalho`: valida e registra horas em uma tarefa.
- `RegraHorasApontadas`: bloqueia a conclusão de uma tarefa quando ela não possui apontamentos.
- `NotificarEmaiil`: simula o envio de e-mail para o usuário responsável.

Esse padrão separa regras de negócio de detalhes de infraestrutura, como banco e envio de e-mails.

## Enum de status

A enum `StatusTarefas` possui os seguintes valores:

- `LISTADO`: tarefa cadastrada, ainda não iniciada.
- `FAZENDO`: tarefa em andamento.
- `CONCLUIDO`: tarefa encerrada.

## Regra de negócio principal

A regra central do projeto é:

> Uma tarefa não pode ser concluída sem possuir ao menos um apontamento de horas.

Essa validação é aplicada pela classe `RegraHorasApontadas` antes da mudança de status.

## Tecnologias utilizadas

- Java 17
- Maven
- SQLite
- JDBC (`sqlite-jdbc`)

## Como executar

### Requisitos

- JDK 17+
- Maven
- IDE Java (recomendado: IntelliJ IDEA, Eclipse ou VS Code com suporte Java)

### Passos

1. Clone o repositório.
2. Entre na pasta do projeto.
3. Compile o projeto:

```bash
mvn clean compile
```

4. Execute a classe `org.example.Main` pela IDE.

A execução da `Main` demonstra o fluxo completo de cadastro, bloqueio por regra, registro de horas e conclusão da tarefa.

## Observações

- O projeto é um exemplo didático de arquitetura simples em Java.
- Ele foca em organização e separação de responsabilidades, em vez de um sistema completo de produção.
- O banco SQLite é criado automaticamente na primeira execução.

## Exemplo de cenário executado pelo projeto

O código simula este cenário:

- Usuário: André Prohmann
- Projeto: Sistema de Gestão
- Tarefa: Modelar Banco de Dados
- Status inicial: `LISTADO`
- Tentativa de conclusão sem horas: bloqueada
- Status alterado para `FAZENDO`
- Registro de 3,5 horas
- Conclusão da tarefa: aprovada

## Conclusão

Este projeto funciona como uma base para estudar conceitos fundamentais de desenvolvimento Java em camadas, incluindo:

- modelagem de domínio
- uso de interfaces
- regras de negócio
- persistência
- separação entre aplicação e infraestrutura

É uma solução pequena, clara e fácil de adaptar para projetos maiores e mais complexos.
