# 🚀 GitHub Repo Analysis

Aplicação Spring Boot para análise de repositórios GitHub. :sparkles:

## 🛠️ Tecnologias

- **☕ Java 25**
- **🌱 Spring Boot** (Web, MVC)
- **🔗 OpenFeign** (Requisições de APIs)
- **📦 Maven** (gerenciamento de dependências)
- **🔧 Lombok** (redução de boilerplate)
- **🐙 GitHub API** (integração para dados de repositórios)

## 🌐 Endpoints

Todos os endpoints aceitam um corpo JSON com `owner` e `repo`

Exemplo:

```json
{
  "owner": "octocat",
  "repo": "Hello-World"
}
```

- `GET /api/repositories/overview`: Visão geral do repositório (nome, descrição, estrelas, etc.) 📊
- `GET /api/repositories/commits`: Estatísticas de commits (total, média por semana, contribuidores) 📈
- `GET /api/repositories/issues-prs`: Estatísticas de issues e PRs (totais, abertos/fechados) 🐛
- `GET /api/repositories/quality`: Verificação de qualidade (README, LICENSE, .gitignore) ✅

## ▶️ Como executar

1. Clone o repositório. 📥
2. Execute `./mvnw spring-boot:run`. 🚀
3. Acesse `http://localhost:8080`. 🌍
