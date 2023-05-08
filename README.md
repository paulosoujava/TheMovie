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


## Importante

> Para que o aplicativo funcione, você deve colcar o código abaixo no:
 ## local.properties

```
api=" SEU KEY DO MOVIE DB"
base_url="https://api.themoviedb.org/3/"
base_image="https://image.tmdb.org/t/p/"
w440_and_h660_face ="w440_and_h660_face"
token=" SEU TOKEN DO MOVIE DB"
```
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
- Clean arquitecture
- Um final de semana todo 😢


# O que falta:
 - Testes
 - Cache
 
