# Let's Workout

Aplicativo de treinos local-first, com armazenamento IndexedDB, interface responsiva, histórico, biblioteca de exercícios e backup JSON.

## Conteúdo
- `www/`: versão PWA para hospedagem HTTPS e instalação pelo navegador.
- `android/`: projeto Android Studio com WebView que empacota os arquivos localmente; não requer internet durante o uso.

## Funcionalidades incluídas no protótipo
- Criar, editar, excluir e filtrar modelos de treino.
- Treino avulso.
- Séries, repetições, cargas e alternativas.
- Marcação de séries concluídas, troca de alternativa e reordenação durante a sessão.
- Histórico com busca, período, filtros e gráfico simples de evolução.
- Biblioteca de exercícios com cadastro e edição.
- Exportação/importação de backup completo e biblioteca JSON.
- Persistência local via IndexedDB.

## Gerar APK no Android Studio
1. Instale Android Studio com Android SDK 35 e JDK 17.
2. Abra a pasta `android/` no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Selecione **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. O APK de debug será gerado em `android/app/build/outputs/apk/debug/app-debug.apk`.

Para distribuir fora do seu aparelho, gere uma versão assinada em **Build > Generate Signed Bundle / APK**.

## Versão PWA
Hospede o conteúdo de `www/` em um domínio HTTPS. Abra o endereço no Android Chrome e use **Instalar aplicativo** ou **Adicionar à tela inicial**. O Service Worker mantém os arquivos essenciais em cache para uso offline.

## Observações
- Os dados ficam no dispositivo e não são sincronizados entre aparelhos.
- Faça backups JSON regularmente. Limpar os dados do app/navegador pode apagar os registros locais.
- A primeira instalação da PWA precisa de conexão. A versão Android empacota os arquivos no APK e abre sem internet.
- Este projeto não inclui serviços de nuvem, login ou analytics.
