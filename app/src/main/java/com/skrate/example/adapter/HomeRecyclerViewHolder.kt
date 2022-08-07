package com.skrate.example.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.skrate.example.databinding.ItemDashboardStatsBinding
import com.skrate.example.databinding.ItemJobPostingsBinding
import com.skrate.example.databinding.ItemUpcomingSessionsBinding

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class OverviewViewHolder(private val binding: ItemDashboardStatsBinding) :
        HomeRecyclerViewHolder(binding) {
        fun bind(title: HomeRecyclerViewItem.Overview) {
            binding.tvProfileView.text = title.profileViews.toString()
            binding.tvMentorSessions.text = title.mentorshipSessions.toString()
            binding.tvJobsApplied.text = title.jobsApplied.toString()
            binding.tvSkills.text = title.skillsVerified.toString()
        }
    }

    class UpcomingViewHolder(private val binding: ItemUpcomingSessionsBinding) :
        HomeRecyclerViewHolder(binding) {
        fun bind(title: HomeRecyclerViewItem.UpcomingSessions) {
            binding.tvMentorName.text = title.mentorName
            binding.tvDate.text = title.date
            binding.tvTiming.text = title.timings
            binding.tvSessionType.text = title.sessionType
        }
    }


    class NewJobsViewHolder(private val binding: ItemJobPostingsBinding) :
        HomeRecyclerViewHolder(binding) {
        fun bind(title: HomeRecyclerViewItem.NewJobs) {
            binding.tvCompanyName.text = title.organizationName
            binding.tvJobDatePosted.text = title.datePosted
            binding.tvJobLocation.text = title.location
            binding.tvJobRole.text = title.role


        }
    }


}