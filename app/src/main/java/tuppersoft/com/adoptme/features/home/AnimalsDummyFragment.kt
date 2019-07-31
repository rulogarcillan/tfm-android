package tuppersoft.com.adoptme.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dummy_fragment.view.ivPhoto
import kotlinx.android.synthetic.main.dummy_fragment.view.tvAge
import kotlinx.android.synthetic.main.dummy_fragment.view.tvName
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.domain.entities.RecordDto

/**
 * Created by Raúl Rodríguez Concepción on 2019-07-30.
 * Talento Mobile
 * raulrcs@gmail.com
 */

class AnimalsDummyFragment : Fragment() {

    lateinit var page: RecordDto

    companion object {
        const val DATA_ANIMAL = "DATA_ANIMAL"
        fun newInstance(data: RecordDto): AnimalsDummyFragment {
            val args = Bundle()
            args.putSerializable(DATA_ANIMAL, data)
            val myFragment = AnimalsDummyFragment()
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dummy_fragment, container, false)
        getmArguments()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.ivPhoto.loadFromUrl(page.imageUrl.random())
        view.tvName.text = page.name
        view.tvAge.text = page.age.toString()
    }

    private fun getmArguments() {
        page = (arguments?.getSerializable(DATA_ANIMAL) ?: RecordDto()) as RecordDto
    }
}