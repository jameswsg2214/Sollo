package com.example.furniture.mainPage.ui

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.furniture.R
import com.example.furniture.dataBase.entity.FurnirureList
import kotlinx.android.synthetic.main.furniture_view.view.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(var context: Activity, var userArrayList: ArrayList<FurnirureList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var OnitemClickListener:OnItemClickListener?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.furniture_view, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val user: FurnirureList = userArrayList[position]
        val viewHolder =
            holder as RecyclerViewViewHolder
        viewHolder.txtView_title.text = user.ProductName

        viewHolder.txtView_type.text = user.ProductType

        viewHolder.txtView_price.text="Rs."+user.ProductPrice

        viewHolder.txtView_originalPrice.text="Rs."+user.ProductOriginalPrice



        viewHolder.deleteImageView.setOnClickListener {

            OnitemClickListener?.onClick(userArrayList[position].Id!!)
        }
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    fun setData(data: ArrayList<FurnirureList>?) {

        this!!.userArrayList.clear()

        this!!.userArrayList= data!!

        notifyDataSetChanged()

    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgView_icon: ImageView
        var txtView_title: TextView
        var txtView_type: TextView
        var txtView_price: TextView
        var txtView_originalPrice: TextView
        var deleteImageView:ImageView


        init {
            imgView_icon = itemView.findViewById(R.id.furnitureImg)
            txtView_title = itemView.findViewById(R.id.name)
            txtView_type = itemView.findViewById(R.id.Type)

            txtView_price = itemView.findViewById(R.id.Price)
            txtView_originalPrice = itemView.findViewById(R.id.OriginalPrice)
            deleteImageView=itemView.findViewById(R.id.delete)

        }
    }

    interface OnItemClickListener {
        fun onClick(
            ItemId: Long
        )
    }
    fun setOnItemClickListener(onitemClickListener: OnItemClickListener) {
        this.OnitemClickListener = onitemClickListener
    }

  /*  init {
        this.userArrayList = userArrayList
    }*/
}