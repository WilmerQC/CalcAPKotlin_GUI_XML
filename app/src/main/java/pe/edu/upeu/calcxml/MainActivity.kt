package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // EditText para mostrar el resultado
    private lateinit var valorResultado: EditText
    // Operador seleccionado (+ o -)
    private var operador = ""
    // Número actual ingresado
    private var numActual = 0.0
    // Número anterior ingresado
    private var numAnt = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el diseño edge-to-edge
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Asigna el EditText desde el layout
        valorResultado = findViewById(R.id.txtResult)

        // Ajusta los insets de la ventana para el diseño edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configura los botones de la calculadora
        setupButtons()
    }

    // Método para configurar los botones
    fun setupButtons() {
        // Array con los IDs de los botones
        val buttons = arrayOf(
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn0, R.id.btnPunto,
            R.id.btnDivicion, R.id.btnMultiplicar, R.id.btnRestar, R.id.btnSumar, R.id.btnIgual, R.id.btnBorrar
        )
        // Asigna el listener a cada botón
        for (button in buttons) {
            val bottonx = findViewById<Button>(button)
            bottonx.setOnClickListener { onButtonClick(bottonx) }
        }
    }

    // Método que maneja los clics en los botones
    fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn0, R.id.btnPunto -> {
                // Añade el número ingresado al EditText
                val bottonx = findViewById<Button>(view.id)
                appendToInput(bottonx.text.toString())
            }
            R.id.btnDivicion, R.id.btnMultiplicar, R.id.btnRestar, R.id.btnSumar -> {
                // Establece el operador
                val bottonx = findViewById<Button>(view.id)
                setOperador(bottonx.text.toString())
            }
            R.id.btnIgual -> {
                // Calcula el resultado
                calcularResultado()
            }
            R.id.btnBorrar -> {
                // Borra el contenido del EditText y resetea las variables
                valorResultado.text.clear()
                operador = ""
                numActual = 0.0
                numAnt = 0.0
            }
        }
    }

    // Añade un valor al EditText
    fun appendToInput(valor: String) {
        valorResultado.append(valor)
    }

    // Establece el operador y almacena el número anterior
    fun setOperador(oper: String) {
        operador = oper
        numAnt = valorResultado.text.toString().toDouble()
        valorResultado.text.clear()
    }

    // Calcula el resultado según el operador seleccionado
    fun calcularResultado() {
        numActual = valorResultado.text.toString().toDouble()
        val result = when (operador) {
            "/" -> numAnt / numActual
            "x" -> numAnt * numActual
            "-" -> numAnt - numActual
            "+" -> numAnt + numActual
            else -> numAnt
        }
        valorResultado.setText(result.toString())
        operador = ""
    }
}