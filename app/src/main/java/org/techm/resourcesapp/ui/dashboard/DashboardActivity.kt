package org.techm.resourcesapp.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.techm.resourcesapp.R
import org.techm.resourcesapp.app.CompetencyApplication
import org.techm.resourcesapp.data.local.entity.ProjectEntity
import org.techm.resourcesapp.ui.associate.view.FragmentAssociate
import org.techm.resourcesapp.ui.project.view.FragmentProject
import org.techm.resourcesapp.ui.project.viewmodel.ProjectViewModel


/**
 * @activity{DashboardActivity} -> view for dashboard, which binds both(associate & project) views
 */
class DashboardActivity : AppCompatActivity() {

    var list: MutableList<ProjectEntity> = ArrayList()
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var app: CompetencyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initUI(FragmentAssociate())
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_associate -> {
                    initUI(FragmentAssociate())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_project -> {
                    initUI(FragmentProject())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }

    private fun initUI(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }
}
