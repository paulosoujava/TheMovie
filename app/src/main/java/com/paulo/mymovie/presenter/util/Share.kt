package com.paulo.mymovie.presenter.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ktx.dynamicLink


fun share(context: Context, title: String, idMovie: String) {

    val url = "https://www.themoviedb.org/movie/$idMovie-${title.lowercase()}"

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Compartilhar")
    context.startActivity(shareIntent)

}