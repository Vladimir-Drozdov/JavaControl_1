Zoo Management System
Проект, демонстрирующий работу зоопарка с животными, инвентарем и ветеринарной клиникой. Используется Spring DI-container, модульное разделение ответственности и покрытие кода unit-тестами.
Основные идеи проекта:
1)Приложение моделирует работу зоопарка:
2)Добавление животных (с проверкой здоровья).
3)Учёт инвентаря (животные тоже считаются объектами инвентаризации).
4)Подсчёт суммарного потребления еды.
5)Получение списка дружелюбных животных.
6)Консольное меню для взаимодействия.
7)Ключевым элементом является внедрение зависимостей через Spring, что позволяет отделять создание объектов от их использования.

Архитектура
Основные компоненты:

Animal и наследники	- модели животных с их поведением
IVetClinic / VetClinic	- проверка здоровья животных
IInventory / Thing	- учёт инвентаря
Zoo	- операции над животными и инвентарём
AppConfig - конфигурация Spring и DI
Main	- консольный интерфейс
Применение SOLID:

S — Single Responsibility

Zoo отвечает только за логику зоопарка.
VetClinic занимается лишь проверкой здоровья.
Каждый тип животного определяет только своё поведение.

O — Open/Closed

Чтобы добавить новое животное, достаточно создать новый класс и унаследовать Animal.
Zoo не нужно изменять.

L — Liskov Substitution

Все наследники Animal могут использоваться как Animal без изменения логики.

I — Interface Segregation

IAlive и IInventory разделяют обязанности.
Животное реализует IAlive и IInventory, а обычные предметы — только IInventory.

D — Dependency Inversion

Zoo работает с интерфейсом IVetClinic, а не с конкретной реализацией.
Реализация подставляется DI-контейнером Spring.

Использование DI-container в проекте

DI выполняет Spring:
файл app/Main:
var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
Zoo zoo = ctx.getBean(Zoo.class);

А config/AppConfig задаётся так:

@Configuration
public class AppConfig {

    @Bean
    public IVetClinic vetClinic() {
        return new VetClinic();
    }

    @Bean
    public Zoo zoo(IVetClinic vetClinic) {
        return new Zoo(vetClinic);
    }
}
Spring сам создаёт объекты и подставляет зависимости в нужные места.

Инструкция по запуску
1. Склонировать проект
   git clone https://github.com/Vladimir-Drozdov/JavaControl_1
   cd project-folder
2. Проект запускается в IntelliJ IDEA. Должны быть установлены Java Development Kit (JDK) 25 версии и maven(если не встроен в IntelliJ IDEA)
Для установки JDK 25 версии нужно прописать sudo apt install openjdk-25-jdk-headless и в file->Project Structure установить JDK 25 версии

3. Собрать проект
   mvn clean package

4. Запустить приложение
   mvn exec:java -Dexec.mainClass="org.example.app.Main"

или в IntelliJ IDEA:

Открыть maven-проект
Убедиться, что Maven подтянул зависимости
Запустить Main через зелёную стрелку

Запуск тестов
mvn test
