package com.paulo.mymovie.data.repositories.local.welcome
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.OnBoardingData

object ListWelcome {
    fun initList(): ArrayList<OnBoardingData> {
        val items = ArrayList<OnBoardingData>()

        items.add(
            OnBoardingData(
                R.drawable.wel1,
                "Seja Bem vindo",
                "Não perca os melhores filmes, de ação," +
                        " suspense, comédia tudo em um único lugar.",
            )
        )

        items.add(
            OnBoardingData(
                R.drawable.wel2,
                "Alguém falou ingressos?",
                "Sim, também vendemos ingressos para cinema, teatro, stand-ups e outros eventos.",

            )
        )

        items.add(
            OnBoardingData(
                R.drawable.wel3,
                "Vamos lá?",
                "Chegou a hora de acessar este super ap e estamos muito felizes de ter você conosco!",

            )
        )
        return items

    }
}