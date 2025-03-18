# HubSpot Integration API

# Pré-requisitos
### Conta no HubSpot
-    Link para criar conta de desenvolvimento: https://br.developers.hubspot.com/get-started
- Utilizar a opção de `Um aplicativo`
- Vincular sua conta de desenvolvedor como conta de teste e sandbox padrão
     Link: https://knowledge.hubspot.com/pt/account-management/set-up-a-hubspot-standard-sandbox-account
     LINK: https://br.developers.hubspot.com/docs/getting-started/account-types
- Vincular para o aplicativo criado as permissões de `crm.objects.contacts.read` e `crm.objects.contacts.write`
- Vincular a URL de redirecionamento para `http://localhost:8080/api/auth/callback`


2 - Docker e Docker Compose

## Visão Geral

Esta API foi desenvolvida em Java com Spring Boot para integração com o HubSpot, permitindo autenticação via OAuth 2.0, criação de contatos e recebimento de webhooks.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- Docker e Docker Compose

## Estrutura do Projeto

```
meetime-hubspotintegration/
│
├── docker-compose.yml
├── Dockerfile
├── src/
│   ├── main/java/br/com/meetime/hubspotintegration/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── entity/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── HubspotIntegrationApplication.java
│   └── resources/
│       └── application.properties
│       └── init.sql
├── pom.xml
└── README.md
```

## Configuração do Ambiente

1. **Clone o repositório**

   ```sh
   git clone https://github.com/rhuanabbud/meetime-hubspotintegration
   cd meetime-hubspotintegration
   ```

2. **Configure as variáveis de ambiente** no arquivo `application.properties`:

   ```properties
   hubspot.client.id=SEU_CLIENT_ID
   hubspot.client.secret=SEU_CLIENT_SECRET
   hubspot.redirect.uri=SEU_REDIRECT_URI
   spring.datasource.url=jdbc:mysql://db:3306/hubspot_db
   spring.datasource.username=root
   spring.datasource.password=root
   ```

3. **Suba o ambiente com Docker Compose**

    Execute a build do Docker Compose
   ```sh
   docker-compose build 
   ```
   Para subir de acordo com o arquivo docker-compose.yml
   ```sh
   docker-compose up
   ```
   
## Apos subir o ambiente fazer o passo a passo:
- Pegar a URL gerada pelo ngrok para configuração do webhook `http://localhost:4040/inspect/http`
- Para acessar o console do banco com phpmyadmin `http://localhost:8081`

## Endpoints Disponíveis

### 1. Autenticação OAuth 2.0

- **Gerar URL de autorização:**
  ```http
  GET /api/auth/authorize
  ```
- Response:
  ```json
  {"https://app.hubspot.com/oauth/authorize?client_id=YOUR_CLIENT_ID&scope=crm.objects.contacts.read%20crm.objects.contacts.write&redirect_uri=YOUR_REDIRECT_URI"}

### 2. CallBack
- **Na chamada da URL do retorno da autorização, já consta o redirect para URL de callback configurado no hubspot:**
  ```http
  GET /api/auth/callback?code=AUTHORIZATION_CODE
  ```
  **Body:** `{ "code": "codigo_recebido" }`
- `Retorna o {access_token}`
  

### 3. Criar Contato no HubSpot

- **Criar contato:**
  ```http
  POST /api/auth/create-contact
  Authorization: Bearer {access_token}
  ```
  **Body:** `{ "email": "teste@email.com", "firstname": "Nome", "lastname": "Sobrenome" }`
#### **Response:** Contato foi criado com sucesso!

### 4. Webhook para Criação de Contatos

- **Recebe eventos de criação de contatos:**
- **URL de retorno é configurada no HubSpot utilizando o ngrok:**
- **Para funcionar no ambiente de desenvolvimento corretamente 
    há nescessidade de deixar disponível o WebHook no HubSpot**
  ```http
  POST /api/webhook/contact-creation
  ```
  **Body:** Lista de contatos criados objeto JSON Serializado WebhookEvent
#### **Response:** Webhook recebido com sucesso!

## Contato

Para dúvidas ou sugestões, entre em contato pelo e-mail: `rhuan.abbud@hotmail.com`.

