# TheMovie
https://www.themoviedb.org/


## Arquitetura

> Presentation
 >>Para lidarmos com a uis, usamos o MVI - Model-View-Intent junto ao viewModels, que 
 casa muito bem com o jetpack compose e sua reatividade
> Domain']
    >> Nossas regras estão aqui, assim como os models, contratos, usecase( Sim optei pelo use case,
    para que o Single responsabilite faça juz)

> Data
 >>Framework externos Room, Retrofit

> Core
 >>Aqui temos algumas coisas que a aplicação precisa

> Util
 >>utilidades para serem acessadas de arcodo com o diretório que esta


# Utilizamos o Cache usando o iterceptor 

# !! IMPORTANTE !!
 

> Onboarding
 >>Usei o Onboarding de welcome para fazer o download dos dados
 que pego da api. Enquanto o cliente se deleita na bela tela de entrada
 eu  baixo os dados, aqui fica uma ressalva, pois
 não fiz o tratamento para caso o app, esteja sem internet, estou sem tempo, mas 
 pretendo impelemtar..
 Uma outra coisa que foi feita foi usar o singleton para
 trasitar os dados sem ter a necessidade de consultas, otimizando assim o app.
 
 ## Padrões utilizado

> Abstract Factory

> Singleton

> Observer

> State



## Importante
COMENTE O CÓDIGO PARA GERAR AS CHAVES CORRETAMENTE:
no build.gradle a nível de projeto comente o código assim:
```
  /*
        buildConfigField("String", "API_KEY", apikeyProperties["api"] as String)
        buildConfigField("String", "BASE_URL", apikeyProperties["base_url"] as String)
        buildConfigField("String", "BASE_IMAGE", apikeyProperties["base_image"] as String)
        buildConfigField("String", "W400H660", apikeyProperties["w440_and_h660_face"] as String)
        buildConfigField("String", "TOKEN", apikeyProperties["token"] as String)
    */
```
DEPOIS:

> Para que o aplicativo funcione, você deve colocar o código abaixo no:
 ## local.properties

```

api="SEU KEY DO MOVIE DB"
base_url="https://api.themoviedb.org/3/"
base_image="https://image.tmdb.org/t/p/"
w440_and_h660_face ="w440_and_h660_face"
token=" SEU TOKEN DO MOVIE DB"
```
Clique em SINCRONIZAR
e DESCOMENTE O CÓDIGO:
```
        buildConfigField("String", "API_KEY", apikeyProperties["api"] as String)
        buildConfigField("String", "BASE_URL", apikeyProperties["base_url"] as String)
        buildConfigField("String", "BASE_IMAGE", apikeyProperties["base_image"] as String)
        buildConfigField("String", "W400H660", apikeyProperties["w440_and_h660_face"] as String)
        buildConfigField("String", "TOKEN", apikeyProperties["token"] as String)
```
Pode ocorrer que seu android studio reclame do plugin do gradle, então mude-o para:
```
  id("com.android.application") version "8.0.2" apply false
```
Se tudo der certo o projeto irá buildar mas se não der você pode se deliciar com o vídeo:

https://www.youtube.com/watch?v=_5c7yJN66yI&t=267s

Usages:
- Flow
- ViewModel
- Retrofit
- Coroutine
- Coil
- Hilt
- Room
- SplashAPi
- Animation
- Json5 Motion Layout
- Jetpack compose ( É VIDA )
- gradle 8
- Local Properties
- Clean architecture
- Um final de semana todo 😢



