package tuppersoft.com.adoptme.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.domain.entities.RecordDto

class AnimalsCardAdapter(private val list: MutableList<RecordDto>, val listener: AnimalsActions) : RecyclerView.Adapter<AnimalsCardViewHolder>() {
    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsCardViewHolder {
        return AnimalsCardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_animal_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimalsCardViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
}