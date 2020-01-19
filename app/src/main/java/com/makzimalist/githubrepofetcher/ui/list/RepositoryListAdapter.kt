package com.makzimalist.githubrepofetcher.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makzimalist.githubrepofetcher.R
import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.extension.hide
import com.makzimalist.githubrepofetcher.extension.visible
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoryListAdapter(
    var data: List<Repository> = emptyList(),
    val listener: RepositoryClickListener
) : RecyclerView.Adapter<RepositoryListAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)
        return RepoViewHolder(item)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repository = data[position]

        holder.itemView.title.text = repository.name
        holder.itemView.description.text = repository.description
        holder.itemView.owner.text = repository.owner.name
        holder.itemView.stars.text = repository.stars.toString()

        if (repository.language.isNullOrEmpty()) {
            holder.itemView.language.hide()
        } else {
            holder.itemView.language.visible()
            holder.itemView.language.text = repository.language
        }

        holder.itemView.setOnClickListener { listener.onRepositoryClick(repository) }
    }

    interface RepositoryClickListener {
        fun onRepositoryClick(repository: Repository)
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}