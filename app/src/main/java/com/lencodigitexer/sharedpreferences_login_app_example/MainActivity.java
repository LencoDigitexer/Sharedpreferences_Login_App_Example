package com.lencodigitexer.sharedpreferences_login_app_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Объявление переменных для работы с элементами интерфейса
    EditText editText_name, editText_email;
    Button button_save;

    // Объект SharedPreferences для сохранения данных
    SharedPreferences sharedPreferences;

    // Ключи для сохранения данных в SharedPreferences
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получение объекта SharedPreferences по имени "mypref"
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Проверяем, сохранено ли имя в SharedPreferences
        String name = sharedPreferences.getString(KEY_NAME, null);

        // Если имя уже сохранено, переходим на экран HomeActivity
        if (name != null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            // Если имя не сохранено, отображаем макет activity_main
            setContentView(R.layout.activity_main);

            // Привязываем переменные к элементам интерфейса
            editText_name = findViewById(R.id.edittext_name);
            editText_email = findViewById(R.id.edittext_email);
            button_save = findViewById(R.id.button_save);

            // Добавляем обработчик клика на кнопку "Сохранить"
            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Получаем редактор SharedPreferences для внесения изменений
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    // Сохраняем введенные имя и email в SharedPreferences
                    editor.putString(KEY_NAME, editText_name.getText().toString());
                    editor.putString(KEY_EMAIL, editText_email.getText().toString());

                    // Применяем изменения
                    editor.apply();

                    // Переходим на экран HomeActivity
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);

                    // Выводим всплывающее уведомление об успешной авторизации
                    Toast.makeText(MainActivity.this, "Успешная авторизация", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
