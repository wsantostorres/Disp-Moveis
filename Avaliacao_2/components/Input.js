import { SafeAreaView, StyleSheet, Text } from "react-native"
import { TextInput } from "react-native-web"

const Input = ({ tipo, placeholder, label, }) => {
  return (
    <SafeAreaView>
        <Text style={styles.label}>{label}:</Text>
        <TextInput
            style={styles.input} 
            placeholder={placeholder}
        />
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
    label: {
        marginBottom: 4
    },
    input: {
        borderWidth: 1,
        borderRadius: 4,
        borderColor: '#aaa',
        padding: 10
    }
})

export default Input
