package jr.brian.volley.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jr.brian.volley.data.AnimalsResponseItem
import jr.brian.volley.databinding.AnimalViewBinding

class AnimalAdapter(
    private var context: Context,
    private var animal: ArrayList<AnimalsResponseItem>
) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    private lateinit var binding: AnimalViewBinding

    override fun getItemCount(): Int {
        return animal.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = AnimalViewBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.apply {
            val animalInfo = animal[position]
            animalHabitat.text = "Habitat: ${animalInfo.habitat}"
            animalDiet.text = "Diet: ${animalInfo.diet}"
            animalFount.text = "Found at: ${animalInfo.geo_range}"
            Glide.with(context)
                .load(animalInfo.image_link).into(imageview)
        }
    }

    inner class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animalHabitat: TextView = binding.animalHabitat
        val animalDiet: TextView = binding.animalDiet
        val animalFount: TextView = binding.animalGeoRange
        val imageview: AppCompatImageView = binding.imgAnimal
    }
}
