package com.example.raithavarta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.room.Room
import com.example.raithavarta.data.local.AppDatabase
import com.example.raithavarta.data.local.TipEntity
import com.example.raithavarta.data.repository.TipRepository
import com.example.raithavarta.ui.screens.HomeScreen
import com.example.raithavarta.ui.screens.LanguageScreen
import com.example.raithavarta.ui.screens.WelcomeScreen
import com.example.raithavarta.utils.LocaleHelper
import com.example.raithavarta.utils.PreferenceManager
import com.example.raithavarta.viewmodel.TipViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val prefManager = PreferenceManager(this)

        val language = prefManager.getLanguage()

        LocaleHelper.setLocale(this, language)

        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tips_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        val repository = TipRepository(db.tipDao())

        val viewModel = TipViewModel(repository)

        fun loadTips(selectedLanguage: String) {

            CoroutineScope(Dispatchers.IO).launch {

                val tips = when (selectedLanguage) {

                    "kn" -> listOf(

                        TipEntity(
                            title = "ನೆಲದ ನೀರಿನ ನಿರ್ವಹಣೆ",
                            description = "ಮಳೆಯ ದಿನಗಳಲ್ಲಿ ಹೆಚ್ಚು ನೀರು ಹಾಕಬೇಡಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1501004318641-b39e6451bec6",
                            cropType = "ನೆಲ"
                        ),

                        TipEntity(
                            title = "ಟೊಮ್ಯಾಟೊ ಕೀಟ ಎಚ್ಚರಿಕೆ",
                            description = "ಸಂಜೆ ವೇಳೆ ನೀಮ್ ಎಣ್ಣೆ ಸಿಂಪಡಿಸಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea",
                            cropType = "ಟೊಮ್ಯಾಟೊ"
                        ),

                        TipEntity(
                            title = "ತೆಂಗಿನ ಮರದ ಆರೈಕೆ",
                            description = "ವಾರಕ್ಕೊಮ್ಮೆ ನೀರಾವರಿ ಮಾಡಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1502741338009-cac2772e18bc",
                            cropType = "ತೆಂಗು"
                        ),

                        TipEntity(
                            title = "ಬಾಳೆ ಕೃಷಿ",
                            description = "ಉತ್ತಮ ಬೆಳವಣಿಗೆಗೆ ಡ್ರಿಪ್ ನೀರಾವರಿ ಬಳಸಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1574226516831-e1dff420e37f",
                            cropType = "ಬಾಳೆ"
                        ),

                        TipEntity(
                            title = "ಕಾಫಿ ತೋಟ",
                            description = "ನೆರಳು ಮರಗಳನ್ನು ಸರಿಯಾಗಿ ನಿರ್ವಹಿಸಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
                            cropType = "ಕಾಫಿ"
                        ),

                        TipEntity(
                            title = "ಹತ್ತಿ ರಕ್ಷಣೆ",
                            description = "ಪ್ರತಿ ವಾರ ಕೀಟಗಳನ್ನು ಪರಿಶೀಲಿಸಿ.",
                            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
                            cropType = "ಹತ್ತಿ"
                        )
                    )

                    "hi" -> listOf(

                        TipEntity(
                            title = "धान जल प्रबंधन",
                            description = "बरसात के दिनों में अधिक पानी न दें।",
                            imageUrl = "https://images.unsplash.com/photo-1501004318641-b39e6451bec6",
                            cropType = "धान"
                        ),

                        TipEntity(
                            title = "टमाटर कीट चेतावनी",
                            description = "शाम के समय नीम तेल छिड़कें।",
                            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea",
                            cropType = "टमाटर"
                        ),

                        TipEntity(
                            title = "नारियल पेड़ देखभाल",
                            description = "साप्ताहिक सिंचाई सुनिश्चित करें।",
                            imageUrl = "https://images.unsplash.com/photo-1502741338009-cac2772e18bc",
                            cropType = "नारियल"
                        ),

                        TipEntity(
                            title = "केला खेती",
                            description = "बेहतर वृद्धि के लिए ड्रिप सिंचाई करें।",
                            imageUrl = "https://images.unsplash.com/photo-1574226516831-e1dff420e37f",
                            cropType = "केला"
                        ),

                        TipEntity(
                            title = "कॉफी बागान",
                            description = "छायादार पेड़ों का रखरखाव करें।",
                            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
                            cropType = "कॉफी"
                        ),

                        TipEntity(
                            title = "कपास सुरक्षा",
                            description = "हर सप्ताह कीटों की निगरानी करें।",
                            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
                            cropType = "कपास"
                        )
                    )

                    "te" -> listOf(

                        TipEntity(
                            title = "వరి నీటి నిర్వహణ",
                            description = "వర్షపు రోజుల్లో అధిక నీరు ఇవ్వవద్దు.",
                            imageUrl = "https://images.unsplash.com/photo-1501004318641-b39e6451bec6",
                            cropType = "వరి"
                        ),

                        TipEntity(
                            title = "టమోటా పురుగు హెచ్చరిక",
                            description = "సాయంత్రం సమయంలో వేపనూనె పిచికారీ చేయండి.",
                            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea",
                            cropType = "టమోటా"
                        ),

                        TipEntity(
                            title = "కొబ్బరి చెట్టు సంరక్షణ",
                            description = "ప్రతి వారం నీటిపారుదల చేయండి.",
                            imageUrl = "https://images.unsplash.com/photo-1502741338009-cac2772e18bc",
                            cropType = "కొబ్బరి"
                        ),

                        TipEntity(
                            title = "అరటి సాగు",
                            description = "మెరుగైన పెరుగుదలకు డ్రిప్ ఇరిగేషన్ ఉపయోగించండి.",
                            imageUrl = "https://images.unsplash.com/photo-1574226516831-e1dff420e37f",
                            cropType = "అరటి"
                        ),

                        TipEntity(
                            title = "కాఫీ తోట",
                            description = "నీడిచ్చే చెట్లను సంరక్షించండి.",
                            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
                            cropType = "కాఫీ"
                        ),

                        TipEntity(
                            title = "పత్తి రక్షణ",
                            description = "ప్రతి వారం పురుగులను పరిశీలించండి.",
                            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
                            cropType = "పత్తి"
                        )
                    )

                    "ta" -> listOf(

                        TipEntity(
                            title = "நெல் நீர் மேலாண்மை",
                            description = "மழைக்காலத்தில் அதிக நீர் விட வேண்டாம்.",
                            imageUrl = "https://images.unsplash.com/photo-1501004318641-b39e6451bec6",
                            cropType = "நெல்"
                        ),

                        TipEntity(
                            title = "தக்காளி பூச்சி எச்சரிக்கை",
                            description = "மாலை நேரத்தில் வேப்பெண்ணெய் தெளிக்கவும்.",
                            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea",
                            cropType = "தக்காளி"
                        ),

                        TipEntity(
                            title = "தேங்காய் மர பராமரிப்பு",
                            description = "வாரத்திற்கு ஒருமுறை நீர்ப்பாசனம் செய்யவும்.",
                            imageUrl = "https://images.unsplash.com/photo-1502741338009-cac2772e18bc",
                            cropType = "தேங்காய்"
                        ),

                        TipEntity(
                            title = "வாழை விவசாயம்",
                            description = "சிறந்த வளர்ச்சிக்கு டிரிப் நீர்ப்பாசனம் பயன்படுத்தவும்.",
                            imageUrl = "https://images.unsplash.com/photo-1574226516831-e1dff420e37f",
                            cropType = "வாழை"
                        ),

                        TipEntity(
                            title = "காபி தோட்டம்",
                            description = "நிழல் மரங்களை பராமரிக்கவும்.",
                            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
                            cropType = "காபி"
                        ),

                        TipEntity(
                            title = "பருத்தி பாதுகாப்பு",
                            description = "ஒவ்வொரு வாரமும் பூச்சிகளை கண்காணிக்கவும்.",
                            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
                            cropType = "பருத்தி"
                        )
                    )

                    else -> listOf(

                        TipEntity(
                            title = "Paddy Water Management",
                            description = "Avoid overwatering during rainy days.",
                            imageUrl = "https://images.unsplash.com/photo-1501004318641-b39e6451bec6",
                            cropType = "Paddy"
                        ),

                        TipEntity(
                            title = "Tomato Pest Alert",
                            description = "Spray neem oil during evening hours.",
                            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea",
                            cropType = "Tomato"
                        ),

                        TipEntity(
                            title = "Coconut Tree Care",
                            description = "Ensure proper irrigation weekly.",
                            imageUrl = "https://images.unsplash.com/photo-1502741338009-cac2772e18bc",
                            cropType = "Coconut"
                        ),

                        TipEntity(
                            title = "Banana Farming",
                            description = "Use drip irrigation for better growth.",
                            imageUrl = "https://images.unsplash.com/photo-1574226516831-e1dff420e37f",
                            cropType = "Banana"
                        ),

                        TipEntity(
                            title = "Coffee Plantation",
                            description = "Maintain shade trees properly.",
                            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
                            cropType = "Coffee"
                        ),

                        TipEntity(
                            title = "Cotton Protection",
                            description = "Monitor pests every week.",
                            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
                            cropType = "Cotton"
                        )
                    )
                }

                repository.deleteAllTips()

                repository.insertTips(tips)

                viewModel.loadTips()
            }
        }

        setContent {

            var currentScreen by remember {
                mutableStateOf("welcome")
            }

            when (currentScreen) {

                "welcome" -> {

                    WelcomeScreen(

                        onContinue = {

                            currentScreen = "language"
                        }
                    )
                }

                "language" -> {

                    LanguageScreen(

                        onLanguageSelected = { selectedLanguage ->

                            prefManager.saveLanguage(selectedLanguage)

                            loadTips(selectedLanguage)

                            currentScreen = "home"
                        }
                    )
                }

                "home" -> {

                    HomeScreen(viewModel)
                }
            }
        }
    }
}