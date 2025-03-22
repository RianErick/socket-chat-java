# Chat Simples com Sockets em Java

Este é um simples chat de linha de comando, implementado em Java, usando sockets para comunicação entre cliente e servidor. O servidor escuta por conexões de clientes e permite que múltiplos clientes se comuniquem entre si.

## Requisitos

- Java 8 ou superior

## Estrutura do Projeto

- `Server.java`: Implementação do servidor que aceita conexões de clientes e retransmite mensagens para todos os clientes conectados.
- `Client.java`: Implementação do cliente, que conecta ao servidor e envia/recebe mensagens
