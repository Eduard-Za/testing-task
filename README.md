# Тестовое задание

<h3>Использованные технологии:</h3>
<ul>
<li>Java 8</li>
<li>Gradle</li>
<li>Selenide</li>
<li>JUnit</li>
<li>SLF4J</li>
<li>Hamcrest</li>
</ul>

<h3>Описание проекта</h3>

Тест <b>(src\test\java\MainTest.java)</b> был написан с использованием Selenide и Page Object pattern <b>(src\main\java\pages)</b>.
Пакет <b>(src\main\java\drivers)</b> содержит <b>ChromeWebDriverInstance.class</b> который содержит статический <b>getLocalDriver()</b> метод и обеспечивает возможностью запуска тестов.
<b>Важная информация</b> - тестирование проводилось на Windows в Chrome. (Хотя в <b>getLocalDriver()</b> реализована возможность запуска тестов под Mac OS, однако она не проверена).</br>

Драйвера находятсся в <b>(src\main\resources\drivers)</b>.
Также, были созданы пользовательские исключения <b>(src\main\java\exceptions)</b>.

Пакет (<b>src\main\java\utils</b>) содержит: PropertiesLoader.java - класс, который включает в себя метод loadProperty(String propertyName) позволяющий загружать properties из property-файла.

<b>src\main\resources\test.properties</b> - файл который содержит урл, пароль, имя пользователя и другие постоянные данные необходимые для теста.

<h3>Запуск тестов локально:</h3>

Откройте терминал и наберите в коммандной строке следующие комманды:

<code>git clone https://github.com/Eduard-Za/testing-task.git</code></br>
<code>cd testing-task</code></br>
<code>gradle cleanTest test</code>
