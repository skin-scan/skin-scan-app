package com.syafi.skinscan.features.component.detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.detection.detail.DetailedDetection
import com.syafi.skinscan.features.component.detail.ResultDetailViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary100
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Secondary700
import com.syafi.skinscan.ui.theme.Type
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ResultDetailContent(

    detectionData: DetailedDetection?,
    viewModel: ResultDetailViewModel,
    modifier: Modifier = Modifier
) {


    LazyColumn(
        modifier = modifier
    ) {
        item {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Primary100),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = detectionData?.title ?: "",
                    style = Type.textmdSemiBold(),
                    color = Primary700,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Neutral50)
                        .padding(20.dp),
                ) {
                    Text(
                        text = stringResource(R.string.medical_name),
                        style = Type.textsmSemiBold()
                    )
                    Text(text = detectionData?.medicalName ?: "", style = Type.textsmRegular())
                    Spacer(modifier = Modifier.height(16.dp))

                    if (detectionData?.commonName != null) {
                        if (detectionData.commonName.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.common_name),
                                style = Type.textsmSemiBold()
                            )
                            Text(text = detectionData.commonName, style = Type.textsmRegular())
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    Text(
                        text = stringResource(R.string.risk_assessment), style = Type
                            .textsmSemiBold()
                    )
                    Text(
                        text = detectionData?.assessment.toString(),
                        style = Type.textsmRegular()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.time_detection),
                        style = Type.textsmSemiBold()
                    )
                    Text(
                        text =
                        if (detectionData?.createdAt != null) {
                            formatDate(detectionData.createdAt)
                        } else {
                            ""
                        },
                        style = Type.textsmRegular()
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    viewModel.setDeleteDialogState(true)
                },
                border = BorderStroke(width = 2.dp, color = Secondary700),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.delete),
                    color = Secondary700,
                    style = Type.textsmBold()
                )
            }
            Spacer(modifier = Modifier.height(28.dp))

        }
    }
}

private fun formatDate(timeStamp: String): String {
    val zonedDateTime = ZonedDateTime.parse(timeStamp, DateTimeFormatter.ISO_DATE_TIME)
    val customFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    return zonedDateTime.format(customFormatter)
}