package org.techm.resourcesapp.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
Employee table schema
 */

@Entity(tableName = "employee")
data class AssociateEntity(
    var employeeId: String,
    var employeeName: String,
    var employeeBand: String,
    var employeeDesignation: String,
    var employeeCompetency: String,
    var employeeCurrentProject: String
    ) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}