package com.skrate.example.adapter

sealed class HomeRecyclerViewItem {

    class Overview(
        val skillsVerified: Int,
        val profileViews: Int,
        val mentorshipSessions: Int,
        val jobsApplied: Int,
    ) : HomeRecyclerViewItem()

    class UpcomingSessions(
        val date: String,
        val timings: String,
        val sessionType: String,
        val mentorName: String
    ) : HomeRecyclerViewItem()

    class NewJobs(
        val role: String,
        val datePosted: String,
        val location: String,
        val organizationName: String
    ) : HomeRecyclerViewItem()


}