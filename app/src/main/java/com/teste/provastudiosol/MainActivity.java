package com.teste.provastudiosol;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21;
private EditText palpiteDigitado;
private Button btnEnviar, btnNovaPartida;
private TextView textview, contador;
private Integer NumeroReal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);
        img10 = findViewById(R.id.imageView10);
        img11 = findViewById(R.id.imageView11);
        img12 = findViewById(R.id.imageView12);
        img13 = findViewById(R.id.imageView13);
        img14 = findViewById(R.id.imageView14);
        img15 = findViewById(R.id.imageView15);
        img16 = findViewById(R.id.imageView16);
        img17 = findViewById(R.id.imageView17);
        img18 = findViewById(R.id.imageView18);
        img19 = findViewById(R.id.imageView19);
        img20 = findViewById(R.id.imageView20);
        img21 = findViewById(R.id.imageView21);
        palpiteDigitado = findViewById(R.id.editTextPalpite);
        btnEnviar = findViewById(R.id.buttonEnviar);
        btnNovaPartida= findViewById(R.id.buttonNovaPartida);
        textview = findViewById(R.id.textView);
        contador = findViewById(R.id.contadorID);
        NumeroReal = -1;
        contador.setText("0");

    /**
        *Requisição do número ao webservice ao abrir a Activity
    */
        String Resultado = null;
        try {
            Resultado = new HttpConnection().execute("").get();
        } catch (Exception ex) {
            textview.setText("error");
        }
        Resultado = Resultado.replace("{\"value\":","").replace("}","");
        NumeroReal = Integer.parseInt(Resultado);

        btnNovaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaNumeroweb();
                btnNovaPartida.setVisibility(View.INVISIBLE);
                contador.setText("0");
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (palpiteDigitado.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Digite um palpite de 1 à 300.", Toast.LENGTH_SHORT).show();
                    } else {
                        int numPalpite = Integer.parseInt(palpiteDigitado.getText().toString());
                        String caracter1 = null;
                        String caracter2 = null;
                        String caracter3 = null;
                        if (numPalpite > 300 || numPalpite < 1) {
                            Toast.makeText(getApplicationContext(), "Digite um palpite de 1 à 300.", Toast.LENGTH_SHORT).show();
                        } else {
                            /**
                             * Verifica se o número digitado possui 3 caracteres, senão houver 3 caracteres.
                             * Em seguida, separo esse número em 3 caracteres para serem enviados cada um no parametro da função de ligar o LED, ligaLED()
                             * Após isso verifico se o palpite digitado está correto, ou menor, ou maior do que o número obtido pelo webservice.
                            */
                            String string = palpiteDigitado.getText().toString();
                           while (string.length() < 3) {
                                string = "0" + string;
                            }

                            String[] splitt = string.split("");
                            caracter1 = splitt[0].toString();
                            caracter2 = splitt[1].toString();
                            caracter3 = splitt[2].toString();
                            if (Integer.parseInt(caracter1) == 0 && Integer.parseInt(caracter2) == 0) {
                                caracter1 = "-1";
                                caracter2 = "-1";
                            }

                            ligaLed(caracter1, caracter2, caracter3);


                            if (numPalpite == NumeroReal) {
                                textview.setText("Acertou!");
                                btnNovaPartida.setVisibility(View.VISIBLE);
                            } else if (numPalpite > NumeroReal) {
                                textview.setText("É menor");
                            } else if (numPalpite < NumeroReal) {
                                textview.setText("É maior");
                            }


                            /**Informa o número de tentativas realizadas no campo.
                            */
                            int aux = Integer.parseInt(contador.getText().toString().replace("º tentativa.", "")) + 1;
                            String a = aux + "º tentativa.";
                            contador.setText(a);
                        }
                    }

                }
        });
    }
    private void buscaNumeroweb() {
        String Resultado = null;
        try {
            Resultado = new HttpConnection().execute("").get();
        } catch (Exception ex) {
            textview.setText("error");
            ligaLed("5","0","2");
        }
        Resultado = Resultado.replace("{\"value\":","").replace("}","");
        NumeroReal = Integer.parseInt(Resultado);
    }

    private void ligaLed(String caracter1, String caracter2, String caracter3) {
        /**
         * Para ligar o LED, criei cada seguimento do número como uma imagem. Criei um formato de cor padrão indicando que o seguimento está desligado, e outro formato com uma cor viva, indicando que o seguimento está ligado.
         * E para cada número que é recebido, usei um switch/case de 0 à 9, indicando qual seguimento do número estaria com a cor ligada e desligada.
         */
        int num1 = Integer.parseInt(caracter1);
        int num2 = Integer.parseInt(caracter2);
        int num3 = Integer.parseInt(caracter3);

        switch (num1) {
            case 0:
                img1.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformatdefault);
                img3.setImageResource(R.drawable.numberformatdefault);
                img4.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformatdefault);
                break;
            case 1:
                img1.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformatdefault);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                break;
            case 2:
                img1.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformat);
                img6.setImageResource(R.drawable.numberformat);
                img5.setImageResource(R.drawable.numberformat);
                break;
            case 3:
                img1.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img7.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                img5.setImageResource(R.drawable.numberformat);
                break;
            case 4:
                img1.setImageResource(R.drawable.numberformat);
                img2.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                break;
            case 5:
                img3.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformat);
                img1.setImageResource(R.drawable.numberformat);
                img7.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                img5.setImageResource(R.drawable.numberformat);
                break;
            case 6:
                img3.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformat);
                img1.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                img5.setImageResource(R.drawable.numberformat);
                img6.setImageResource(R.drawable.numberformat);
                img7.setImageResource(R.drawable.numberformat);
                break;
            case 7:
                img1.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                break;
            case 8:
                img1.setImageResource(R.drawable.numberformat);
                img2.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                img5.setImageResource(R.drawable.numberformat);
                img6.setImageResource(R.drawable.numberformat);
                img7.setImageResource(R.drawable.numberformat);
                break;
            case 9:
                img6.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img1.setImageResource(R.drawable.numberformat);
                img2.setImageResource(R.drawable.numberformat);
                img3.setImageResource(R.drawable.numberformat);
                img4.setImageResource(R.drawable.numberformat);
                img7.setImageResource(R.drawable.numberformat);
                break;
            default:
                img1.setImageResource(R.drawable.numberformatdefault);
                img2.setImageResource(R.drawable.numberformatdefault);
                img3.setImageResource(R.drawable.numberformatdefault);
                img4.setImageResource(R.drawable.numberformatdefault);
                img5.setImageResource(R.drawable.numberformatdefault);
                img6.setImageResource(R.drawable.numberformatdefault);
                img7.setImageResource(R.drawable.numberformatdefault);
        }
        switch (num2) {
            case 0:
                img14.setImageResource(R.drawable.numberformatdefault);
                img8.setImageResource(R.drawable.numberformat);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                img13.setImageResource(R.drawable.numberformat);
                break;
            case 1:
                img8.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformatdefault);
                img12.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img14.setImageResource(R.drawable.numberformatdefault);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                break;
            case 2:
                img8.setImageResource(R.drawable.numberformatdefault);
                img11.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                img13.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                break;
            case 3:
                img8.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                break;
            case 4:
                img9.setImageResource(R.drawable.numberformatdefault);
                img12.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img8.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                break;
            case 5:
                img10.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformat);
                img8.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                break;
            case 6:
                img10.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformat);
                img8.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                img13.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                break;
            case 7:
                img8.setImageResource(R.drawable.numberformatdefault);
                img12.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img14.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                break;
            case 8:
                img8.setImageResource(R.drawable.numberformat);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img12.setImageResource(R.drawable.numberformat);
                img13.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                break;
            case 9:
                img12.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img8.setImageResource(R.drawable.numberformat);
                img9.setImageResource(R.drawable.numberformat);
                img10.setImageResource(R.drawable.numberformat);
                img11.setImageResource(R.drawable.numberformat);
                img14.setImageResource(R.drawable.numberformat);
                break;
            default:
                img12.setImageResource(R.drawable.numberformatdefault);
                img13.setImageResource(R.drawable.numberformatdefault);
                img9.setImageResource(R.drawable.numberformatdefault);
                img8.setImageResource(R.drawable.numberformatdefault);
                img10.setImageResource(R.drawable.numberformatdefault);
                img11.setImageResource(R.drawable.numberformatdefault);
                img14.setImageResource(R.drawable.numberformatdefault);
        }
        switch (num3) {
            case 0:
                img21.setImageResource(R.drawable.numberformatdefault);
                img15.setImageResource(R.drawable.numberformat);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                img20.setImageResource(R.drawable.numberformat);
                break;
            case 1:
                img15.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformatdefault);
                img19.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img21.setImageResource(R.drawable.numberformatdefault);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                break;
            case 2:
                img15.setImageResource(R.drawable.numberformatdefault);
                img18.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                img20.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                break;
            case 3:
                img15.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                break;
            case 4:
                img16.setImageResource(R.drawable.numberformatdefault);
                img19.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img15.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                break;
            case 5:
                img17.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformat);
                img15.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                break;
            case 6:
                img17.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformat);
                img15.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                img20.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                break;
            case 7:
                img15.setImageResource(R.drawable.numberformatdefault);
                img19.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img21.setImageResource(R.drawable.numberformatdefault);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                break;
            case 8:
                img15.setImageResource(R.drawable.numberformat);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img19.setImageResource(R.drawable.numberformat);
                img20.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                break;
            case 9:
                img19.setImageResource(R.drawable.numberformatdefault);
                img20.setImageResource(R.drawable.numberformatdefault);
                img15.setImageResource(R.drawable.numberformat);
                img16.setImageResource(R.drawable.numberformat);
                img17.setImageResource(R.drawable.numberformat);
                img18.setImageResource(R.drawable.numberformat);
                img21.setImageResource(R.drawable.numberformat);
                break;
            default:
        }

    }
}