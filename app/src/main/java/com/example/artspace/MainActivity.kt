package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BotonesFunction()
    }
}

@Composable
private fun CuadroAutor(
    foto: Int,
    nombre: Int,
    autor: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Imagen(foto)
        Textos(nombre, autor)
    }
}

@Composable
private fun Imagen(foto: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(foto),
            contentDescription = null
        )
    }
}

@Composable
private fun Textos(nombre: Int, autor: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(nombre),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "~ " + stringResource(autor) + " ~",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
private fun BotonesFunction() {
    var posicion by remember { mutableStateOf(1) }
    var isButtonNextEnabled by remember { mutableStateOf(true) }
    var isButtonPreviousEnabled by remember { mutableStateOf(false) }

    MostrarObraArte(posicion = posicion)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                onClick = { posicion-- },
                enabled = isButtonPreviousEnabled
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = { posicion++ },
                enabled = isButtonNextEnabled
            )
            {
                Text(text = "Next")
            }
            if (posicion == 1) {
                isButtonPreviousEnabled = false
            } else if (posicion == 5) {
                isButtonNextEnabled = false
            } else {
                isButtonPreviousEnabled = true
                isButtonNextEnabled = true
            }

        }
    }
}

@Composable
private fun MostrarObraArte(
    posicion: Int
) {
    when (posicion) {
        1 -> CuadroAutor(
            foto = R.drawable.gioconda,
            nombre = R.string.la_gioconda,
            autor = R.string.leonardo_da_vinci
        )

        2 -> CuadroAutor(
            foto = R.drawable.nocheestrellada,
            nombre = R.string.la_noche_estrellada,
            autor = R.string.vincent_van_gogh
        )

        3 -> CuadroAutor(
            foto = R.drawable.jovendelaperla,
            nombre = R.string.la_joven_de_la_perla,
            autor = R.string.johannes_vermeer
        )

        4 -> CuadroAutor(
            foto = R.drawable.beso,
            nombre = R.string.el_beso,
            autor = R.string.gustav_klimt
        )

        else -> CuadroAutor(
            foto = R.drawable.meninas,
            nombre = R.string.las_meninas,
            autor = R.string.diego_velazquez
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpace()
}