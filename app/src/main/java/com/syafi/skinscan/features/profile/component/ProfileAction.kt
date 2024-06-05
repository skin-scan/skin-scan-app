package com.syafi.skinscan.features.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Secondary900
import com.syafi.skinscan.ui.theme.Type

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAction(
    icon: Int,
    action: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = { onClick() }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(model = icon, contentDescription = action, modifier = Modifier.size(30
                    .dp))
                Text(
                    text = action,
                    style = Type.textsmMedium(),
                    color =
                        if (action.equals(stringResource(id = R.string.logout))) {
                            Secondary900
                        } else {
                            Neutral900
                        }
                )
            }
            
            if (!action.equals(stringResource(id = R.string.logout))) {
                IconButton(onClick = { onClick() }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = action,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
}