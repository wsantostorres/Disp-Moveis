import { StyleSheet, View } from 'react-native';
import Input from './components/Input';

/* 
  * Nome
  * Telefone
  * Sexo
  * Profissão
  * Idiomas
*/

export default function App() {
  return (
    <View style={styles.container}>
      <Input tipo="select" label="Nome" placeholder={"Digite seu nome"}/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
