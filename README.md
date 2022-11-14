# mensageria
project mini-autorization mensageria

Spring boot + Kafka

### Documentação Swagger

http://localhost:8080/swagger-ui/index.html

### Solução para tratar concorrências

Sobre o desafio relacionado a solução de `concorrência` optei por construir uma api de mensageria em kafka.
Que tem como objetivo:
- Gerar de forma automática o número e a senha do cartão com um valor inicial de 500.00, partindo do pressuposto
  que a solicitação venho de um aplicativo de cartão feito pelo usuário da sessão.
- Receber solicitações de transação passando no corpo da requisição os dados como número do cartão, senha e o valor.

A solução foi pensada para tratar as requisições simultâneas como fila. Sendo que os produtores receberão de qualquer 
formas as requisições e ficará por conta da configuração uma partição 
para cada grupo e permitindo apenas uma fila de processamento. O kafka consumirar uma por uma e evitaremos a 
`concorrência`. 

### Como inicializar o projeto

Dentro do projeto se encontra uma pasta (docker), e dentro o docker-compose.yml com as configurações do services
zookeeper e o kafka, após criar as imagens e fazer o start dos containeres do kafka, inicie a aplicação spring boot
e utilize o swagger para visualizar os endpoints disponiveis.


