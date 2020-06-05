package org.techm.resourcesapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.techm.resourcesapp.data.local.entity.ProjectEntity

/**
 * @DAO{ProjectDAO}
 */
@Dao
interface ProjectDAO{

    @Query("SELECT * FROM project")
    fun fetchAllProjects(): LiveData<MutableList<ProjectEntity>>

    @Insert
    suspend fun insertListOfProjects(projectEntity: MutableList<ProjectEntity>)

    @Insert
    suspend fun createProject(projectEntity: ProjectEntity): Long

}