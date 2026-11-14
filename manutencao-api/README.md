# API de Chamados de Manutenção

Em um prédio ou escritório, pedidos como “a luz do corredor queimou” podem se perder entre mensagens. Esta API organiza a fila e registra o que foi feito em cada atendimento.

## Fluxo de um chamado

Um chamado nasce **ABERTO**, passa para **EM_ATENDIMENTO** e termina **RESOLVIDO**. Ao resolver, é obrigatório descrever o serviço realizado. Pular uma etapa ou repetir uma transição retorna HTTP 409.

| Campo | Significado |
| --- | --- |
| `id` | Identificador gerado pelo banco |
| `titulo`, `local`, `descricao` | Informações para localizar e entender o problema |
| `status`, `abertoEm` | Situação e data de abertura |
| `resolucao`, `resolvidoEm` | Relato e data preenchidos no encerramento |

## Como rodar

Instale Java 21 e Maven. Execute na pasta que contém o `pom.xml`.

```powershell
mvn test
mvn spring-boot:run
```

Acesse o [Swagger UI](http://localhost:8080/swagger-ui/index.html) para experimentar as rotas ou abra o [contrato OpenAPI](http://localhost:8080/v3/api-docs). A aplicação roda em loopback e usa um arquivo H2 na pasta `data/` para manter os dados entre reinícios. Os testes de integração usam um banco H2 em memória.

## Rotas

| Método e rota | Resultado esperado |
| --- | --- |
| `POST /api/v1/chamados` | 201 com o chamado criado e cabeçalho `Location` |
| `GET /api/v1/chamados` | 200 com lista paginada. Aceita `status`, `pagina` e `tamanho` |
| `GET /api/v1/chamados/{id}` | 200 com os dados do chamado |
| `PATCH /api/v1/chamados/{id}/iniciar` | 200 com o status `EM_ATENDIMENTO` |
| `PATCH /api/v1/chamados/{id}/resolver` | 200 com o status `RESOLVIDO` |

A lista começa na página 0, retorna 20 itens por padrão e aceita até 100 por página. Os chamados mais recentes aparecem primeiro.

### Experimente no PowerShell

```powershell
$url = 'http://localhost:8080/api/v1/chamados'
$corpo = @{ titulo = 'Luz queimada'; local = 'Corredor'; descricao = 'A lampada do corredor apagou' } | ConvertTo-Json
$chamado = Invoke-RestMethod -Method Post -Uri $url -ContentType 'application/json' -Body $corpo

Invoke-RestMethod -Method Patch -Uri "$url/$($chamado.id)/iniciar"
$resolucao = @{ resolucao = 'Lampada trocada' } | ConvertTo-Json
Invoke-RestMethod -Method Patch -Uri "$url/$($chamado.id)/resolver" -ContentType 'application/json' -Body $resolucao
Invoke-RestMethod -Uri "${url}?status=RESOLVIDO"
```

Erros usam `ProblemDetail`. Entradas inválidas retornam 400, IDs inexistentes retornam 404 e conflitos de status ou atualizações concorrentes retornam 409.

## Arquitetura e testes

| Camada | Responsabilidade |
| --- | --- |
| `web` | Rotas REST, DTOs, validação e respostas de erro |
| `service` | Casos de uso e limites das transações |
| `domain` | Entidade `Chamado` e regras de transição |
| `repository` | Consultas e persistência com Spring Data JPA |

A entidade usa `@Version` para detectar alterações concorrentes. Os testes unitários usam JUnit 5 e Mockito para verificar o serviço. Os testes com MockMvc e H2 exercitam a API, a persistência e a documentação.

## Roteiro para continuar o projeto

1. **Domínio e persistência.** Leia `Chamado.java` e acompanhe a abertura até o `repository.save`.
2. **REST e erros.** Reproduza no Swagger uma abertura, uma tentativa de resolver cedo demais e a resolução após iniciar o atendimento.
3. **Testes.** Implemente um caso para tentar iniciar o mesmo chamado duas vezes. Verifique o 409 e confirme que o chamado permanece em atendimento.
4. **Segurança.** Em uma segunda versão, adicionar Spring Security com JWT. Separar solicitante, que cria e consulta os próprios chamados, de técnico, que atende a fila. Cobrir 401 sem token válido e 403 sem permissão.

Stack da versão inicial: Java 21, Spring Boot 3.5, Spring Data JPA, H2, Bean Validation, JUnit 5, Mockito e springdoc.
