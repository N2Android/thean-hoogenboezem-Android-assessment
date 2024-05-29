package com.glucode.about_you.about.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewEngineerProfileCardBinding

class ProfileCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {
    private val binding: ViewEngineerProfileCardBinding =
        ViewEngineerProfileCardBinding.inflate(LayoutInflater.from(context), this)

    private val profilePicture: ImageView = binding.profileWrapper.findViewById(R.id.profile_image)
    private val name: TextView = binding.profileWrapper.findViewById(R.id.name)
    private val role: TextView = binding.profileWrapper.findViewById(R.id.role)

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        binding.profileWrapper.setBackgroundColor(context.getColor(R.color.black))
        name.setTextColor(context.getColor(R.color.white))
        role.setTextColor(context.getColor(R.color.white))
    }

    fun setupProfilePictureClickListener(view: ProfilePictureView){
        profilePicture.setOnClickListener { view.selectPhoto() }
    }

    fun setProfilePicture(uri: Uri){
        profilePicture.setImageURI(uri)
    }

    fun addName(engineerName: String){
         name.text = engineerName
    }

    fun addRole(engineerRole: String){
        role.text = engineerRole
    }
}