package com.example.roundRobin

data class Agent(val id: Int, var currentJob: Job? = null)

data class Job(var id: String, var isCompleted: Boolean = false)
data class Jobs(var tickedId: String, var status: Int)

data class JobDetails(val agentId: String, val ticketId: String)

fun assignJobsToAgents(agents: List<Agent>, jobs: List<Job>) {
    val assignments = mutableListOf<Agent>()
    var currentJobIndex = 0
    for (agent in agents) {
        if (currentJobIndex < jobs.size) {
            val currentJob = jobs[currentJobIndex]
            agent.currentJob = currentJob
            assignments.add(Agent(agent.id, currentJob))
            currentJobIndex++
        }
    }
    while (currentJobIndex < jobs.size) {
        // completing the job
        for (agent in agents) {
            assignments.map { agent.currentJob?.isCompleted = true }
        }
        // Assign new jobs to agents who have completed their tasks
        for (agent in agents) {
            if (currentJobIndex < jobs.size && agent.currentJob?.isCompleted == true) {
                val newJob = jobs[currentJobIndex]
                agent.currentJob = newJob
                assignments.add(Agent(agent.id, newJob))
                currentJobIndex++
            }
        }
    }
    assignments.forEach {
        println(it)
    }

}

fun jobAssignment(agents: List<String>, jobs: List<String>): List<JobDetails> {
    val assignments = mutableListOf<JobDetails>()
    var i = 0
    do {
        agents.map { agent ->
            if (i < jobs.size) {
                assignments.add(JobDetails(agent, jobs[i]))
                i++
            }
        }
    } while (i < jobs.size)
    assignments.forEach {
        println(it)
    }
    return assignments
}

fun main() {
    val agents = List(2) { Agent(it + 1) }

    val jobs = List(8) { Job("Job-${it + 1}") }
    val agent = List(3) { "agent-${it + 1}" }

    val job = List(7) { "Job-${it + 1}" }
    jobAssignment(agent, job)
//    assignJobsToAgents(agents, jobs)
}

