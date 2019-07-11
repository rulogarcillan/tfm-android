package tuppersoft.com.adoptme.features.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.custom_radio_sex.view.*


import tuppersoft.com.adoptme.R


/**
 * Created by Raúl Rodríguez Concepción on 2019-07-11.
 * Talento Mobile
 * raulrcs@gmail.com
 */
class CustomRadioSex : ConstraintLayout {


    lateinit var globalActions: (Int) -> Unit
    var idSelected = 1

    fun setOnClick(globalActions: (Int) -> Unit) {
        this.globalActions = globalActions
    }

    fun getSelectedId() = idSelected

    constructor(context: Context?) : super(context) {
        init()

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.custom_radio_sex, this)
        unselectAll()
        select1.setColorFilter(ContextCompat.getColor(context, R.color.primaryBlue), PorterDuff.Mode.SRC_IN)
        selectText1.setTextColor(ContextCompat.getColor(context, R.color.primaryBlue))
        idSelected = 1

        select1.setOnClickListener {
            unselectAll()
            select1.setColorFilter(ContextCompat.getColor(context, R.color.primaryBlue), PorterDuff.Mode.SRC_IN)
            selectText1.setTextColor(ContextCompat.getColor(context, R.color.primaryBlue))
            setSelected(1)
        }
        select2.setOnClickListener {
            unselectAll()
            select2.setColorFilter(ContextCompat.getColor(context, R.color.primaryBlue), PorterDuff.Mode.SRC_IN)
            selectText2.setTextColor(ContextCompat.getColor(context, R.color.primaryBlue))
            setSelected(2)
        }

    }

    private fun setSelected(id: Int) {
           globalActions(id)
        idSelected = id
    }

    private fun unselectAll() {
        select1.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)
        select2.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)

        selectText1.setTextColor(ContextCompat.getColor(context, R.color.black))
        selectText2.setTextColor(ContextCompat.getColor(context, R.color.black))

    }


}