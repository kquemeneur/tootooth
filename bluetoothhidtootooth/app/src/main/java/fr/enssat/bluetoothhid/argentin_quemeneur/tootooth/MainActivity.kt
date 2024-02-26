package fr.enssat.bluetoothhid.argentin_quemeneur.tootooth

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.enssat.bluetoothhid.argentin_quemeneur.tootooth.ThemeColors

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var bluetoothController: BluetoothController

    private fun ensureBluetoothPermission(activity: ComponentActivity) {
        val requestPermissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted: Boolean ->
                if (isGranted) {Log.d(TAG, "Bluetooth connection granted")
                } else { Log.e(TAG, "Bluetooth connection not granted, Bye!")
                         activity.finish()
                }
        }

        requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        ensureBluetoothPermission(this)

        bluetoothController = BluetoothController()

        setContent {

                Surface(modifier = Modifier.fillMaxSize(), color = ThemeColors.background) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BluetoothUiConnection(bluetoothController)
                        BluetoothDesk(bluetoothController)
                    }
                }
        }
    }

    override fun onPause() {
        super.onPause()
        bluetoothController.release()
    }
}

typealias KeyModifier = Int