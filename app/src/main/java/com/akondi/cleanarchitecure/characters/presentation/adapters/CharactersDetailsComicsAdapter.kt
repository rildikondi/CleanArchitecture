package com.akondi.cleanarchitecure.characters.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.CharacterDetailsView
import com.akondi.cleanarchitecure.characters.domain.entities.characterdetails.Item
import com.akondi.cleanarchitecure.core.extensions.inflate
import com.akondi.cleanarchitecure.core.extensions.loadFromUrl
import com.akondi.cleanarchitecure.core.navigation.Navigator
import kotlinx.android.synthetic.main.row_character.view.*
import kotlinx.android.synthetic.main.row_comic.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class CharactersDetailsComicsAdapter
@Inject constructor() : RecyclerView.Adapter<CharactersDetailsComicsAdapter.ViewHolder>() {

    internal var collection: List<Item> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (CharacterDetailsView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent.inflate(
                R.layout.row_comic
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, clickListener: (CharacterDetailsView, Navigator.Extras) -> Unit) {
            itemView.comicDesciption.text = item.name
            itemView.setOnClickListener {

            }
        }
    }
}