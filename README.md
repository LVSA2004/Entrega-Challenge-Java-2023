<h1 align="center">Projeto Baymax</h1>

<h2> API RESTful criada para gerar deitas e treinos usando o chatgpt e com capacidade de cadastro e login, tanto de usuários como médicos para a validação dos prompts gerados pelo chatGPT</h2>

<h2 align="center">🛠 Arquitetura do Projeto 🛠</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/946468431794954250/1109516890503651378/Mapa_Mental_com_brainstorm_escrito_a_mao_colorido.png"/>
</div>

<h2 align="center">📊 TOGAF 📈</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/946468431794954250/1109524825988399276/image.png"/>
</div>

<h2 align="center">Endpoints 📋</h2>

### Cadastro com Autenticação e Token **`/cliente/registrar`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"nome": "Luan Sá",
	"email": "lvssfiap@gmail.com",
	"senha": "|(:oUuC<UZ",
	"cpf":"535.710.748-96",
	"telefone":"(11) 95954-0882"
}
```

### Cadastro com Autenticação e Token **`/medico/registrar`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"nome": "Luiza Ferreira",
	"email": "lvsi2571@gmail.com",
	"senha": "o2ecohTEXl",
	"crm": "515730-6",
	"afiliacao": "Associação Paulista de Medicina",
	"telefone": "(13) 93189-40862"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `201` | Perfil cadastrado com sucesso.
| `403` | Não foi possível cadastrar o perfil.

### Login com validação de Token **`/cliente/login`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"email": "lvssfiap@gmail.com",
	"senha": "|(:oUuC<UZ"
}
```

### Login com validação de Token **`/medico/login`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"email": "lvsi2571@gmail.com",
	"senha": "o2ecohTEXl"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Login validado com sucesso.
| `403` | Não foi possivel validar o login.

### Cadastro **`/baymax/cliente`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"nome": "André Santi",
	"email": "santificado@gmail.com",
	"senha": "()faG(Ix40",
	"cpf":"168.384.445-93",
	"telefone":"(19) 92483-9220"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Cadastro feito com sucesso.
| `403` | Não foi realizar o cadastro.

### Cadastro **`/baymax/medico`**:

#### POST 📬

**Exemplo 👇**
```js
{
	"nome": "Fernando de Sá",
	"email": "fds@gmail.com",
	"senha": "JsXCaDule6",
	"crm": "027730-6",
	"afiliacao": "Associação Paulista de Medicina",
	"telefone": "(16) 93735-5312"
}
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Cadastro feito com sucesso.
| `403` | Não foi realizar o cadastro.

### Pesquisa Geral **`/baymax/cliente/pesquisa/{id}`**:

#### GET 📪

**Exemplo 👇**
```js
http://localhost:8080/baymax/cliente/pesquisa/1
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Cliente com o id {id} encontrado.
| `404` | Cliente com o id {id} não foi encontrado.

### Pesquisa por ID **`/baymax/medico/pesquisa/{id}`**:

#### GET 📪

**Exemplo 👇**
```js
http://localhost:8080/baymax/medico/pesquisa/1
```

**Respostas 👇**

| <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|-----------|
| `200` | Medico com o id {id} encontrado.
| `404` | Medico com o id {id} não foi encontrado.


