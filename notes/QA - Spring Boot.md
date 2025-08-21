
## Camanda clássica de uma aplicação Spring MVC

- Controller Layer <-> Service Layer <-> Repositorio Layer <-> Banco de Dados

## Testes na Camada de Repositório

- @DataJpaTest
	- testar os componentes da camada de persistência, que configurarão automaticamente um banco de dados embarcado em memória para fins de teste
- A anotação @DataJpaTest não carrega outros beans do Spring (@Components, @Controller, @Service e beans anotados) no ApplicationContext -> o torna rápido e leve
- Por padrão, ele procura por classes @Entity e configura os repositórios do Spring Data JPA anotados com a anotação @Repository
- Por padrão, os testes anotados com @DataJpaTest são transacionais e são revertidos ao final de cada teste
- Para testar repositórios do Spring Data JPA ou qualquer outro componente relacionado ao JPA, o Spring Boot fornece a anotação @DataJpaTest

## Testes na Camada de Serviços

- @Mock e @InjectMocks
	- Quando queremos injetar um objeto mockado em outro objeto mockado, podemos usar a annotation @InjectMocks. Essa annotation cria um mock da classe e injeta os mocks que estão marcados com as annotations @Mock nela
	
``` java
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest{
	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonServices service
}
```


## Testes na Camanda de Controllers

- @WebMvcTest e @Mock @InjectMocks
- JsonPath Library
	- Uma DSL Java para leitura de documentos JSON
	- As expressões JsonPath sempre se referem a uma estrutura JSON da mesma forma que as expressões XPath são usadas em combinação com um documento XML. O "objeto membro raiz" no JsonPath é sempre referido como $, independentemente de ser um objeto ou um array

``` JSON
//JSON
{
	"firstName":"Ayrton",
	"lastName":"Yoshii",
	"email":"ayoshii@email.com"
}

//JsonPath Expressions
//onde $ -> root member de uma estrutura JSON, seja um objeto ou um array
$.firstName = "Ayrton",
$.lastName = "Yoshii",
$.email = "ayoshii@email.com"
```

- @WebMvcTest vs. @SpringBootTest
	- O Spring Boot fornece a anotação @WebMvcTest para testar controllers Spring MVC. Além disso, os testes são baseados em @WebMvcTest são mais rápidos, pois carregam apenas o controller especificado e suas dependências, sem carregar a aplicação inteira
	- O Spring Boot instancia apenas a camada web em vez de todo o application context. Em uma aplicação com vários controlers, você pode definir a instanciação de apenas um deles usando, por exemplo, @WebMvcTest(PersonController.class)
	- O Spring Boot também fornece a anotação @SpringBootTest para testes de integração. Essa anotação carrega um application context completo


## Testes de Integração em uma Aplicação Spring Boot

- Como o nome sugere, os testes de integração têm foco na integração de diferentes camadas da aplicação. Isso também significa que não há uso de mocks
- Basicamente, escrevemos testes de integração para testar um feature que pode evolver interação com múltiplos componentes
- Não iremos usar o Mockito
- Aqui usamos o @SpringBootTest
	- ele iniciará o application context completo, o que significa que podemos usar a anotação @Autowired para injetar qualquer bean detectado pelo component scan em nosso teste
	- ele inicia um servidor embarcado, cria um web environment e possibilita que os métodos @Test façam testes de integração
	- por padrão, o @SpringBootTest não inicia um servidor. Precisamos adicionar o atributo WebEnvironment para refinar ainda mais como nossos testes serão executados. Existem várias opções:
		- MOCK (padrão) -> Carrega um WebServerApplicationContext e fornece um web environment mockado
		- RANDOM_PORT -> Carrega um WebServerApplicationContext e fornece um web environment real. O servidor embarcado é inicado e exposto em uma porta aleatória. Esa opção deve ser usada para testes de integração
		- DEFINED_PORT -> Carrega um WebServerApplicationContext e fornece um web environment real
		- NONE -> Carrega um ApplicationContext usando o SpringApplication, mas não fornece nenhum web environment
- Não iremos usar o H2 para fazer o teste, pois o H2 não simula um banco de dados de verdade e isso causará diferenças nos testes em H2 e um banco como o MySQL
- Iremos usar um Testcontainer, que se basea no do Docker
- Não usamos REST-MVC, e sim REST-assured para fazer as requisições. Fazemos as requisões diretamente


## 