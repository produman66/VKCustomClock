
# Тестовое задание от VK. Custom view в виде стрелочных часов 

Автор: [Безруков Владимир](https://drive.google.com/file/d/1A99tjowbGJ3MW2limVrFLC3IuJALrQjB/view)


# Содержание

1. [Скриншоты](#скриншоты)
2. [Использование](#использование)
3. [Статус выполнения задач](#статус-выполнения-задач)
4. [Загрузка](#загрузка)

# Скриншоты

![image 151](https://github.com/produman66/VKCustomClock/assets/115027939/9ca7b06b-c462-4f23-b71a-9dd5f394c97a)
![image 153](https://github.com/produman66/VKCustomClock/assets/115027939/4f122b6a-dd3c-471d-a444-bd0285ccb9f6)

# Использование
-  Пример использования
```xml  
  <com.example.customclockview.customView.ClockCustom
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:clockBorderWidth="10dp"
        app:clockColor="@color/beige"
        app:digitSize="24sp"
    </com.example.customclockview.customView.ClockCustom> 
```

-  Вы можете поменять цвет циферблата
```xml  
  app:clockColor="@color/white"
```
-  Вы можете поменять цвет и ширину рамки циферблата
```xml
   app:clockBorderColor="@color/gray"
   app:clockBorderWidth="2dp"
```
-  Вы можете поменять цвет и размер цифр на циферблате 
```xml
   app:digitColor="@color/gray"
   app:digitSize="12sp"
```

-  Вы можете поменять цвет и размер стрелок на циферблате 
```xml
   app:secondHandSize="50dp"
   app:secondHandColor="@color/gray"

   app:minuteHandSize="40dp"
   app:minuteHandColor="@color/gray"

   app:hourHandSize="30dp"
   app:minuteHandColor="@color/gray"
```

-  Вы можете поменять цвет и размер меток, обозначающих минуты на часах 
```xml
   app:minuteDotsColor="@color/gray"
   app:minuteDotsSize="1dp"
```  


# Статус выполнения задач

| Задание                                                                              | Готовность    |
| ------------------------------------------------------------------------------------ | ------------- |
| Сохранение состояния при смене  конфигурации устройства                              | Готово        |
| Когда бы не включить приложение, часа показывают правильное время                    | Готово        |
| Легкая интеграция часов в любой экран. Как через xml layout так и через код          | Готово        |
| Не использовать Jetpack Compose UI                                                   | Готово        |
| Для демонстрации работы сделать запускаемое приложение с примерами экранов с часами  | Готово        |

# Загрузка

Скачать [.apk](https://github.com/produman66/VKApp/releases/tag/FirstRelease) файл на андроид устройство




