Microservice-Standalone
===

Ein Beispiel-Projekt, was zeigt, wie nach meinem Verständis eine Microservice-Architektur mit hilfe von Java SE Standalone-Anwendungen umgesetzt werden kann.

Motivation
---

Dieses Projekt ist im Rahmen meiner Master-Thesis entstanden.
Dabei sollte Untersucht werden, wie Microservices miteinander und evtl. mit einen existierenden Monolithen integrieren können.
Die hier vorgestellte Lösung stellt ein mögliches Vorgehen da.

Die Master-Thesis wurde von der [BioArtProducts GmbH](http://www.bioeng.de) betreut.
Entsprechend richtet sich die Fachlichkeit dieser Beispielanwendung an der Branche dieses Unternehmens aus.
Es wird eine Art Mini-Patienten-Register umgesetzt.
Es ist möglich anonyme Patienten zuerfassen und abzurufen.
Für jeden Patienten können zusätzlich Mediaktionen und Diagnosen gespeichert werden.
    
Umsetzung
---

Das Projekt definiert zunächst zwei (Micro-) Services.
Der __Backend-Service__ definiert eine Schnittstelle zu einer Datenbank.
Er ist für das Speichern und Auslesen der Patientendaten zuständig.
Außerdem gibt es noch den __Frontend-Service__, welche eine Darstellung für einen Webbrowser generiert.
Für die Verarbeitung von Nutzereingaben wird auf die Schnittstellen des Backends zugegriffen.

Dieses Projekt generiert sogenannte Standalone-Anwendungen.
Diese sind von sich aus lauffähig und benötigen keinen Anwendungsserver.
Dadurch können sie leichter übertragen werden.
Allerdings benötigt der Build-Vorgang im Gegensatz zum Thin-WAR-Ansatz mehr Ressourcen (Zeit und Speicher).
Dafür entfällt allerdings die Notwendigkeit, dass zwingend eine Konfiguration einer Plattform, z.B. ein Anwendungsserver wie [Glassfish](https://javaee.github.io/glassfish/) oder [Wildfly](http://wildfly.org), vorgenommen werden muss.

Die Vorzüge des Thin-WAR-Deployments habe ich bereits in dem zu diesem [alternativen Projekt](https://github.com/luiswolff/sample-microservice-as) diskutiert. 
    
Build
---

Bei diesem Projekt handelt es sich um eine [Apache Maven](https://maven.apache.org)-Anwendung.
Die Vorhandenen Module werden zu einer sogenannter Uber- oder Fat-JAR gepackt.
Das Bedeutet, dass alle Abhängigkeiten und die Anwendung selbst zu einem einzelnen, ausführbaren JAR gepackt werden.
Hierfür wird das [Maven-Shade-Plugin](https://maven.apache.org/plugins/maven-shade-plugin/) verwendet, welches automatisch ausgeführt wird, wenn die Phase `package` ausgeführt wird.
Daher können beide Module durch das Ausführen des Befehls `mvn package` im Projekt-Verzeichnis gebaut werden.
    
Abhängigkeiten
---

Beide Services benötigen folgende Umgebung, um betrieben werden zu können:

* __Java SE 8:__ Da im Projekt Lambda-Ausdrücke und die Java SE 8 API verwendet werden, wird diese sowohl für den Build als auch die Laufzeit benötigt.
 
* __Jersey 2.25.1 (JAX-RS):__ Jersey, bzw. JAX-RS, bildet das Herzstück der hier erstellten Services. 
Mit der Hier zu Verfügung gestellten API wird die Hauptlogik der Komponenten modelliert.

* __EclipseLink Moxy 2.60.0:__ Hierbei handelt es sich um eine Implementierung von JAX-B (XML-Binding).
Allerdings kann Moxy Java-Objekte auch als JSON serialisieren.
Beide Services tauschen Daten in JSON-Format aus.

* __Grizzly HTTP Server 2.3.28:__ Ein HTTP-Server, welche von Jersey als Laufzeitumgebung genutzt wird.

### Frontend

* __Jersey MVC 2.25.1:__ Ein Jersey-Spezifisches Framework, mit den HTML-Templates gerendert werden können.
Es hat eine ähnliche Funktionsweise wie [SpringMVC](https://spring.io/guides/gs/serving-web-content/) und es werden die Template-Engines [Freemarker](http://freemarker.org/), [Mustache](https://github.com/spullara/mustache.java) und [JavaServer Pages (JSP)](https://jcp.org/en/jsr/detail?id=245) (Nur Servlet-Container) unterstützt.
 
* __Freemarker 2.3.23:__ Diese ist eine HTML-Template-Engine, mit dem durch das Zusammenführen von Templates und data-models ein dynamischer Output generiert werden kann.
Ich habe mich für diese Engine entschieden, dass sie besser dokumentiert ist als Mustache und im Gegensatz zu JSP auch ausserhalb eines Servlet-Containers verwendet werden kann.

### Backend

* __EclipseLink Persistence 2.60.0 (Backend):__ EclipseLink ist die Referenceimplementierung für JPA.
Diese Spezifikation wird als Schnittstelle zur Datenbank verwendet.

* __Apache Derby 10.13.1.1 (Backend):__ Als Datenbank wird Apache Derby verwendet.
Es wird der sogenannte "Embedded Driver" verwendet, weshalb Datenbank und Service in im gleichen Rechenprozess befinden.
Daher generiert das Backend-Projekt auch einen neuen Ordner `app-sample-db`, welcher aber nicht in die Versionskontrolle mit aufgenommen wird.

Start und Deployment
---

Dadurch, dass ausführbare Uber-JARs gebaut werden, können die Services direkt z.B. über die Kommandozeile gestartet werden.
Ist die aus dem Abschnit Abhängigkeiten beschriebene Java-Version in der PATH-Variable vorhanden können die Services mit den folgenden Befehlen gestartet werden:

* Frontend-Service: `java -jar frontend/target/frontend.jar`
* Backend-Service: `java -jar backend/target/backend.jar`
    
Autor und Datum
---

Luis-Manuel Wolff Heredia

05.09.2017