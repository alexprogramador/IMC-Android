package com.example.myimc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referências aos componentes do layout
        EditText editTextPeso = findViewById(R.id.editText_peso);
        EditText editTextAltura = findViewById(R.id.editText_altura);
        Button buttonCalcular = findViewById(R.id.button_calcular);
        TextView textResultado = findViewById(R.id.text_resultado);

        // Configuração do botão "Calcular"
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obter os valores de peso e altura
                String pesoStr = editTextPeso.getText().toString().trim();
                String alturaStr = editTextAltura.getText().toString().trim();

                // Verificar se os campos estão preenchidos
                if (TextUtils.isEmpty(pesoStr) || TextUtils.isEmpty(alturaStr)) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha ambos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Converter os valores para números
                    double peso = Double.parseDouble(pesoStr);
                    double altura = Double.parseDouble(alturaStr);

                    // Validar se altura é maior que zero
                    if (altura <= 0) {
                        Toast.makeText(MainActivity.this, "Altura deve ser maior que zero.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calcular o IMC
                    double imc = peso / (altura * altura);

                    // Exibir o resultado formatado
                    textResultado.setText(String.format("Resultado: %.2f", imc));

                    // Opcional: Exibir uma mensagem sobre o status do IMC
                    String statusIMC = getStatusIMC(imc);
                    Toast.makeText(MainActivity.this, "IMC: " + statusIMC, Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Método para determinar o status do IMC com base no valor.
     */
    private String getStatusIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }
}
