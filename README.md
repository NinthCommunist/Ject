# Ject
### Введение
Добрый день! Это мой персональный проект по автоматизации на Java.
Ридми документ будет состоять из отдельных частей, логически обособленных друг от друга, где будет описываться суть проекта. После каждого раздела *курсивом* будут указываться те моменты, где у меня возникли сложности или же я считаю, что мог их сделать лучше.
Также оговорюсь, что количество тестов небольшое, но в данном проекте есть костяк, который позволял бы сделать и другие тесты для данных ресурсов.

###POM
В качестве сборщика выбран мавен, так как по нему больше всего информации.
1. selenide - основа для UI тестов (знаю селениум, но селенид нравится больше, так как не задумываешься об аспектах реализации селениума, а просто пишешь тесты).
2. webdrivermanager от bonigarcia выбрал, так как не люблю лишние файлы в проектах, а без этой библиотеки пришлось бы искать вебдрайвер нужной версии, вставлять ее в проект
3. rest-assured+jacksone классическая связка, по которой больше всего информации в интернете (jackson можно не использовать, если есть селенид в проекте, но мне нравится аннотации в джексоне)
4. slf4j - та самая магическая штука, без которой ничего бы не работало. Да, там можно писать логи, но в моем случае они не отображались, хотя все делал через логгерФактори
5. testng - основа для прогона тестов, выбрал, потому что можно удобно управлять тестами через xml файлы
6. owner - для работы с пропертями
7. lombok - для аннотаций, чтобы не перезагружать POJO классы
8. allure - для отображения алюр отчетов, показывания скриншотов для UI, тел запросов и ответов в ресте
9. BUILD часть - классическая для таких проектов, взял из оф доки, плюс немного погуглил, примечательна строчка **<suiteXmlFilesrc/test/resources/xmls/${xml}.xml/suiteXmlFile>** где указывается xml файл, определяющий то, какие тесты будут запускаться

*хотел бы написать проект на градле, так как более лаконично и гибче за счет того, то можно писать на груви, но информации по нему меньше. Плюс не понравилось то, что пришлось build часть копипастить из оф. доки, сам бы такого не написал*

###UI
1. Page Object. У данного паттерна есть свои минусы, но мне он кажется самым логичным. Page описаны в main/ui/selenide/pages базовый класс содержит общие для всех страниц возможности (на сайте есть табБар, который одинаковый для всех страниц). Пришлось у функций возвращать BasePage, но это ошибка, нужно возвращать соответствующие страницы. Но данные страницы не создавал, так как проект небольшой.
2. Также в ui/selenide создан класс SelenideSetup, который определяет то, какой вебдрайвер будет запускаться (нужно для @BeforeTest)
3. Проперти располагаются в main/resources/properties/ui . Где используется только одно значение url=http://pestrecy-rt.ru/ (выбрал этот сайт потому что он мне нравится)
4. Тесты располагаются в test/java/ui/selenide Также есть базовый тест класс, который содержит аннотации. В методе setUp определяется то, как по сути будет запускаться тест. Если переменная ${selenoid} = true, то запустится тест в селениде, если false, то запустится обычный тест на том браузере, что установлен на компьютере. 
5. Специально добавил один тест, который должен упасть с ошибкой, для проверки того, что в аллюре можно найти скриншот экрана.

*Можно добавить тесты на Cucumber. Первоначально даже была такая задумка, но это бы нагрузило проект. А так синтаксис тоже знаю. Пишутся те же самые степы, со специальными аннотациями. а в feature файлах уже используются эти шаги в тестах. Плюс не нравится конструкция с переменной ${selenoid}, но лучше ничего не придумал.*

###REST
1. Толковых паттернов для рестовых тестов не знаю, поэтому сделал так, как подсказывало сердце. POJO отдельно, степы отдельно, создание спецификаций отдельно тесты отдельно.
2. POJO классы располагаются в main/java/rest/PojoClasses тут аннотации от ломбока, для того чтобы не прописывать геттеры и сеттеры, ну и аннотации от джексона.
3. Степы располагаются рядом, причем в пост запросах использую JSON файл и POJO класс.
4. UserCreater использует библиотеку подам для создания радномных значений.
5. RestSpecInstall используется для создания спецификаций. Там есть страшный приватный метод по переводу JSON файла в строку. Хотел бы от него отказаться, но не смог. Уж очень много сил на рестовые тесты потратил.
6. У самих тестов классический путь в test/java/rest
7. урл для рестовых тестов - url=https://reqres.in/

*Подход с таким созданием спецификаций не очень нравится, но кажется логичным, его минус в том, что он не такой гибкий, как хотелось бы. Плюс хотел бы сдесь использовать функциональные интерфейсы, но по логике тестов пришлось использовать только предикат для фильтрации*


### CONFIG
Можно найти по адресу main/java/config .Классический owner и класс с константами. Причем то, к какому урлу мы хотим обратиться будет зависеть от переменной ${testType}

*Не нравится как расположены проперти, где указаны урлы для рестового и UI тестов*

###XML
Можно найти в test/resources/xmls. Именно названия данных xml файлов используются для переменной ${xml} (о ней писал в блоке про POM)

*Часть информации в xml файла попадает в алюровский отчет*


### ЗАПУСК ТЕСТОВ
Пример запуска тестов - mvn clean test -DtestType=ui -Dxml=allUI -Dselenoid=true . Как уже сказал выше:
1. -DtestType по сути нужно для src/main/java/config/DataConfig.java чтобы знать какой урл брать
2. -Dxml нужен для строчки в POM файле suiteXmlFile> src/test/resources/xmls/${xml}.xml< /suiteXmlFile> чтобы знать какой xml файл брать
3. -Dselenoid нужен для src/main/java/ui/selenide/SelenideSetup.java чтобы знать запускать remote driver или нет

*Можно не добавлять переменную селеноида, а сделать запуск UI тестов всегда в селениде, а рестовых без запуска селенида в разных джобах Дженкинса*


###ALLURE
Кроме добавления в POM файл еще добавил src/test/resources/allure.properties где указал то место, откуда брать репорт для создания отчета.
Добавил фильры для отображения логов рестАшура и селенида.

*Может быть не все понятно в отображении информации в аллюре*

###Jenkins
Есть 2 джобы. Одна для запуска обычных тестов. Вторая для запуска тестов в селениде (пайплайн джоба, где используется Jenkinsfile). В zip файле будет видео с запуском тестов в разных джобах с указанием конфигов.

Отдельного внимания заслуживает файл Jenkinsfile. На него я потратил больше всего времени, так как информации о том, как сделать это на винде мало.
Данный файл используется только для запусков тестов на селениде.
1. Определяю проперти, которые нужно использовать, в моем случае это названия xml файлов.
2. Стэйдж start Selenoid  используется для скачивания селеноида и образа браузера(как я понял в моей реализации данный образ можно не тянуть, так как он у меня указан в src/test/resources/selenoid/browsers.json ,но в мануале про это прям написано, что нужно). Дальше запускаю селеноид и проверяю статус на порту 4444
3. На стейдже test запускается скрипт bat "mvn clean test -DtestType=ui -Dxml=${params.xml} -Dselenoid=true", о котором я уже рассказывал
4. Обычный аллюр отчет

То есть получается что на обычной джобе можно гонять и ui и rest тесты, а в селеноид джобе (та, что используется в Jenkinsfile, только UI)

*В дженкинс файле используется и powershell и bat, лучше выбрать что-то одно (а еще лучше запускать скрипт через sh на линуксе)*