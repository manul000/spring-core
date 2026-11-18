# Spring Core Task Manager

Установите JDK 22. В переменных среды Windows задайте `JAVA_HOME` — папку JDK, а в `Path` добавьте `%JAVA_HOME%\bin`.
Затем откройте PowerShell в папке проекта. Maven загрузится автоматически; первый запуск требует интернета.

Установка зависимостей и сборка:

```powershell
.\mvnw.cmd package
```

Запуск с профилем `dev`:

```powershell
java -jar .\target\08-spring-core-1.0-SNAPSHOT.jar
```

Запуск с профилем `prod`:

```powershell
java -jar .\target\08-spring-core-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

Запуск с обоими профилями:

```powershell
java -jar .\target\08-spring-core-1.0-SNAPSHOT.jar "--spring.profiles.active=dev,prod"
```
