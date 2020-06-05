package org.techm.resourcesapp.ui.project.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_project.*
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.ProjectEntity
import org.techm.resourcesapp.ui.project.viewmodel.ProjectViewModel

/**
 * @activity{ActivityAddProject} -> view for project
 */
class ActivityAddProject: AppCompatActivity() {

    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var projectEntity: ProjectEntity
    private var isAddOrUpdate: String? = null
    private var projectName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)

        isAddOrUpdate = intent.extras?.getString("addOrUpdate")
        projectName = intent.extras?.getString("projectName")
        Log.e("isAddOrUpdate : " , "$isAddOrUpdate")
        Log.e("projectName : " , "$projectName")

        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        progressBarProject.visibility = View.GONE
        initUI()
    }

    /**
     * initializing ui
     */
    private fun initUI() {
        if (isAddOrUpdate.equals("update"))
            inputCurrentProject.setText(projectName)

        val projectName = inputCurrentProject.text
        inputCurrentProject.text = projectName
        /**
         * add project entry to database
         */
        buttonSubmitProject.setOnClickListener {
            progressBarProject.visibility = View.VISIBLE
            Log.e("project name" , projectName.toString())
            projectEntity = ProjectEntity(inputCurrentProject.text.toString())

            projectViewModel.insert(projectEntity)
            progressBarProject.visibility = View.GONE
        }
    }
}