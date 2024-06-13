package com.syafi.skinscan.features.analyze.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Secondary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun GuidelineCard(modifier: Modifier = Modifier) {

    val guideline = stringArrayResource(id = R.array.guideline)

    Card(elevation = CardDefaults.elevatedCardElevation(6.dp)) {
        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.guideline),
                style = Type.textmdSemiBold(),
                color = Primary700,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Neutral50, RoundedCornerShape(10.dp))
                    .padding(20.dp)
            ) {
                guideline.forEachIndexed { index, guidelineText ->
                    when (index) {
                        0 -> {
                            val boldLighting = stringResource(R.string.lighting)
                            val boldGood = stringResource(R.string.good)

                            GetTextWithBoldWords(
                                sentence = guidelineText,
                                index = index,
                                boldLighting, boldGood
                            )
                        }

                        1 -> {
                            val bold10Cm = stringResource(R.string._10_cm)
                            GetTextWithBoldWords(sentence = guidelineText, index = index, bold10Cm)
                        }

                        2 -> {
                            val boldFocus = stringResource(R.string.focus)
                            val boldCentered = stringResource(R.string.centered)

                            GetTextWithBoldWords(
                                sentence = guidelineText,
                                index = index,
                                boldFocus, boldCentered
                            )
                        }

                        3 -> {
                            val boldCloseup = stringResource(R.string.close_up)
                            val boldMostRepresentative =
                                stringResource(R.string.most_representatove)

                            GetTextWithBoldWords(
                                sentence = guidelineText,
                                index = index,
                                boldCloseup, boldMostRepresentative
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GetTextWithBoldWords(sentence: String, index: Int, vararg words: String) {

    val spanStyles: MutableList<AnnotatedString.Range<SpanStyle>> = mutableListOf()


    words.forEach {
        val start = sentence.indexOf(it)

        spanStyles.add(
            AnnotatedString.Range(
                SpanStyle(fontWeight = FontWeight.Bold, color = Secondary700),
                start = start,
                end = start + it.length
            )
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "${index + 1}.", style = Type.textsmRegular())
        Text(
            text = AnnotatedString(text = sentence, spanStyles = spanStyles.toList()),
            style = Type.textsmRegular()
        )
    }
}