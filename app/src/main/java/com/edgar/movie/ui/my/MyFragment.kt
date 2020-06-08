package com.edgar.movie.ui.my

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.edgar.movie.R
import com.edgar.movie.databinding.FragmentMyBinding
import com.edgar.movie.ui.setting.SettingActivity

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMyBinding>(
            inflater, R.layout.fragment_my, container, false
        ).apply {
            viewModel = MyViewModel()
            lifecycleOwner = viewLifecycleOwner
            var isToolbarShown = false
            Log.e("MyFrament", "setOnScrollChangeListener: ")
            // scroll change listener begins at Y = 0 when image is fully collapsed
            plantDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    val shouldShowToolbar = scrollY > toolbar.height
                    Log.e("MyFrament", "shouldShowToolbar: " + shouldShowToolbar.toString())

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {

                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
//                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar

                        Log.e("MyFrament", "isToolbarShown: " + isToolbarShown.toString())
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
//                view.findNavController().navigateUp()
                Log.e("MyFrament", "NavigationOnClick")
//                view.findNavController().navigate()
//                activity.startInt
                startActivity(Intent(activity, SettingActivity::class.java))
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_my_message -> {
                        Log.e("MyFrament", "message click")
//                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }


}