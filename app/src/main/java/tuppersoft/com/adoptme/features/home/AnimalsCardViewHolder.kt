package tuppersoft.com.adoptme.features.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.Sex.FEMALE
import tuppersoft.com.domain.entities.Sex.MALE

class AnimalsCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val ivPhoto = view.findViewById<ImageView>(R.id.ivPhoto)
    private val tvName = view.findViewById<TextView>(R.id.tvName)
    private val tvAge = view.findViewById<TextView>(R.id.tvAge)
    private val ivSex = view.findViewById<ImageView>(R.id.ivSex)

    fun bind(recordDto: RecordDto, listener: AnimalsActions) {
        tvName.text = "${recordDto.name.substring(0, 1).toUpperCase() + recordDto.name.substring(1)}"
        tvAge.text = "${recordDto.age} ${view.context.resources.getQuantityText(R.plurals.age, recordDto.age)}"
        ivPhoto.loadFromUrl(recordDto.imageUrl[0])
        when (recordDto.sex) {
            MALE -> {
                ivSex.setImageResource(R.drawable.ic_male)
                ivSex.setColorFilter(ContextCompat.getColor(view.context, R.color.maleColor))
            }
            FEMALE -> {
                ivSex.setImageResource(R.drawable.ic_female)
                ivSex.setColorFilter(ContextCompat.getColor(view.context, R.color.femaleColor))
            }
        }

        view.setOnClickListener {
            listener.onClick(recordDto)
        }
    }
}