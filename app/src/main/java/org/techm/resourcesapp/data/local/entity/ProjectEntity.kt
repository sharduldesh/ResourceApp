package org.techm.resourcesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @class{ProjectEntity} -> entity for project
 */

@Entity(tableName = "project")
data class ProjectEntity(

    @ColumnInfo(name = "projectName")
    var projectName: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}