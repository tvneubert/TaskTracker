# <a href="https://github.zhaw.ch/PM2-IT22tbZH-wahl-krea/team1-hugsforbugs-projekt1-racetrack"><img src="https://cdn-icons-png.flaticon.com/512/785/785104.png" alt="Logo" width="80" height="80"></a> TaskTracker feat. Hugs4Bugs

Der TaskTracker ist ein Programm, dass das persönliche Task- und Projektmanagement optimieren soll.
Umgesetzt wird dies durch verschiedenen Projekte die man anlegen kann.
In diesen Projekten können Task angelegt werden, die Terminiert werden müssen und mit einer Beschreibung spezifiziert werden müssen.
Durch den TimeTraker den jeder Task besitzt wird jede Aufgabe messbar gemacht.
Mit einem minimalen UI wollen wir es dem Anwnder besonders leicht machen unser Programm zu nutzen und die eignene Produktivität zu steigern.

## Team01 Hugs4Bugs

* Emilio Lilie de León
* Larissa Bosshard
* Jan Nussberger
* Theresa Valentina Neubert

## Lets Start

Der TaskTracker ist in Java entwickelt und benutzt Gradle-build tools.<br>
Wir empfehlen das Programm mit ```./gradlew run``` in deinem git-bash Terminal zu starten

## Klassendiagramm

Eine einfache Version des UML-Diagramms ist unter "PFAD EINFÜGEN" zu finden. Um mehr über die Abhängigkeiten und die Verwendung in Code zu erfahren, schauen Sie bitte auf das UML-Diagramm "PFAD EINFÜGEN".

# Erkenntnisse und Projektverlauf

## Tatsächliche Umsetzung im Bezug auf die Projektskizze:

Man kann Projekte und in diesen Projekten Task anlegen
Es gibt Zeit pro Task die sich kummuliert pro projekt oder in der übersicht über alle projekte.
Wenn man einen neuen Task erstellt muss man Namen eingeben, dann eine beschreibung die den Task spezifiziert und eine deadline. 

In der Projektansicht hat man die übersicht über verscheidene task. Man kann doort den timer starten, oder pausieren. mann kann auf die previwe der beschreibung tippen um diese ganz anzuzeigen. Bei erneutem klicken wird der text wieder minimiert.

Beim abhaken stoppt der timer und wird disabled, das wird durch ein ausgrauen des Task präsentiert.

Versteicht die Deadline eines task und er ist noch aktiv so wird diese rot.

Wir haben uns für die Features entscheiden:
File basierte database mit JSON elementen.
Eine Filter option im Projekt um Task nach Aufwand, Deadline oder done zu sorteiren.

Wir haben uns gegen das Costumize Feature entschhieden und lieber mehr praktische Features eingebaut und mehr auf benutzerfreundlichkeit geachtet.

## ChatGPT
Siehe nutzung im RaceTrack ReadMe -> analog dazu.
