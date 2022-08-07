package com.skrate.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skrate.example.R
import com.skrate.example.databinding.ItemDashboardStatsBinding
import com.skrate.example.databinding.ItemJobPostingsBinding
import com.skrate.example.databinding.ItemUpcomingSessionsBinding

class JobDetailsAdapter(private val items: ArrayList<HomeRecyclerViewItem>) :
    RecyclerView.Adapter<HomeRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when (viewType) {

            R.layout.item_dashboard_stats -> HomeRecyclerViewHolder.OverviewViewHolder(
                ItemDashboardStatsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_upcoming_sessions -> HomeRecyclerViewHolder.UpcomingViewHolder(
                ItemUpcomingSessionsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_job_postings -> HomeRecyclerViewHolder.NewJobsViewHolder(
                ItemJobPostingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )


            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when (holder) {
            is HomeRecyclerViewHolder.OverviewViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Overview)
            is HomeRecyclerViewHolder.UpcomingViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.UpcomingSessions)
            is HomeRecyclerViewHolder.NewJobsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.NewJobs)
        }
    }


    override fun getItemCount() = items.size


    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeRecyclerViewItem.Overview -> R.layout.item_dashboard_stats
            is HomeRecyclerViewItem.UpcomingSessions -> R.layout.item_upcoming_sessions
            is HomeRecyclerViewItem.NewJobs -> R.layout.item_job_postings
        }
    }

}


