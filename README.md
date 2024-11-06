<!-- title -->
<h1 align="center">
    <span>Gerador de orçamentos</span>
</h1>

<br>

<!-- badges -->
<div align="left">
    <img src="https://img.shields.io/badge/license-MIT-yellow" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/version-1.0-green" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/repo size-1.8 MB-orange" alt="badge icon"></img>
</div>

<br>

<!-- About -->
## <img src="https://cdn2.iconfinder.com/data/icons/flat-pack-1/64/Computer-512.png" alt="todo list image icon" width="40px" align="center"> Sobre o Projeto

Essa aplicação automatiza a criação e envio de orçamentos, tornando o processo rápido e eficiente. 
Funciona assim:

<br>

- Através de uma rota POST, é feita uma requisição com os dados necessários para o orçamento.

    ![](./readme_images/request_example.png)

<br>

- Depois de validado os dados, é criado um arquivo Excel (.xlsm) detalhado, com base em um modelo já existente.

    ![](./readme_images/image_orcamento_example.png)


- A partir desse arquivo Excel, é gerado um PDF pronto para envio.

<br>

- Esse PDF é enviado diretamente para o WhatsApp da pessoa desejada.

    ![](./readme_images/whatsapp_example.png)

<hr>
<br>

<!-- Technologies e dependencias -->
## <img src="https://cdn4.iconfinder.com/data/icons/general-office/91/General_Office_48-256.png" alt="todo list image icon" width="40px" align="center"> Tecnologias e dependencias
- Java 17
- Spring Boot 3.3.5
- Spring Web
- Apache POI --> Usado para manipular arquivos xlsx (orcamentos)
- selenium --> Dependencia que simula o navegador. Assim, conseguimos enviar o pdf para o whatsapp.
- OpenAPI (Swagger)


<hr>
<br>

<!-- Diagram -->
### <img src="https://cdn3.iconfinder.com/data/icons/web-design-development-flat-colors/48/flow_chart-512.png" alt ="image icon" width="40px" align="center"> Diagrama de relacionamento

A aplicação trabalha com 2 entidades:

- Orcamento
- Item

<br>

![](./readme_images/diagrama.png)


<hr>
<br>

<!-- Validations -->
## <img src="https://cdn4.iconfinder.com/data/icons/rating-validation-3/128/validation_stamp_approval_approve_check-512.png" alt ="image icon" width="40px" align="center"> Validações


*Exempo do Objeto JSON enviado na requisicao*
```json
{
    "cliente" : "Naruto",
    "descricao" : "Serviço marcenaria",
    "observacoes": null,
    "items": [
        {
            "descricao": "quarto sasuke",
            "acabamento": "MDF natural oak",
            "medidas": "110 x 46 x 75",
            "valorUnit": 500,
            "quantity": 1
        },
        {
            "descricao": "Bancada quarto naruto",
            "acabamento": "MDF natural oak",
            "medidas": "110 x 46 x 75",
            "valorUnit": 55,
            "quantity": 1
        },
        {
            "descricao": "Painel sala naruto",
            "acabamento": "MDF natural oak",
            "medidas": "110 x 46 x 75",
            "valorUnit": 2300,
            "quantity": 1
        }
    ],
    "prazoEntrega": 30,
    "sinal": null,
    "parcelas": null
}
```


Esses campos seguem as seguintes validacoes:


### orcamento
- **cliente**
    - Não pode ser null
    - Deve possuir entre 5 e 30 characters

- **descricao**
    - Não pode ser null
    - Deve possuir entre 5 e 45 characters

- **observacoes**
    - Deve possuir entre 5 e 200 characters

- **descricao**
    - Não pode ser null
    - Deve possuir entre 5 e 45 characters

- **prazoEntrega**
    - Deve ser maior que 0

- **sinal**
    - Deve ser maior que R$ 1.000,00
    - Se preenchido, o campo 'parcelas' deve ser preenchido também

- **parcelas**
    - Deve ser maior que 1
    - Se preenchido, o campo 'sinal' deve ser preenchido também

<br>

### item

- **descricao**
    - Não pode ser null
    - Deve possuir entre 5 e 80 characters

- **acabamento**
    - Não pode ser null
    - Deve possuir entre 5 e 80 characters

- **medidas**
    - Deve possuir entre 5 e 80 characters

- **valorUnit**
    - Não pode ser null
    - Deve possuir ser maior que 0

- **quantity**
    - Não pode ser null
    - Deve possuir ser maior que 0

<hr>
<br>

<!-- Custom Exception Handler -->
## <img src="https://cdn4.iconfinder.com/data/icons/common-app-symbols-round-colored/1024/caveat_proviso_disclaimer_exception_app_round_colored-512.png" alt ="image icon" width="40px" align="center"> Lançamento de exceções customizadas

Todas as exceções foram personalizadas para um melhor entendimento do usuário

**Exemplo 1**

![exception example 1](./readme_images/validacao_example1.png)	
	
<br>

**Exemplo 2**

![exception example 2](./readme_images/validacao_example2.png)	

<hr>
<br>

## Documentação no Swagger

![swagger image example](./readme_images/swagger_example.png)	

<hr>
<br>

## <img src="https://cdn1.iconfinder.com/data/icons/internet-45/64/http-link-internet-domain-1024.png" alt ="image icon" width="40px" align="center"> Endpoints

| Método Http | URI |  Descrição | Status Code esperado |                  
| :---:       | :--- |  :---     | :---:                |
| POST        | `http://localhost:8080/orcamento`| Criar e enviar orcamento    | 201 |

<hr>
<br>

<!-- Build and run -->
## <img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/rocket-256.png" alt="todo list image icon" width="40px" align="center"> Rodando a aplicação

### Requisitos

- java 17

<br>

### Passo a passo

1. Clone esse repositório
    ```bash
    git clone https://github.com/lGabrielDev/gerador_orcamentos
    ```
<br>

2. Vá ao diretorio

    ```bash
    cd gerador_orcamentos
    ```

<br>

3. Atribua os valores das variáveis no aplication.properties

    <img alt="environment variables image example" src="./readme_images/properties_image.png" width="750px">

    <br>

    - **REPO_PATH** --> Local da sua máquina onde você salvou esse repositorio

    - **YOUR_CHROME_PROFILE** --> Seu perfil do navegador google chrome. Informando o perfil, você só irá precisar autenticar o WhatsApp apenas 1 vez. Para encontrar seu perfil, abra o navegador e digite `chrome:version`

    - **PHONE_NUMBER** --> Numero da pessoa que voce quer enviar o orcamento. DDD + Numero `XXxxxxx-xxxx`


<br>

4.  Rode a aplicação e crie seu orcamento:

    `http://localhost:8080/swagger.html`

<hr>
<br>


<!-- Credits -->
<h2>
    <img src="https://cdn4.iconfinder.com/data/icons/thank-you/256/Artboard_4_copy-512.png" alt="thumbs up icon image" width="40px" align="center">
    <span>Créditos</span>
</h2>

<p>As imagens usadas nesse projeto foram retiradas dos seguintes sites:</p>

- [shields.io](https://shields.io/)
- [iconfinder](https://www.iconfinder.com/)
- [storyset](https://storyset.com/)
- [vecteezy](https://www.vecteezy.com)
- [svgrepo](https://www.svgrepo.com)

<br>

<span>Thanks!</span>

<hr>
<br>


<!-- License -->
## <img src="https://cdn4.iconfinder.com/data/icons/jetflat-2-multimedia-vol-3/60/0042_049_license_agreement_file_document_paper_page_sheet-512.png" alt="todo list image icon" width="40px" align="center"> Licença --> MIT

O projeto está sob a licença do [MIT](./LICENSE).

<hr>
<br>

<!-- Author -->
## <img src="https://cdn1.iconfinder.com/data/icons/office-work-3/200/copywriting-512.png" alt="todo list image icon" width="40px" align="center"> Autor

<br>

<div align="center">
    <img src="./readme_images/avatar_circular.png" alt="profile avatar" width="150px">
    <p> <a href="https://github.com/lGabrielDev">Gabriel Freitas</a> 😎 </p>
</div>