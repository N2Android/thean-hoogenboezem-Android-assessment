package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewEngineerProfileCardBinding

class ProfileCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {
    private val binding: ViewEngineerProfileCardBinding =
        ViewEngineerProfileCardBinding.inflate(LayoutInflater.from(context),
            this)

    //TODO private val profilePicture: ImageView = binding.profileImage
    private val name: TextView = binding.name
    private val role: TextView = binding.role

    init {
        val whiteColour = ContextCompat.getColor(context, R.color.white)
        val blackColour = ContextCompat.getColor(context, R.color.black)
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        name.setTextColor(whiteColour)
        role.setTextColor(whiteColour)
        setCardBackgroundColor(blackColour)
    }

    fun addName(engineerName: String){
         name.text = engineerName
    }

    fun addRole(engineerRole: String){
        role.text = engineerRole
    }
}