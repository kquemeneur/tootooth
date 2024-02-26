package fr.enssat.bluetoothhid.argentin_quemeneur.tootooth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val keyboardAlphaMapping = listOf(
    Pair('a', KeyEvent.KEYCODE_A),
    Pair('z', KeyEvent.KEYCODE_Z),
    Pair('e', KeyEvent.KEYCODE_E),
    Pair('r', KeyEvent.KEYCODE_R),
    Pair('t', KeyEvent.KEYCODE_T),
    Pair('y', KeyEvent.KEYCODE_Y),
    Pair('u', KeyEvent.KEYCODE_U),
    Pair('i', KeyEvent.KEYCODE_I),
    Pair('o', KeyEvent.KEYCODE_O),
    Pair('p', KeyEvent.KEYCODE_P),
    Pair('q', KeyEvent.KEYCODE_Q),
    Pair('s', KeyEvent.KEYCODE_S),
    Pair('d', KeyEvent.KEYCODE_D),
    Pair('f', KeyEvent.KEYCODE_F),
    Pair('g', KeyEvent.KEYCODE_G),
    Pair('h', KeyEvent.KEYCODE_H),
    Pair('j', KeyEvent.KEYCODE_J),
    Pair('k', KeyEvent.KEYCODE_K),
    Pair('l', KeyEvent.KEYCODE_L),
    Pair('m', KeyEvent.KEYCODE_M),
    Pair('w', KeyEvent.KEYCODE_W),
    Pair('x', KeyEvent.KEYCODE_X),
    Pair('c', KeyEvent.KEYCODE_C),
    Pair('v', KeyEvent.KEYCODE_V),
    Pair('b', KeyEvent.KEYCODE_B),
    Pair('n', KeyEvent.KEYCODE_N),
)

val keyboardNumericMapping = listOf(
    Pair('0', KeyEvent.KEYCODE_0),
    Pair('1', KeyEvent.KEYCODE_1),
    Pair('2', KeyEvent.KEYCODE_2),
    Pair('3', KeyEvent.KEYCODE_3),
    Pair('4', KeyEvent.KEYCODE_4),
    Pair('5', KeyEvent.KEYCODE_5),
    Pair('6', KeyEvent.KEYCODE_6),
    Pair('7', KeyEvent.KEYCODE_7),
    Pair('8', KeyEvent.KEYCODE_8),
    Pair('9', KeyEvent.KEYCODE_9)
)
@Composable
fun Keyboard(onKeyPress: (Int) -> Unit) {
    Column {
        val alphaRows = keyboardAlphaMapping.chunked(6)
        alphaRows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                for ((char, keyCode) in row) {
                    KeyboardButton(
                        text = char.toString(),
                        onClick = { onKeyPress(keyCode) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        val numericRows = keyboardNumericMapping.chunked(5)
        numericRows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                for ((char, keyCode) in row) {
                    KeyboardButton(
                        text = char.toString(),
                        onClick = { onKeyPress(keyCode) }
                    )
                }
            }
        }
    }
}

@Composable
fun KeyboardButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(45.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentPadding = PaddingValues(0.dp),
        shape = MaterialTheme.shapes.small

    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}