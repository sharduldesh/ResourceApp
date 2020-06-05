package org.techm.resourcesapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.techm.resourcesapp.data.local.dao.ProjectDAO
import org.techm.resourcesapp.data.local.database.ResourcesDatabase
import org.techm.resourcesapp.data.local.entity.ProjectEntity

/**
 * @class{ProjectRepository} -> repository for project
 */

class ProjectRepository(application: Application) {

    private val projectDAO: ProjectDAO
    private var projectList: LiveData<MutableList<ProjectEntity>>

    init {
        val db = ResourcesDatabase.getInstance(application)
        projectDAO = db?.projectDao()!!
        projectList = projectDAO.fetchAllProjects()
    }

    fun fetchListOfProjects(): LiveData<MutableList<ProjectEntity>> {
        return projectList
    }

    suspend fun inertListOfProjects(projectList: MutableList<ProjectEntity>) {
        projectDAO.insertListOfProjects(projectList)
    }

    suspend fun insertProject(projectEntity: ProjectEntity) {
        projectDAO.createProject(projectEntity)
    }

}