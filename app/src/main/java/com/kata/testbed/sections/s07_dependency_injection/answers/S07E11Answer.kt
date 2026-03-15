package com.kata.testbed.sections.s07_dependency_injection.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private interface ImageLoader {
    fun load(url: String): String
}

private class RealImageLoader : ImageLoader {
    override fun load(url: String): String = "Loaded image from $url"
}

private class ImageEditor(
    private val imageLoader: ImageLoader,
    private val imageUrl: String
) {
    fun edit(): String {
        val loaded = imageLoader.load(imageUrl)
        return "$loaded -> applying filters"
    }
}

private fun interface ImageEditorFactory {
    fun create(imageUrl: String): ImageEditor
}

@Composable
fun S07E11Answer() {
    val imageLoader: ImageLoader = RealImageLoader()
    val factory = ImageEditorFactory { url -> ImageEditor(imageLoader, url) }

    val editor1 = factory.create("https://example.com/photo1.jpg")
    val editor2 = factory.create("https://example.com/photo2.jpg")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Assisted Inject",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Some params injected, others provided at runtime:", fontWeight = FontWeight.SemiBold)
        Text(
            text = "class ImageEditor @AssistedInject constructor(\n  private val imageLoader: ImageLoader,\n  @Assisted private val imageUrl: String\n)\n\n@AssistedFactory\ninterface ImageEditorFactory {\n  fun create(imageUrl: String): ImageEditor\n}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Factory creates editors with different URLs:", fontWeight = FontWeight.SemiBold)
        Text(text = "Editor 1: ${editor1.edit()}")
        Text(text = "Editor 2: ${editor2.edit()}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "imageLoader is injected by Hilt; imageUrl is passed at creation time.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
