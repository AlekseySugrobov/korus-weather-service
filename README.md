    Тестовое задание следующее: написать веб приложение для отображения погоды.
    Приложение должно позволять пользователям узнавать текущую погоду для конкретного
    города или локации (долгота/широта) (не обязательно реальную, это могут быть
    случайные данные - температура, ветер, давление... или данные с
    http://openweathermap.org/api).
    Реализовать страницу для ввода поисковой информации (например попросить
    пользователя ввести город), так же страницу для отображения результатов поиска по
    запросу.
    
    Дополнительные  условия (не обязательно, но желательно):
    
    Приложение должно обрабатывать только один запрос за раз. Т.е. если несколько
    пользователей &quot;одновременно&quot; выполнили поиск, их запросы становятся в очередь и
    выполняются друг за другом. Пользователь должен информироваться о задержке и
    автоматический перекидываться к результатам поиска, когда до него дойдет очередь.
    Подумайте как это можно сделать эффективней, пользователей может быть очень много.
    Добавьте авторизацию для пользователей и статистику их работы с приложением: когда
    зашел, какой запрос ввел, что получил. Это может быть просто текстовой файл с логом.

    Серверную часть приложения желательно реализовать на Spring 3 или 4. Можно
    использовать Spring Boot. Nodejs и Java EE.  Главный критерий для серверной части - это
    надежность. Приложение должено обрабатывать &quot;все&quot; возможные исключительные
    ситуации и вести их логирование.
    Реализация клиента на усмотрение. Желательно что бы он представлял из себя простой
    статичный сайт, т.е. html + js. JS фреймворк значения не имеет, но использование
    AngularJS или KendoJS особо приветствуется.
    Постарайтесь показать свое владение фреймворком. Т.е. хотелось бы увидеть роутинг,
    обработку ошибок, использование директив (если это angular) и т.п. Даже если это будет
    немного излишним для небольшого приложения.
    То же касается js. Клиент должен нормально работать во всех популярных браузерах
    (Chrome, Firefox, IE(10-11))