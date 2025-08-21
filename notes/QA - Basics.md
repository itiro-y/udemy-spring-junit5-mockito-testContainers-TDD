## Testes Unitários

- é um pequeno método que você escreve para testar alguma parte do seu código
- cada método possui 3+ testes unitários
- caso o teste foi sucedido - teste será verde
- caso o teste falhe - teste será vermelho
- JUnit 5
	- fornece api para implementar os testes

- exemplo

Metodo que iremos testar:
``` Java
public class SimpleMath{
	public Double sum(Double firstNum, Double secondNum){
		return firstNum + secondNum;
	}
}
```

Metodo que realiza o teste
``` Java
@Test
void testSum_WhenSixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo(){
	// Given or Arrange
	SimpleMath math = new SimpleMath();

	// When or Act
	Double actual = math.sum(6.2D, 2D);

	// Then or Assert
	assertEquals(8.2D, actual, () -> "6.2 + 2 did not produce 8.2!");
}
```

- Test Reports
	- Verde -> todos os testes passaram
	- Vermelho -> um ou mais testes falharam

- Objetos fakes
	- Simulam mocks para um cenario real

- 3 Testes Unitários (ex)
	1. Todos os parâmetros são válidos
	2. Um dos parâmetros tem um valor inválido
	3. Resposta inválida do HTTP Client

## Por que implementar testes unitários?

- Ter certeza de que o código funcioona
- O código funciona tanto com parâmetros válidos quanto inválidos
- O código funciona hoje e vai continuar funcionando no futuro
- O código continuará funcionando inclusive depois que fizermos mudanças


## O Princípio F.I.R.S.T

- Fast - os testes unitários devem executar rapidamente
- Independent - os testes unitários devem ser independentes uns dos outros
- Repeatable - os testes unitários devem ser repetíveis
- Self-validating - os testes unitários devem se auto validarem
- Through & Timely - os testes unitários devem cobrir casos extremos

## Testando Código em Isolamento

- Classe A
	- Método A
		- Teste Unitário
			- Cria uma instância do método A
				- Mas o método A chama metodos de outras classes
	- Método B

- Dito isso devemos encontrar maneiras de isolar o método A sem ser dependente de outras métodos de outras classes
- Para isso usamos a Injeção de Dependências
	- Objeto A
		- Objeto B
		- Objeto C
		
	- Faremos o contrário
	
	- Objeto B (Mock)
		- Objeto A
	- Objeto C (Mock)
		- Objeto A

 - exemplo

Injeção de Dependência
``` Java
public void checkout(Cart cart){
	PayoutProcessor payoutProcessor = new PayoutProcessor(); // ERRADO
	payoutProcessor.processPayment(cart);
}

//---

PayoutProcessor payoutProcessor;
// No construtor
public CartCheckout(PayoutProcessor payoutProcessor){ // CERTO
	this.payoutProcessor = payoutProcessor
}

//---
// Passando como argumento no metodo
public void checkout(Cart cart, PayoutProcessor payoutProcessor){ // CERTO
	payoutProcessor.processPayment(cart);
}

```
## A Pirâmide de Testes

Topo - End-to-end Testing / UI Testing
- Testes da funcionalidade de software de ponta a ponta
Meio - Integration Tests
- O código da aplicação é testado sem mockar acessos ao banco de dados e/ou conexões HTTP
Base - Unit Tests
- Testes de pequenos trechos de código isolados com dependências stubs ou mockadas

## O que é JUnit 5

- Framework de testes que nos permite implementar testes unitários
- Uma combinação de 3 coisas
	- JUnit Platform (Roda os testes)
		- é o principal framework para testes na JVM, sendo a base que fundamenta a maioria das novas ferramentas de testes
	- JUnit Jupiter (Implementa os testes)
		- é a combinação do novo modelo de programação e extensão para escrever testes e extensões para o JUnit 5
	- JUnit Vintage (Rodar JUnit 3 e 4)
		- TestEngine de backward compatibility para a execução de testes escritos em JUnit 3 e JUnit 4

## Maven

- importar para o pom.xml
	- jupiter api
	- jupiter engine
	- jupiter params
- adicionar \<plugin> do maven surefire-plugin para realizar testes ao dar $ mvn package

## Nomenclatura de Testes Unitários

- test\[System Under Test]\_\[Condition or State Change]\_\[Expected Result]
- melhor usar @DisplayName()

## Behiviour-driven development (BDD)

- BDD Style
	- Given
	- When
	- Then

	- Arrange
	- Act 
	- Assert

## Test Method Template

``` Java
// test[System Under Test]_[Condition or State Change]_[Expected Result]
@DisplayName("Display Name")
@Test
void testABCD_when_XYZ_Should() {
	// Given / Arrange
	// When / Act
	// Then / Assert
}
```

## Ciclo de Vida dos Testes no JUnit 5

- Test Class
	- Test Method 1
	- Test Method 2
	- Test Method 3

- JUnit vai criar uma intancia de classe pra cada metodo com @Test
- A ordem de criação é aleatória

- O metodo com @BeforeAll sera executado antes de todos os testes unitarios @Test
	- Normalmente usado como setup()
	- Ex. Metodo para criar o banco de dados
- O metodo com @AfterAll sera executado despois de todos os testes unitarios @Test
	- Normalmente usado como cleanup()
	- Ex. excluir o banco de dados criado pelo before-all
- O metodo com @BeforeEach sera executado antes de cada teste unitario @Test
	- Normalmente usado para fins de configuração
	- Ex. Se todos os metodos @Test irao iniciar um objeto, faz sentido inicializa-lo no @BeforeEach, para evitar repetição
- O  metodo com @AfterEach sera executado apos cada teste unitario @Test
	- Normalmente usado para fins de limpeza
	- Ex. fechar a conexão com o banco de dados

- Ciclo da Classe de Testes
1. @BeforeAll
2. @BeforeEach
3. @Test
4. @AfterEach
5. @AfterAll

## Conceitos Avançados de JUnit 5

- @ParametereziedTest
	- @MethodSource
	- @CsvSource
	- @CsvSourceFile
	- @ValueSource
- @RepeatedTest
- @TestMethodOrder
	- Random
	- ByName
	- ByIndex
- @TestClassOrder
- Ciclo de vida do JUnit
	- Alterando o ciclo de vida com @TestInstance

## Test Driven Development (TDD)

- Metodologia para escrita de codigo -> codifica o Teste antes do código existir

1. RED -> Write a test, watch it fail
2. GREEN -> Write just enough, code to pass the test
3. BLUE/REFACTOR -> Improve the code without changing its behaviour
4. REPEAT

## Mockito

- https://site.mockito.org/
- framework de test e spy
- simular a instancia de classes e comportamentos de metodos
- configurar o retorno e as ações de acordo com o teste
- principais funções
	- mock -> criar uma instância de uma classe mockada
		- ao chamar um método, ele irá chamar um método fake
	- spy -> criar uma instância da classe que voce pode mockar ou chamar os métodos reais
		- alternativa do inject mocks
	- inject mocks -> cria uma instância e injeta as dependências necessárias anotadas com @mock
	- verify -> verifica a quantidade de vezes e quais parametros utilizados foram para acessar um determinado método
	- when -> após um mock ser criado,  você pode configurar as ações da chamada e qual retorno receberá
	- matchers -> permite a verificação por meio de matchers os argumentos "any object", "any string" ...


## Relatórios de Testes usando Surefire Report Plugin

- instalar plugin do Surefire Report no pom.xml
```
<plugin>  
  <groupId>org.apache.maven.plugins</groupId>  
  <artifactId>maven-surefire-report-plugin</artifactId>  
  <version>3.5.3</version>  
  <executions>    
	  <execution>      <phase>test</phase>  
	      <goals>          
		      <goal>report</goal>  
	      </goals>    
      </execution>  
  </executions>
</plugin>
```

- adicionar no plugin do surefire plugin
```
<plugin>  
  <groupId>org.apache.maven.plugins</groupId>  
  <artifactId>maven-surefire-plugin</artifactId>  
  <version>3.5.3</version>  
  <configuration>
      <testFailureIgnore>true</testFailureIgnore>  
  </configuration>
</plugin>
```

- rodar no terminar usando mvn clean test ou mvn test
- report dos testes em html está no target/reports

## JaCoCo - Java Code Coverage

- adicionar no pom
```
<plugin>  
	<groupId>org.jacoco</groupId>  
	<artifactId>jacoco-maven-plugin</artifactId>  
	<version>0.8.13</version>  
  
	<executions>    
		<execution>      
			<id>prepare-agent</id>  
			<goals>        
				<goal>prepare-agent</goal>  
			</goals>
			</execution>  
		<execution>
			<id>report</id>  
			<phase>test</phase>  
			<goals>
				<goal>report</goal>  
			</goals>
		</execution>
	</executions>  
</plugin>
```





