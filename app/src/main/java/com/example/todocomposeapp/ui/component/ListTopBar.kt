package com.example.todocomposeapp.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.R

@Composable
fun ListTopBar() {
	DefaultTopBar()
}

@Preview(showBackground = true)
@Composable
fun DefaultTopBar() {
	TopAppBar(title = {
		Text(text = stringResource(id = R.string.list_top_bar_title))
	}, backgroundColor = MaterialTheme.colors.primary)

}