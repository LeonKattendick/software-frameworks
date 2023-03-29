# Database

## DB vs Kafka Streams Tables

<img height="50" src="https://1.bp.blogspot.com/-LIHoqo96Dos/X7wKY2u124I/AAAAAAAAp88/SrC8E6Y-5l4Xil7UPrDRp-RuEx2GioXkQCLcBGAsYHQ/s435/kafka-streams.png" width="150"/>

Kafka bietet als Event Streaming Platform mehr als Streams an.  
Der volle Prozess umfasst:  
*"Publish and Subscribe" -> "Store" -> "Process" -> "Integration"*

Man kann rein theoretisch Kafka als Storage auf unbegrenzte Zeit nutzen und kann sogar ACID garantieren.  
Die Grundeidee ist jedoch kein totales Replacement sondern eher ein Addon zu einer DB.

Kafka Streams ist nicht ausgelegt ein large-scale persistence store für z.B.: Dokumente zu sein, ob Kafka Streams sich
also als Store eignet hängt vom spezifischen Use Case ab.  
Bei key/value lookups sollte man z.B. eher auf klassische DBs setzen, da Kafka Message basierte lookups durchführt,
anstatt key/index based.

## H2 Database Engine

<img height="50" src="http://www.h2database.com/html/images/h2-logo-2.png" width="100"/>

Wir haben uns für eine H2 Datenbank entschieden.

H2 ist eine open Source Java SQL Datenbank und bietet vorallem für kleinere/monolitische Java Projekte diverse
Vorteile.  
Sie ist lightweight, relational und einfach aufzusetzen.

Es sind alle benötigten Features enthalten und somit der passende / least complex Fit auf den Use Case des Projekts.

https://www.h2database.com/html/main.html

## Alternative DBs/Storage

Alternativ könnte man eine MariaDB z.B. mit XAMPP für unseren Use Case nutzen oder den vorher erwähnten Ansatz Kafka
Streams als Store zu nutzen.  
