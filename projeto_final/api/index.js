// Importação
const express = require('express');
const AuthRoutes = require('./routes/AuthRoutes');

// Configuração
const app = express();
const PORT = process.env.PORT || 4000;
app.use(express.json())

// Main
app.use(AuthRoutes)

// Listen
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta: ${PORT}`)
});