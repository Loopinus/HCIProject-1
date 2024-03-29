
package kayleenp.github.io.getapet.petdetail
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kayleenp.github.io.getapet.R
import kayleenp.github.io.getapet.petgrid.picasso.Tint

import kotlinx.android.synthetic.main.activity_pet_detail.*

class PetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)
        loadHeaderPicture()
        loadAvatarPicture()
        setupToolbarIcons()
    }

    private fun loadHeaderPicture() {
        val url = intent?.extras?.getString(EXTRA_PET_PICTURE_URL, null)
        Picasso.get()
            .load(url)
            .transform(Tint(1f, ContextCompat.getColor(this, R.color.colorPrimary)))
            .fit()
            .centerCrop()
            .into(details_header)
    }

    private fun loadAvatarPicture() {
        val url =
            "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg?cs=srgb&dl=face-facial-hair-fine-looking-614810.jpg&fm=jpg"
        Picasso.get()
            .load(url)
            .fit()
            .centerCrop()
            .into(avatar)
    }

    private fun setupToolbarIcons() {
        val isDark = intent?.extras?.getBoolean(EXTRA_PET_IS_DARK, false) ?: false
        val iconRes = if (isDark) R.drawable.ic_close_black_24dp_light else R.drawable.ic_close_black_24dp_dark
        //details_toolbar.setNavigationIcon(iconRes)

        val startingConstraintSet = details_motion.getConstraintSet(R.id.start)

        val darkColor = ContextCompat.getColor(this, R.color.iconColor)
        val lightColor = ContextCompat.getColor(this, R.color.colorSecondary)
        startingConstraintSet
            .getParameters(R.id.details_toolbar)
            .mCustomConstraints["IconTint"]
            ?.setColorValue(if (isDark) lightColor else darkColor)
    }

    private fun intentToWA() {

    }

    companion object {
        const val EXTRA_PET_ID: String = "EXTRA_PET_ID"
        const val EXTRA_PET_PICTURE_URL: String = "EXTRA_PET_PICTURE_URL"
        const val EXTRA_PET_IS_DARK: String = "EXTRA_PET_IS_DARK"
    }
}
