# TheMovie
https://www.themoviedb.org/




# O prejeto conta com o padrão clena
# Domain
  - Nossa logica de negocio: Models, Usecases, Contratos
# Presenter
  - Nossas views, estamos usando o MVI (model - View - Intent) que casa muito bem com o jetpack compose
# Data
  - Framework externos Room, Retrofit
# Core
  - Aqui temos algumas coisas que a aplicação precisa
# Util
  - utilidades para serem acessadas de arcodo com o diretório que esta
  
# O que falta:
 - Testes
 - Cache
 
 # !! IMPORTANTE !!
 ````
 Para que o app funcione corretamente é necessário colocar no arquivo:
 
 local.properties

api=" SEU KEY DO MOVIE DB"
base_url="https://api.themoviedb.org/3/"
base_image="https://image.tmdb.org/t/p/"
w440_and_h660_face ="w440_and_h660_face"
token=" SEU TOKEN DO MOVIE DB"
```
