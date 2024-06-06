package com.syafi.skinscan.features.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.profile.UserData
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Type

@Composable
fun ProfileHead(userData: UserData?, safeDiagnosed: Int, problemDiagnosed: Int) {

    val totalScanned = (safeDiagnosed + problemDiagnosed).toString()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 55.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = userData?.profilePicture ?: R.drawable.profile_place_holder,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .border(width = 4.dp, color = Base50, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = userData?.name ?: "", style = Type.textsmSemiBold(), color = Neutral50)
        Text(text = userData?.email ?: "", style = Type.textxsRegular(), color = Neutral50)
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            Modifier
                .fillMaxWidth(.85f)
                .background(Neutral50, CircleShape)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProfileStats(value = totalScanned.toString(), title = stringResource(id = R.string.uploaded))
            ProfileStats(value = problemDiagnosed.toString(), title = stringResource(id = R.string.diagnosed))
            ProfileStats(value = safeDiagnosed.toString(), title = stringResource(id = R.string.safe))
        }
    }
}