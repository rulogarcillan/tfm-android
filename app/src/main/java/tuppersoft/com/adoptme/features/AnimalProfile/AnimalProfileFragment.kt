package tuppersoft.com.adoptme.features.AnimalProfile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.home.AnimalsDummyFragment
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.domain.entities.RecordDto

class AnimalProfileFragment : GlobalFragment() {

    lateinit var recordDto: RecordDto

    companion object {
        private const val ANIMAL = "ANIMAL"

        @JvmStatic
        fun newInstance(recordDto: RecordDto) =
            AnimalProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ANIMAL, recordDto)

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_animal_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getmArguments() {
        recordDto = (arguments?.getSerializable(ANIMAL) ?: RecordDto()) as RecordDto
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
         getmArguments()
        (context as MainActivity).tvTittle.text = recordDto.name
    }
}
