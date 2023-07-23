package com.lencodigitexer.sharedpreferences_login_app_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    // Объявление переменных для работы с элементами интерфейса
    TextView textView_name, textView_email;
    Button button_logout;

    // Объект SharedPreferences для сохранения данных
    SharedPreferences sharedPreferences;

    // Ключи для доступа к сохраненным данным в SharedPreferences
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Привязываем переменные к элементам интерфейса
        textView_name = findViewById(R.id.text_fullname);
        textView_email = findViewById(R.id.text_email);
        button_logout = findViewById(R.id.button_logout);

        // Получаем объект SharedPreferences по имени "mypref"
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Получаем сохраненные имя и email из SharedPreferences
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        // Если имя или email не равны null, выводим их на экран
        if (name != null || email != null){
            textView_name.setText("Имя - " + name.toString());
            textView_email.setText("Почта - " + email.toString());
        }

        // Добавляем обработчик клика на кнопку "Выход"
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Получаем редактор SharedPreferences для внесения изменений
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Очищаем все сохраненные данные в SharedPreferences
                editor.clear();

                // Применяем изменения
                editor.commit();

                // Выводим всплывающее уведомление о успешном выходе из аккаунта
                Toast.makeText(HomeActivity.this, "Выход успешно завершён", Toast.LENGTH_SHORT).show();

                // Завершаем текущую активити (экран) и возвращаемся назад
                finish();
            }
        });
    }
}
