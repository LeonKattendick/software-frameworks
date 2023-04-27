# SSR

### Server Side Rendering.

Es handelt sich dabei um eine Technik, bei der die Webseite oder die Anwendung auf dem Server gerendert wird und das
fertige HTML-Dokument an den Client (den Browser des Nutzers) gesendet wird.

Im Gegensatz zur Client Side Rendering (CSR) wird somit der initiale HTML-Code nicht im Browser generiert, sondern auf
dem Server.

SSR hat den Vorteil, dass die Webseite schneller geladen wird und die Suchmaschinenoptimierung verbessert wird.  
Außerdem ermöglicht es eine bessere Barrierefreiheit und Benutzerfreundlichkeit, da die Inhalte schneller angezeigt
werden und Nutzer auch bei deaktiviertem JavaScript die Webseite besuchen können.

## Build time rendering

### Allgemein

Build Time Rendering (BTR) ist eine Technik, bei der der HTML-Code zur Laufzeit der Anwendung generiert wird, aber
bereits während des Build-Prozesses erstellt wird.  
Dies kann beispielsweise durch die Verwendung von Static Site Generators (SSG) erreicht werden.

Bei einer Kafka Streams Applikation handelt es sich jedoch um eine Datenverarbeitungsanwendung, die in der Regel als
Microservice oder als Teil eines verteilten Systems betrieben wird.  
Da BTR eine Vorabgenerierung des HTML-Codes erfordert, müssten die Kafka Streams-Daten in ein statisches Format wie HTML
konvertiert werden, bevor sie im Build-Prozess in die Anwendung integriert werden können.

Dies würde die Flexibilität der Anwendung einschränken und die Datenverarbeitung unnötig kompliziert machen.
Zudem sind Kafka Streams Applikationen oft auf die Echtzeitverarbeitung von Daten ausgerichtet, was sich nicht gut mit
einer BTR-Technik verträgt, da sich die Daten kontinuierlich ändern können.

Daher ist Build Time Rendering für eine Kafka Streams Applikation nicht die beste Wahl, da es zu einer unnötigen
Komplexität bei der Datenverarbeitung führt und die Flexibilität der Anwendung einschränkt.

### Unser Use Case

In unserer Applikationen ist das Ziel einen Stream aus Daten in "Echtzeit" zu haben.  
Wie oben erwähnt hat die Nutzung von BTR bei sehr dynamischer Echtzeitverarbeitung etliche Probleme und würde eine
ungeheuerlichen Overhead verursachen (Datentransformation).  
