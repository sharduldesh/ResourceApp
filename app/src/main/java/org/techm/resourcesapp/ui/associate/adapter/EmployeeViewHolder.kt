package org.techm.resourcesapp.ui.associate.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.AssociateEntity



/**
 * @class{EmployeeViewHolder} -> view-holder for associate
 */
class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var mLogo: ImageView = itemView.findViewById(R.id.imageViewPic)
    var mName: TextView = itemView.findViewById(R.id.textViewName)
    var mBand: TextView = itemView.findViewById(R.id.textViewBand)
    var mID: TextView = itemView.findViewById(R.id.textViewID)
    var mDesignation: TextView = itemView.findViewById(R.id.textViewDesignation)
    var mCompetency: TextView = itemView.findViewById(R.id.textViewCompetency)
    var mCurrentProject: TextView = itemView.findViewById(R.id.textViewCurrentProject)

    fun bind(associateEntity: AssociateEntity , listener: EmployeeAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(associateEntity)
        }
    }
}
