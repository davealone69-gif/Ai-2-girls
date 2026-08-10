package com.example.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.AvatarGraphicPreview
import com.example.viewmodel.AuraViewModel

@Composable
fun AvatarCreatorScreen(
    viewModel: AuraViewModel,
    modifier: Modifier = Modifier
) {
    val avatarState by viewModel.avatarState.collectAsState()
    val advSpec by viewModel.advancedAvatarSpec.collectAsState()
    val activePersona by viewModel.activePersona.collectAsState()

    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { viewModel.saveLocalReferenceImage(context, it) }
    }

    // Expanded option lists tuned to the goal reference images
    val heightOptions = listOf(
        "Tall / Model (5'9\"+)",
        "Average Height (5'5\"-5'7\")",
        "Petite (5'2\"-5'4\")",
        "Amazonian (6'0\"+)"
    )
    val bodyTypes = listOf(
        "Hourglass",
        "Slim / Petite",
        "Curvy / Athletic",
        "Voluptuous",
        "Muscular / Fit",
        "Soft / Thick"
    )
    val breastSizes = listOf(
        "Small / Subtle",
        "Medium / Natural",
        "Large / Natural",
        "Full / Voluptuous",
        "Huge / Enhanced"
    )
    val waistHipRatios = listOf(
        "Extreme Hourglass",
        "Classic 0.7 Ratio",
        "Athletic Curved",
        "Slim & Straight",
        "Wide Hips / Soft"
    )
    val races = listOf(
        "Eurasian",
        "Caucasian",
        "Latina",
        "East Asian",
        "Afro-Caribbean",
        "Middle Eastern",
        "South Asian",
        "Mixed"
    )
    val skinTextures = listOf(
        "Natural Realism (with subtle freckles/texture)",
        "Smooth Studio Porcelain",
        "Sun-Kissed / Tan Lines",
        "Dewy Glow",
        "Pale / Fair",
        "Deep / Rich Brown"
    )
    val tattoosOptions = listOf(
        "No Tattoos / Clean Skin",
        "Minimalist Ink & Piercings",
        "Full Sleeves & Nose Ring",
        "Back Piece + Rib Ink",
        "Subtle Ankle & Wrist",
        "Heavy Traditional Sleeves"
    )
    val facialStructures = listOf(
        "High Cheekbones",
        "Soft Oval",
        "Sculpted Jawline",
        "Heart Shaped",
        "Delicate / Petite"
    )
    val lipShapes = listOf(
        "Plump & Defined",
        "Natural Rose",
        "Pouty Full",
        "Classic Curved"
    )
    val expressions = listOf(
        "Seductive / Smoldering",
        "Playful & Mischievous",
        "Mysterious",
        "Innocent & Sweet",
        "Dominant & Fierce",
        "Soft / Dreamy"
    )
    val makeupStyles = listOf(
        "Smoky Eyes",
        "Minimal Natural",
        "Bold Red Lip",
        "Gothic Glam",
        "High-Fashion Editorial",
        "Fresh / Dewy"
    )
    val hairColors = listOf(
        "Jet Black",
        "Platinum Blonde",
        "Deep Brunette",
        "Vibrant Red",
        "Auburn / Copper",
        "Silver Grey",
        "Pastel Pink",
        "Hot Pink",
        "Blue-Black"
    )
    val hairStyles = listOf(
        "Long Sleek Straight",
        "Long Cascading Waves",
        "Sleek Bob",
        "Curled Ponytail",
        "High Ponytail",
        "Twin Tails / Pigtails",
        "Messy Bun",
        "Cyber Pixie",
        "Wet Look"
    )
    val outfits = listOf(
        "Designer Silk Lingerie",
        "Black Lace Lingerie",
        "Fishnet Bodysuit",
        "Leather & Straps",
        "Sheer Black Stockings + Garter",
        "Micro Bikini",
        "Open Shirt + Nothing Under",
        "Cosplay / Thematic",
        "Casual Streetwear",
        "Evening Gala Dress",
        "Latex / Vinyl",
        "Nothing (use Nude toggle)"
    )
    val bgVibes = listOf(
        "Moody Studio Backdrop with Cinematic Key Lighting",
        "Luxury Penthouse Suite",
        "Dark Bedroom / Soft Lamp",
        "Neon Cyberpunk Lounge",
        "Bathroom / Wet Look",
        "Motorcycle / Garage",
        "Red Velvet / Dramatic",
        "Clean White Studio",
        "Sunset Penthouse",
        "Cozy Coffee Shop"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Model Design Studio",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.testTag("avatar_creator_title")
                )
                Text(
                    text = "Design your sexy AI model • Age 18+",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = {
                    viewModel.updateAdvancedAvatarSpec(
                        heightStature = heightOptions.random(),
                        bodyType = bodyTypes.random(),
                        breastSize = breastSizes.random(),
                        waistHipRatio = waistHipRatios.random(),
                        race = races.random(),
                        skinTexture = skinTextures.random(),
                        facialStructure = facialStructures.random(),
                        lipShape = lipShapes.random(),
                        expressionVibe = expressions.random(),
                        makeupStyle = makeupStyles.random(),
                        hairColor = hairColors.random(),
                        hairStyle = hairStyles.random(),
                        currentOutfit = outfits.random(),
                        backgroundVibe = bgVibes.random(),
                        age = (18..35).random(),
                        tattoosAndPiercings = listOf(tattoosOptions.random())
                    )
                },
                modifier = Modifier.testTag("randomize_avatar_button")
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Randomize",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        OutlinedTextField(
            value = advSpec.name,
            onValueChange = { viewModel.updateAdvancedAvatarSpec(name = it) },
            label = { Text("Model Name") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("companion_name_input"),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Age
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Age", style = MaterialTheme.typography.titleMedium)
                Text("${advSpec.age}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            }
            Slider(
                value = advSpec.age.toFloat(),
                onValueChange = { viewModel.updateAdvancedAvatarSpec(age = it.toInt()) },
                valueRange = 18f..45f,
                steps = 26,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("age_slider")
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Live preview
        AvatarGraphicPreview(
            state = avatarState,
            characterName = advSpec.name.ifEmpty { activePersona.name }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ===== NUDE / NO CLOTHES TOGGLE =====
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (advSpec.isNudeEnabled)
                    MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.55f)
                else MaterialTheme.colorScheme.surfaceVariant
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (advSpec.isNudeEnabled) "NO CLOTHES — ACTIVE" else "Clothes On",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = if (advSpec.isNudeEnabled)
                            "Full nude / anatomical mode. Prompt forces detailed body & classical lighting."
                        else
                            "Using selected outfit: ${advSpec.currentOutfit}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = advSpec.isNudeEnabled,
                    onCheckedChange = { viewModel.updateAdvancedAvatarSpec(isNudeEnabled = it) },
                    modifier = Modifier.testTag("nude_mode_toggle")
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Reference image
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Image, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Local Reference Photo", style = MaterialTheme.typography.titleSmall)
                        Text(
                            text = if (!advSpec.referenceImagePath.isNullOrEmpty())
                                advSpec.referenceImagePath!!.substringAfterLast("/")
                            else "None selected",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { photoPickerLauncher.launch("image/*") },
                        modifier = Modifier.weight(1f).testTag("upload_reference_photo_button")
                    ) {
                        Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Select Photo")
                    }
                    if (!advSpec.referenceImagePath.isNullOrEmpty()) {
                        OutlinedButton(onClick = { viewModel.clearLocalReferenceImage() }) {
                            Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Live prompt preview
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.35f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Generation Prompt", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = advSpec.toAbstractedPromptToken(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(10.dp)
                            .testTag("abstracted_payload_token_text")
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Attribute sections
        AttributeCategorySection("Height & Stature", heightOptions, advSpec.heightStature,
            { viewModel.updateAdvancedAvatarSpec(heightStature = it) }, "height_stature")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Body Type", bodyTypes, advSpec.bodyType,
            { viewModel.updateAdvancedAvatarSpec(bodyType = it) }, "body_type")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Breast Size", breastSizes, advSpec.breastSize,
            { viewModel.updateAdvancedAvatarSpec(breastSize = it) }, "breast_size")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Waist-to-Hip", waistHipRatios, advSpec.waistHipRatio,
            { viewModel.updateAdvancedAvatarSpec(waistHipRatio = it) }, "waist_hip_ratio")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Ethnicity / Race", races, advSpec.race,
            { viewModel.updateAdvancedAvatarSpec(race = it) }, "race")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Skin Texture", skinTextures, advSpec.skinTexture,
            { viewModel.updateAdvancedAvatarSpec(skinTexture = it) }, "skin_texture")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Tattoos & Piercings", tattoosOptions,
            advSpec.tattoosAndPiercings.firstOrNull() ?: tattoosOptions[0],
            { viewModel.updateAdvancedAvatarSpec(tattoosAndPiercings = listOf(it)) }, "tattoos_piercings")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Facial Structure", facialStructures, advSpec.facialStructure,
            { viewModel.updateAdvancedAvatarSpec(facialStructure = it) }, "facial_structure")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Lips", lipShapes, advSpec.lipShape,
            { viewModel.updateAdvancedAvatarSpec(lipShape = it) }, "lip_shape")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Expression", expressions, advSpec.expressionVibe,
            { viewModel.updateAdvancedAvatarSpec(expressionVibe = it) }, "expression_vibe")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Makeup", makeupStyles, advSpec.makeupStyle,
            { viewModel.updateAdvancedAvatarSpec(makeupStyle = it) }, "makeup_style")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Hair Style", hairStyles, advSpec.hairStyle,
            { viewModel.updateAdvancedAvatarSpec(hairStyle = it) }, "hair_style")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Hair Color", hairColors, advSpec.hairColor,
            { viewModel.updateAdvancedAvatarSpec(hairColor = it) }, "hair_color")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Outfit", outfits, advSpec.currentOutfit,
            { viewModel.updateAdvancedAvatarSpec(currentOutfit = it) }, "outfit")
        Spacer(modifier = Modifier.height(14.dp))

        AttributeCategorySection("Background / Lighting", bgVibes, advSpec.backgroundVibe,
            { viewModel.updateAdvancedAvatarSpec(backgroundVibe = it) }, "bg_vibe")

        Spacer(modifier = Modifier.height(24.dp))

        // Save button
        Button(
            onClick = { viewModel.updateAdvancedAvatarSpec() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("generate_model_save_button"),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save Model to Studio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Status
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Avatar Spec Saved Locally", style = MaterialTheme.typography.titleSmall)
                    Text(
                        text = advSpec.toShortSummary(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun AttributeCategorySection(
    title: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    testTagPrefix: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = selected.take(28) + if (selected.length > 28) "…" else "",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(options) { item ->
                val isSelected = item == selected
                FilterChip(
                    selected = isSelected,
                    onClick = { onSelect(item) },
                    label = { Text(item) },
                    leadingIcon = if (isSelected) {
                        { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                    } else null,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.testTag("${testTagPrefix}_chip_${item.lowercase().replace(" ", "_").take(20)}")
                )
            }
        }
    }
}
