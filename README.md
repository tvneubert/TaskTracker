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

Das ganze Projekt ist hier ersichtlich; ./uml-diagramm-1 <br>
Eine vereinfachte und übersichtlichere Variante ist hier ersichtlich: ./uml-diagramm-2

# Erkenntnisse und Projektverlauf

## Tatsächliche Umsetzung im Bezug auf die Projektskizze:

Man kann Projekte und in diesen Projekten Task anlegen
Es gibt Zeit pro Task die sich kummuliert pro Projekt oder in der Übersicht über alle Projekte.
Wenn man einen neuen Task erstellt muss man Namen eingeben, dann eine Beschreibung die den Task spezifiziert und eine Deadline. Wir haben uns dagegen entschieden der SMART Philosopie eine ganze Klasse zu widmen, sondern dies mit der Beschreibung des Task und, dass diese Felder "required" sind versucht umzusetzen.

In der Projektansicht hat man die Übersicht über die einzelnen Task die dem Projekt angehören. Man kann dort den Timer starten, oder pausieren. man kann auf die Vorschau der Beschreibung tippen um diese ganz anzuzeigen. Bei erneutem klicken wird der Text wieder minimiert.

Wir haben somit die Grundanforderungen aus der Projektskizze umgesetzt, aber im UI einige Entscheidungen getroffen, die von den ursprünglichen Mockups abweichen.
Beim abhaken stoppt der Timer und wird inaktiv, das wird durch ein ausgrauen des Task präsentiert.
Versteicht die Deadline eines Task und er ist noch aktiv so wird diese rot eingefärbt.

### Wir haben uns für folgende extra Features:
File basierte Database mit JSON Elementen. -> das führt zu einigen Gettern oder public Methoden die auf den ersten Blick als "ungenutzt" in der IDE angezeigt werden können. allerdings liest das JSON die für sich wichtigen zu speichernden Datenfelder aus den "Gettern" der zuständigen Klassen und kann nur so komplett funktionieren.

Eine Filter-Option im Projekt um Task nach Aufwand, Deadline oder "done" zu sortieren.

Wir haben uns gegen das Costumize-Feature entschieden und lieber mehr praktische Features eingebaut und mehr auf Benutzerfreundlichkeit geachtet. Unser Ziel war es ein Programm zu präsentieren, dass einen tatsächlichen Nutzen hat und bereits in der jetzigen "Demo-Phase" ein durchdachtes UI-Design beinhaltet.

Sind alle Task in einem Projekt erledigt so färbt sich der Text des Projektes grün.

## Mögliche Verbesserungen
* Es wäre schön gewesen, wenn es nicht so viele Fenster öffnet, wir konnten uns allerdings nicht einigen ob wir lieber dem User die Möglichkeit geben wollen alle Projekte ansehen zu können, oder weniger Fenster offen zu haben.
* Icons hätten vor allem in der Projekt-Übersicht besser gewählt werden können
* Die Date Methoden hätte man ggf. verweinfachen können. Leider ist uns nicht in den Sinn gekommen wie ohne die aktuelle Funktionalität zu verlieren.

## ChatGPT
Siehe nutzung im RaceTrack ReadMe -> analog dazu.
