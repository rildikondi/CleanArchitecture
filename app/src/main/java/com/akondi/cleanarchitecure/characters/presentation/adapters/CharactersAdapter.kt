package com.akondi.cleanarchitecure.characters.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.characters.domain.entities.characters.CharacterView
import com.akondi.cleanarchitecure.core.extensions.inflate
import com.akondi.cleanarchitecure.core.extensions.loadFromUrl
import com.akondi.cleanarchitecure.core.navigation.Navigator
import kotlinx.android.synthetic.main.row_character.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class CharactersAdapter
@Inject constructor() : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    internal var collection: List<CharacterView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (CharacterView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent.inflate(
                R.layout.row_character
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(merchantView: CharacterView, clickListener: (CharacterView, Navigator.Extras) -> Unit) {
            itemView.characterName.text = merchantView.name
            itemView.characterPoster.loadFromUrl(merchantView.url)
            itemView.setOnClickListener { clickListener(merchantView, Navigator.Extras(itemView.characterPoster)) }
        }
    }
}