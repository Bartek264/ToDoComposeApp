package com.example.todocomposeapp.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.todocomposeapp.R

@Composable
fun CustomAlertDialog(
	title: String,
	message: String,
	openDialog: Boolean,
	closeDialog: () -> Unit,
	onYesClicked: () -> Unit
) {

	if (openDialog) {
		AlertDialog(
			title = {
				Text(
					text = title,
					fontSize = MaterialTheme.typography.h5.fontSize,
					fontWeight = FontWeight.Bold
				)
			}, text = {
				Text(
					text = message,
					fontSize = MaterialTheme.typography.subtitle1.fontSize,
					fontWeight = FontWeight.Normal
				)
			},
			confirmButton = { TextButton(onClick = {
				onYesClicked()
				closeDialog()
			}) {
				Text(text = stringResource(id = R.string.yes))
			}},
			dismissButton = { TextButton(onClick = { closeDialog() }) {
				Text(text = stringResource(id = R.string.no))
			} },
			onDismissRequest = {
				closeDialog()

			}
		)
	}

}