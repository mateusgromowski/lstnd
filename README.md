# lstnd.

Aplicação web para buscar álbuns musicais via API do Spotify e deixar avaliações (reviews com nota de 1 a 5 estrelas e comentário). Pense em algo no estilo Letterboxd, só que para álbuns.

## Funcionalidades

- Busca de álbuns por nome usando a API do Spotify
- Página de detalhes do álbum com capa, artista e ano de lançamento
- Criação de reviews com nome de usuário, nota (1-5 estrelas) e texto
- Listagem de todas as reviews de um álbum, das mais recentes para as mais antigas
- Autenticação com o Spotify feita no back-end via Client Credentials Flow, sem expor as credenciais no front-end

## Tecnologias

**Back-end**
- Java 21
- Spring Boot 4.1.0
- Spring Web (RestClient para consumir a API do Spotify)
- Spring Data JPA
- H2 Database (em memória)
- Lombok

**Front-end**
- HTML, CSS e JavaScript puro (vanilla)

## Estrutura do projeto

```
src/main/java/com/lstnd/lstnd/
├── config/          # Configuração dos clientes REST (auth e requisições ao Spotify)
├── controller/       # Endpoints REST ( ReviewController, AlbumController)
├── service/           # Regras de negócio (SpotifyService, ReviewService, AlbumService)
├── repository/       # Acesso a dados (ReviewRepository)
├── model/             # Entidades e modelos de domínio (Album, Review)
├── DTO/                # Objetos de transferência de dados
└── exception/         # Exceções customizadas

src/main/resources/
├── static/             # Front-end (index.html, review.html, script.js, review.js, styles.css)
└── application.properties
```

## Como rodar o projeto

### Pré-requisitos

- Java 21
- Uma conta de desenvolvedor no [Spotify for Developers](https://developer.spotify.com/) com um app criado, para obter `client_id` e `client_secret`

### Configuração

O projeto lê as credenciais do Spotify a partir de variáveis de ambiente:

```
CLIENT_ID=seu_client_id_do_spotify
CLIENT_SECRET=seu_client_secret_do_spotify
```

Defina essas variáveis antes de subir a aplicação. Opcionalmente, a porta do servidor pode ser configurada pela variável `PORT` (padrão: `8080`).

### Executando

Usando o Maven Wrapper incluído no projeto:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

O console do H2 fica disponível em `http://localhost:8080/h2-console`, apontando para o banco em memória `jdbc:h2:mem:testdb`.

## Endpoints da API

### Spotify

| Método | Rota                     | Descrição                          |
|--------|---------------------------|--------------------------------------|
| GET    | `/albums?name=`           | Busca álbuns pelo nome               |
| GET    | `/albums/{id}`    | Retorna detalhes de um álbum pelo ID |

### Reviews

| Método | Rota                          | Descrição                                    |
|--------|--------------------------------|-------------------------------------------------|
| GET    | `/reviews/{spotifyId}`         | Retorna uma review de um álbum                  |
| GET    | `/reviews?spotifyId=`    | Lista todas as reviews de um álbum (mais recentes primeiro) |
| POST   | `/reviews/{spotifyId}`         | Cria uma nova review para um álbum              |

## Observações

- O banco de dados H2 é em memória: os dados de reviews são perdidos a cada reinicialização da aplicação.
- Este é um projeto em desenvolvimento; endpoints e estrutura podem mudar.

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

Mateus B. Gromowski
