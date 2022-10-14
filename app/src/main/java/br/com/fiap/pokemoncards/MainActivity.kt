package br.com.fiap.pokemoncards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import br.com.fiap.pokemoncards.databinding.ActivityMainBinding
import br.com.fiap.pokemoncards.network.Pokemon
import br.com.fiap.pokemoncards.network.PokemonApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       loadCard()

        binding.button.setOnClickListener{
            loadCard()
        }
    }

    private fun loadCard(){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val service = PokemonApi.RETROFIT_SERVICE.getPokemons()

                withContext(Dispatchers.Main){
                    updateUI(service)
                }
            }catch (e: java.lang.Exception){
                Log.e("RETROFIT", "Falha: ${e.message}")
            }
        }
    }

    private fun updateUI(result: Pokemon) {
        //A funcao random do kotlin possui um bug nas versões menores que a 1.7.20
        //O codigo a seguir é apenas uma solucao alternativa
        val randomGenerator = Random(System.currentTimeMillis())
        val randomInt = randomGenerator.nextInt(0,14)

        val card = result.data[randomInt]
        binding.textViewName.text = card.name
        Picasso.get().load(card.images.large).resize(450, 600).into(binding.imageView)
        binding.textViewLevel1.text = card.level
        binding.textViewHealth1.text = card.hp
        binding.textViewRarity1.text = card.rarity
        binding.textViewTypes1.text = card.types.toString().replace("[\\[\\]]".toRegex(), "")
        binding.textViewEvolvesTo1.text = card.evolvesTo.toString().replace("[\\[\\]]".toRegex(), "")
        binding.textViewEvolvesFrom1.text = card.evolvesFrom
    }
}