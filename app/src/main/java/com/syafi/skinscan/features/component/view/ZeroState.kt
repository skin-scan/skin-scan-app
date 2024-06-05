package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun ZeroState(navController: NavController) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Base50)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier
                    .background(Primary700)
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.still_empty),
                    color = Base50,
                    style = Type.textsmSemiBold()
                )

                Text(
                    text = stringResource(R.string.didnt_scan_yet),
                    color = Base50,
                    style = Type.text2xsMedium(),
                    modifier = Modifier.width(114.dp)
                )

                OutlinedButton(
                    onClick = {
                        navController.navigate(Route.ANALYZE_SCREEN)
                    },
                    border = BorderStroke(width = 1.dp, color = Base50),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(30.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.scan),
                        color = Base50,
                        style = Type.text2xsBold()
                    )
                }
            }
            AsyncImage(
                model = R.drawable.img_zero_state,
                contentDescription = "",
                modifier = Modifier
                    .padding(20.dp)
                    .width(140.dp)
            )
        }
    }
}