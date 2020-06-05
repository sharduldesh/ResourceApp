package org.techm.resourcesapp.ui.project.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.ProjectEntity


/**
 * @class{ProjectViewHolder} -> view-holder for project
 */
class ProjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var mName: TextView = itemView.findViewById(R.id.textViewProjectName)

    fun bind(projectEntity: ProjectEntity, listener: ProjectAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(projectEntity)
        }
    }
}
