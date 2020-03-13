package io.mega.megahub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import timber.log.Timber

class FirstFragment : Fragment() {
    private val args: FirstFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.d("taskId: %s, title: %s", args.taskId, args.title)

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

}
