# Expedição na Montanha

Aplicativo Android de catálogo de trilhas para explorar percursos, acompanhar conquistas e consultar informações de cada caminhada.

## Telas e navegação

- **Splash:** exibe a identidade do app e encaminha automaticamente para Login ou para as Trilhas conforme o estado da sessão.
- **Login:** entrada demonstrativa que abre o painel principal.
- **Trilhas:** lista percorrível, estado vazio com opção para restaurar os itens e acesso aos detalhes de cada trilha.
- **Progresso:** resumo das trilhas conquistadas e da distância percorrida.
- **Perfil:** resumo do explorador e opção para sair.
- **Detalhe da trilha:** mostra local, dificuldade, distância, desnível, duração e descrição; permite marcar a conquista.

O painel tem três abas. A navegação entre abas usa `saveState`, `restoreState` e `launchSingleTop` para restaurar o estado da lista. As rotas são objetos tipados Kotlin Serialization; a tela de detalhe recebe o identificador em `Rota.DetalheTrilha`. Um único `ExpedicaoViewModel`, criado pelo host do `NavHost`, é compartilhado com as telas.

## Como abrir e executar no Android Studio

1. Instale o Android Studio e o Android SDK Platform 35.
2. No Android Studio, escolha **File > Open** e selecione a pasta deste projeto (a pasta que contém `settings.gradle.kts`).
3. Aguarde a sincronização do Gradle. Se solicitado, instale o Android SDK ou aceite a configuração sugerida pelo Android Studio.
4. Selecione um emulador Android ou conecte um dispositivo com depuração USB ativada.
5. Clique em **Run** (▶) para compilar e iniciar o app.

O botão **Entrar** é demonstrativo: não precisa de credenciais. A sessão permanece ativa enquanto o processo do app estiver aberto; ao sair, o app retorna para Login.
