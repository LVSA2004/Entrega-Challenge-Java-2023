<h1 align="center">Projeto Baymax</h1>

<h1 align="center">Por motivos de segurança, a OpenAI destrói as chaves de API que são colocadas na internet e por isso nosso projeto não consta uma chave de API</h1>
<h2> Vídeo da API funcionando:https://youtu.be/b7Yxvw2rgec </h2>

<h2> API RESTful criada para gerar dietas e treinos usando o chatgpt e com capacidade de cadastro e login, tanto de usuários como médicos para a validação dos prompts gerados pelo chatGPT</h2>

<h2 align="center">🛠 Arquitetura do Projeto 🛠</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/946468431794954250/1109862708133777469/Mapa_Mental_com_brainstorm_escrito_a_mao_colorido.png"/>
</div>

<h2 align="center">🛠 Futura Implementação da Nuvem🛠</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/946468431794954250/1109516890503651378/Mapa_Mental_com_brainstorm_escrito_a_mao_colorido_1.png"/>
</div>

<h2 align="center">📊 TOGAF 📊</h2>
<div align="center">
    <img height src="https://cdn.discordapp.com/attachments/946468431794954250/1109524825988399276/image.png"/>
</div>

<h2 align="center">Endpoints 📋</h2>

### Cadastro com Autenticação e Token ╹Cliente╷ **`/cliente/registrar`**:

#### POST ➡️

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

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
✔️ | `201` | Perfil cadastrado com sucesso.
❌ | `403` | Não foi possível cadastrar o perfil.

### Cadastro com Autenticação e Token ╹Médico╷ **`/medico/registrar`**:

#### POST ➡️

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

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `201` | Perfil cadastrado com sucesso.
| ❌ | `403` | Não foi possível cadastrar o perfil.

### Login com validação de Token ╹Cliente╷ **`/cliente/login`**:

#### POST ➡️

**Exemplo 👇**
```js
{
	"email": "lvssfiap@gmail.com",
	"senha": "|(:oUuC<UZ"
}
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Login validado com sucesso.
| ❌ | `403` | Não foi possivel validar o login.

### Login com validação de Token ╹Médico╷ **`/medico/login`**:

#### POST ➡️

**Exemplo 👇**
```js
{
	"email": "lvsi2571@gmail.com",
	"senha": "o2ecohTEXl"
}
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Login validado com sucesso.
| ❌ | `403` | Não foi possivel validar o login.

### Cadastro ╹Cliente╷ **`/baymax/cliente`**:

#### POST ➡️

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

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Cadastro feito com sucesso.
| ❌ | `403` | Não foi realizar o cadastro.

### Cadastro ╹Médico╷ **`/baymax/medico`**:

#### POST ➡️

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

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Cadastro feito com sucesso.
| ❌ | `403` | Não foi realizar o cadastro.

### Pesquisa Por ID ╹Cliente╷ **`/baymax/cliente/pesquisa/{id}`**:

#### GET ⬅️

**Exemplo 👇**
```js
http://localhost:8080/baymax/cliente/pesquisa/1
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Cliente com o id {id} encontrado.
| ❌ | `404` | Cliente com o id {id} não foi encontrado.

### Pesquisa por ID ╹Médico╷ **`/baymax/medico/pesquisa/{id}`**:

#### GET ⬅️

**Exemplo 👇**
```js
http://localhost:8080/baymax/medico/pesquisa/1
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Medico com o id {id} encontrado.
| ❌ | `404` | Medico com o id {id} não foi encontrado.

### Atualização de Cadastro ╹Cliente╷ **`/baymax/cliente/{id}`**:

#### PUT 🔄

**Exemplo de cadastro 👇**
```js
	"nome": "Luan Sá",
	"email": "lvssfiap@gmail.com",
	"senha": "|(:oUuC<UZ",
	"cpf":"535.710.748-96",
	"telefone":"(11) 95954-0882"
```
** Exemplo de Alteração 👇**

```js
	"nome": "Luan Victor",
	"email": "lvss.fiap@fiap.com.br",
	"senha": "|(:oUuC<UZ",
	"cpf":"525.610.658-96",
	"telefone":"(11) 95954-0882"
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Alteração feita com sucesso.
| ❌ | `404` | Cliente com o id {id} não foi encontrado.

### Atualização de Cadastro ╹Médico╷ **`/baymax/medico/{id}`**:

#### PUT 🔄

**Exemplo de Cadastro👇**
```js
	"nome": "Luiza Ferreira",
	"email": "lvsi2571@gmail.com",
	"senha": "o2ecohTEXl",
	"crm": "515730-6",
	"afiliacao": "Associação Paulista de Medicina",
	"telefone": "(13) 93189-40862"
```

**Exemplo de Alteração👇**
```js
	"nome": "Luiza da Silva",
	"email": "lvsi@hotmail.com",
	"senha": "o2ecohTEXl",
	"crm": "515730-6",
	"afiliacao": "Associação Mineira de Medicina",
	"telefone": "(13) 93189-40862"
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `200` | Alteração feita com sucesso.
| ❌ | `404` | Medico com o id {id} não foi encontrado.

### Deletar Cadastro ╹Cliente╷ **`/baymax/cliente/{id}`**:

#### DELETE ⬇️

**Exemplo 👇**
```js
http://localhost:8080/baymax/cliente/pesquisa/1
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `204` | Cliente deletado com sucesso.
| ❌ | `404` | Cliente com o id {id} não foi encontrado.

### Deletar Cadastro ╹Médico╷ **`/baymax/medico/{id}`**:

#### DELETE ⬇️

**Exemplo 👇**
```js
http://localhost:8080/baymax/medico/pesquisa/1
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `204` | Medico deletado com sucesso.
| ❌ | `404` | Medico com o id {id} não foi encontrado.

### Mandar prompt para o ChatGPT ╹ChatGPT╷ **`/baymax/chatbot/api`**:

#### POST ➡️

**Exemplo 👇**
```js
{
	"pergunta": "Gere uma dieta para um Homem de 19 anos, 1.75cm de altura, 85 kg, que não prática nenhuma atividade física",
}
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
✔️ | `201` | Prompt inserido com sucesso.
❌ | `403` | Não foi possívelinserir o prompt.

### Deletar Prompt ╹ChatGPT╷ **`/baymax/chatbot/{id}`**:

#### DELETE ⬇️

**Exemplo 👇**
```js
http://localhost:8080/baymax/chatbot/1
```

**Saída 👇**

|  | <font color="#aa31f5">código</font> | <font color="#e0af0d">descrição</font> |
|:------:|:------:|-----------|
| ✔️ | `204` | Prompt deletado com sucesso.
| ❌ | `404` | Medico com o id {id} não foi encontrado.


