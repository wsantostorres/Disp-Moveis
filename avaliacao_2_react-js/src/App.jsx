import Input from "./components/Input"

function App() {

  const profissoes = [
    {
      value: 1,
      nome: 'Programador'
    },
    {
      value: 2,
      nome: 'Analista de Negócios'
    }
  ]

  const idiomas = [
    {
      id: 'ingles',
      label: 'Inglês',
      value: 'ingles'
    },
    {
      id: 'espanhol',
      label: 'Espanhol',
      value: 'espanhol'
    },
    {
      id: 'frances',
      label: 'Francês',
      value: 'frances'
    },
    {
      id: 'alemao',
      label: 'Alemão',
      value: 'alemao'
    }
  ]

  const sexos = [
    {
      id: 'masculino',
      label: 'M',
      value: 'masculino'
    },
    {
      id:'feminino',
      label: 'F',
      value: 'feminino'
    }
  ]

  function phoneMask(event){
    let input = event.target
    if (!input.value) return ""
    input.value = input.value.replace(/\D/g,'')
    input.value = input.value.replace(/(\d{2})(\d)/,"($1) $2")
    input.value = input.value.replace(/(\d)(\d{4})$/,"$1-$2")
    return input.value
  }

  return (
    <div>
      <Input name="nome" tipo="text" placeholder="Digite seu nome..." label="Nome"/>
      <Input name="telefone" placeholder="(xx) xxxxx-xxxx" tipo="text" label="Telefone" mask={phoneMask} maxLength={15}/>
      <Input name="profissao" tipo="select" label="Profissão" options={profissoes}/>
      <Input tipo="radio" name="sexo" label="Sexo" options={sexos}/>
      <Input tipo="checkbox" name="idiomas" label="Idiomas" options={idiomas}/>
    </div>
  )
}

export default App
