# Avaliador de famílias

- Gerar uma lista de pessoas aptas a ganhar uma casa popular com base em sua pontuação

### CONFIGURAÇÃO
 - java 11
 - spring 2.7.8
 - flyway
 - h2

### FLUXO
[avaliador-familia](./doc/avaliador-familia.png)


### DOCUMENTAÇÃO DA APLICAÇÃO
 - http://localhost:8080/swagger-ui/index.html
 - http://localhost:8080/v3/api-docs

### ACESSO A BASE H2
 - http://localhost:8080/h2-console

## RODANDO O PROJETO
 # via docker
   Rode o comando abaixo na pasta raiz do projeto, com isto ele criara a imagem docker.
   
      - sudo docker build . -t avaliador-familia:1.0.0
   Rodando a imagem criada

      - sudo docker run -it --rm --name avaliador-familia-api -p 8080:80 avaliador-familia:1.0.0
      
   
 # via terminal
    - ./mvnw clean package
    - java -jar './target/avaliador-familia-0.0.1.jar'


# OBSERVAÇÕES
 #### Os teste implementados, foram teste de integrações, por ser mais rapido o desenvolvimento, mas sabemos que para uma melhor performance da aplicação devemos usar na maioria das vezes testes unitários