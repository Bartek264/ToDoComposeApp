package com.example.todocomposeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.ui.theme.MediumGray

@Composable
fun EmptyComponent() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colors.background),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Icon(
			modifier = Modifier.size(120.dp),
			imageVector = ImageVector.vectorResource(id = R.drawable.ic_sad),
			contentDescription = null,
			tint = MediumGray
		)
		Text(
			text = stringResource(id = R.string.no_data_found),
			color = MediumGray,
			fontWeight = FontWeight.Bold,
			fontStyle = MaterialTheme.typography.h6.fontStyle
		)
	}
}

@Preview
@Composable
fun EmptyPreview() {
	EmptyComponent()
}