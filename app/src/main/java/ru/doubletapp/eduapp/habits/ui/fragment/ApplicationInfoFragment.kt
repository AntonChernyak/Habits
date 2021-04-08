package ru.doubletapp.eduapp.habits.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.databinding.FragmentApplicationInfoBinding

class ApplicationInfoFragment : Fragment() {

    private val binding: FragmentApplicationInfoBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_application_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addToggleToNavigationDrawer()
    }

    private fun addToggleToNavigationDrawer(){
        val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            requireActivity(), drawer, binding.infoToolbar,
            R.string.navigation_open, R.string.navigation_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }
}