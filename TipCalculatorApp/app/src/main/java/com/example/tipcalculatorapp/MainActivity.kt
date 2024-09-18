package com.example.tipcalculatorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculatorapp.ui.components.InputField
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import com.example.tipcalculatorapp.ui.utils.calculateTipAmount
import com.example.tipcalculatorapp.ui.utils.calculateTotalPerPerson
import com.example.tipcalculatorapp.ui.widgets.RoundedIconButton
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    App()
}

@Composable
private fun App() {
    TipCalculatorAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Body(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier) {
    val billAmountState = remember {
        mutableStateOf("")
    }
    // https://chatgpt.com/share/66e9d142-e980-8013-ac80-4988c8509ae3
    val billAmountValidState = remember(billAmountState.value) {
        billAmountState.value.trim().isNotEmpty()
    }
    val splitByState = remember {
        mutableIntStateOf(1)
    }
    val sliderPositionState = remember {
        mutableDoubleStateOf(0.0)
    }
    val tipPercentage = remember(sliderPositionState.doubleValue) {
        (sliderPositionState.doubleValue * 100).roundToInt()
    }
    val tipAmountState = remember {
        mutableDoubleStateOf(0.0)
    }
    val totalPerPersonState = remember {
        mutableDoubleStateOf(0.0)
    }

    Column(modifier = modifier.padding(16.dp)) {
        TotalPerPerson(totalPerPersonState.doubleValue)
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                BillAmount(
                    billAmountState = billAmountState,
                    billAmountValidState = billAmountValidState,
                ) { amount ->
                    Log.d("BillTAG", "Amount: $amount")
                }
                if (billAmountValidState) {
                    Split(splitByState)
                    Tip(tipAmountState)
                    TipSlider(
                        sliderPositionState = sliderPositionState,
                        tipPercentage = tipPercentage,
                    )
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        onClick = {
                            updateTipAmountState(tipAmountState, billAmountState, tipPercentage)
                            updateTotalPerPersonState(
                                totalPerPersonState,
                                billAmountState,
                                splitByState,
                                tipPercentage
                            )
                        }
                    ) {
                        Text(
                            text = "Calculate",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Box(modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun TotalPerPerson(amount: Double = 0.0) {
    val formattedAmount = "%.2f".format(amount)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(12.dp)),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "$$formattedAmount",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Composable
private fun BillAmount(
    billAmountState: MutableState<String>,
    billAmountValidState: Boolean,
    onValChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    InputField(
        modifier = Modifier.fillMaxWidth(),
        valueState = billAmountState,
        label = "Bill Amount",
        keyboardType = KeyboardType.Number,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney,
                contentDescription = "Money Icon"
            )
        },
        keyboardActions = KeyboardActions {
            if (!billAmountValidState) {
                return@KeyboardActions
            }
            onValChange(billAmountState.value.trim())
            keyboardController?.hide()
        }
    )
}

@Composable
private fun Split(splitByState: MutableState<Int>) {
    val range = IntRange(start = 1, endInclusive = 10)

    Row(
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Split",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundedIconButton(
                imageVector = Icons.Default.Remove,
                backgroundColor = Color.Red,
                onClick = {
                    Log.d("BillTAG", "Remove Clicked")
                    if (splitByState.value > 1) {
                        splitByState.value -= 1
                    } else {
                        splitByState.value = 1
                    }
                }
            )
            Text(
                splitByState.value.toString(),
                modifier = Modifier.padding(horizontal = 4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            RoundedIconButton(
                imageVector = Icons.Default.Add,
                backgroundColor = Color.Green,
                onClick = {
                    Log.d("BillTAG", "Add Clicked")
                    if (splitByState.value < range.last) {
                        splitByState.value += 1
                    } else {
                        splitByState.value = 10
                    }
                }
            )
        }
    }
}

@Composable
private fun Tip(tipAmountState: MutableState<Double>) {
    val formattedTipAmount = "%.2f".format(tipAmountState.value)

    Row(
        modifier = Modifier.padding(top = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Tip Amount",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "$$formattedTipAmount",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun TipSlider(
    sliderPositionState: MutableState<Double>,
    tipPercentage: Int
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "$tipPercentage %",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider(
            value = sliderPositionState.value.toFloat(),
            onValueChange = {
                Log.d("BillTAG", "Slider: $it")
                sliderPositionState.value = it.toDouble()
            }
        )
    }
}

private fun updateTipAmountState(
    tipAmountState: MutableState<Double>,
    billAmountState: MutableState<String>,
    tipPercentage: Int
) {
    tipAmountState.value = calculateTipAmount(
        billAmountState.value.toDouble(),
        tipPercentage
    )
}

private fun updateTotalPerPersonState(
    totalPerPersonState: MutableState<Double>,
    billAmountState: MutableState<String>,
    splitByState: MutableState<Int>,
    tipPercentage: Int
) {
    totalPerPersonState.value = calculateTotalPerPerson(
        billAmountState.value.toDouble(),
        splitByState.value,
        tipPercentage
    )
}
