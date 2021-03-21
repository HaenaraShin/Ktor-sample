package dev.haenara.sample.ktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import dev.haenara.sample.core.Cat
import dev.haenara.sample.core.MyClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val imageView: ImageView by lazy { findViewById<ImageView>(R.id.iv_cat) }
    val button: Button by lazy { findViewById<Button>(R.id.btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            CoroutineScope(IO).launch {
                findCat()
            }
        }
    }

    suspend fun findCat() {
        val cats = MyClass().getCatFromApi()
        showUrl(cats.first())
    }

    fun showUrl(cat: Cat) {
        CoroutineScope(Main).launch {
            Glide.with(this@MainActivity)
                .load(cat.url)
                .into(imageView)
        }
    }
}
