package com.example.data.models

data class AdvancedAvatarSpec(
    val id: String = "custom_model_advanced",
    val name: String = "Custom Elite Model",

    // Core Physical
    val age: Int = 23,
    val heightStature: String = "Tall / Model (5'9\"+)",
    val bodyType: String = "Hourglass",
    val breastSize: String = "Large / Natural",
    val waistHipRatio: String = "Extreme Hourglass",
    val race: String = "Eurasian",

    // Detailed Skin & Features
    val skinTexture: String = "Natural Realism (with subtle freckles/texture)",
    val tattoosAndPiercings: List<String> = listOf("Minimalist Ink & Piercings"),

    // Head, Face & Glam
    val eyeColor: String = "Amber Gold",
    val hairColor: String = "Jet Black",
    val hairStyle: String = "Long Sleek Straight",
    val facialStructure: String = "High Cheekbones",
    val lipShape: String = "Plump & Defined",
    val expressionVibe: String = "Seductive / Smoldering",
    val makeupStyle: String = "Smoky Eyes",

    // Wardrobe & Environment
    val currentOutfit: String = "Designer Silk Lingerie",
    val isNudeEnabled: Boolean = false,
    val backgroundVibe: String = "Moody Studio Backdrop with Cinematic Key Lighting",

    // Local Byte Reference Image Path
    val referenceImagePath: String? = null
) {

    /**
     * Builds a high-quality prompt token designed for the photoreal / explicit style
     * shown in the goal reference images (nude, lingerie, cosplay, detailed anatomy).
     * When isNudeEnabled = true it forces full anatomical detail with classical lighting.
     */
    fun toAbstractedPromptToken(): String {
        val clothingDesc = if (isNudeEnabled) {
            "completely nude, fully exposed body, detailed female anatomy, natural skin texture with subtle pores and highlights, fine-art classical lighting, high-detail photorealistic nude portrait, no clothing, no fabric"
        } else {
            "wearing $currentOutfit, detailed fabric texture, realistic clothing folds"
        }

        val inkDesc = if (tattoosAndPiercings.isNotEmpty() &&
            !tattoosAndPiercings.first().contains("No Tattoos", ignoreCase = true)
        ) {
            tattoosAndPiercings.joinToString(", ")
        } else {
            "clean skin, no tattoos"
        }

        val refTag = if (!referenceImagePath.isNullOrEmpty()) {
            "using reference image for face consistency"
        } else {
            "no reference image"
        }

        return buildString {
            append("(masterpiece, best quality, photorealistic, ultra detailed, 8k, sharp focus), ")
            append("$age year old $race woman, ")
            append("$heightStature, $bodyType body type, $breastSize breasts, $waistHipRatio figure, ")
            append("$skinTexture skin, $facialStructure face, $lipShape lips, ")
            append("$eyeColor eyes, $hairStyle $hairColor hair, ")
            append("$makeupStyle makeup, $expressionVibe expression, ")
            append("$clothingDesc, ")
            append("$inkDesc, ")
            append("background: $backgroundVibe, ")
            append("cinematic lighting, realistic skin subsurface scattering, ")
            append("($refTag)")
        }
    }

    /** Short human-readable summary for UI */
    fun toShortSummary(): String {
        val nudeTag = if (isNudeEnabled) " • NUDE" else ""
        return "$name • $age • $race • $bodyType • $breastSize$nudeTag"
    }

    /** Even shorter label for chips / lists */
    fun toChipLabel(): String {
        return if (isNudeEnabled) "$name (Nude)" else name
    }
}
