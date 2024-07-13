import styles from "./Input.module.css"

const Input = ({ tipo = "text", name, label, placeholder, mask, maxLength, options }) => {

  return (
    <div className={styles.input_container}>

      { tipo === "select" && (
        <>
          <label className={styles.label}>{label}: </label>
          <select className={styles.input} name={name}>
            {   
              options.map((option) => (
                <option key={option.value} value={option.value}>{option.nome}</option>
              )) 
            }
          </select>
        </>
      ) }

      { tipo === "radio" && (
          <>
          <label className={styles.label}>{label}: </label>
        
          { options.map((option) => (
            <div key={option.value}>
              <input type="radio" name={name} value={option.value} id={option.id}/>
              <label htmlFor={option.id}>{option.label}</label>
            </div>
          )) }
        </>
       )
      }

      { tipo === "checkbox" && (
          <>
          <label className={styles.label}>{label}: </label>
        
          { options.map((option) => (
            <div key={option.value}>
              <input type="checkbox" name={name} value={option.value} id={option.id}/>
              <label htmlFor={option.id}>{option.label}</label>
            </div>
          )) }
        </>
       )
      }

      { tipo === "text" && (
        <>
          <label className={styles.label}>{label}: </label>

          { mask ? (
            <input className={styles.input}
                  name={name} 
                  type={tipo} 
                  placeholder={placeholder}
                  maxLength={maxLength}
                  onKeyUp={(e) => mask(e)}
            />
          ) : (
            <input className={styles.input}
                  name={name} 
                  type={tipo} 
                  placeholder={placeholder}
                  maxLength={maxLength}
            />
          ) }

        </>
      )}

    </div>
  )
}

export default Input
