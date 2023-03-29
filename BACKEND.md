# Backend

## SpringBoot

<img height="50" src="https://4.bp.blogspot.com/-ou-a_Aa1t7A/W6IhNc3Q0gI/AAAAAAAAD6Y/pwh44arKiuM_NBqB1H7Pz4-7QhUxAgZkACLcBGAs/s1600/spring-boot-logo.png" width="100"/>  

Wir nutzen das SpringBoot Framework für unsere Backend Applikation, welche in Controller, Models, Repositories und
Services unterteilt ist.  
SpringBoot bietet für dieses Projekt eine Menge Funktionalitäten/Unterstützung *"out-of-the-Box"* und macht die
Entwicklung somit schneller, flexibler
und einfacher.

- OR-Mapper (Hibernate)
- HTTP Server / HTTP Funktionalitäten
- DB Anbindung
- Service / Scalability
- Rest Controller

Spring/SpringBoot ist das GoTo Framework für Java Applikationen auf dem Markt.

## AOT vs JIT

### JIT

Just in Time Compilation bedeutet, dass Code beim Start oder Teile sogar erst basierend auf Informationen die zur
Laufzeit gesammelt werden compiliert.

**Vorteile:**

- reduzierte Latenz
- concurrent garbage collectors
- Resilienz bei Peaks durch laufzeitoptimisierten Code

**Nachteile:**

- Warm Up Period
- CPU Overhead bei Laufzeit
- Scalability

**Anwendungsfälle:**

- Klassischer Approach
- *"Write once, run anywhere"*

### AOT

Ahead of Time Compilation bedeutet, dass Code bereits vor der Ausführung compiliert wurde.

**Vorteile:**

- schneller Start/Boot -> schnelle Skalierungsmöglichkeiten
- Volle Leistung von Anfang an
- kleinere Images
- weniger RAM Usage und CPU Overhead

**Nachteile:**

- keine runtime Optimisierung, somit Performance bei längerer Nutzung geringer
- nicht Platform agnostisch nach Compilierung

**Anwendungsfälle:**

- Cloud Services
- Containers (z.B.: Docker)
- Microservices

## FaaS (Lambda)

#### Würde ein Faas (Function as a Service) Approach sich für dieses Projekt eignen ?

**Generally:** Yes  
**Preconditions:** etablierte Platform, Know-How

Der Nachteil bei FaaS für kleine Projekte ist der Overhead einer etablierten Platform/Bezug dieser Platform durch
Geld.  
Sowie eingeschränktere Entwicklungsmöglichkeiten durch Rahmenbedingungen der Infrastruktur des Anbieters.

FaaS bietet jedoch auch einige sehr markante Vorteile für kleine Projekte, gegeben die Preconditions um FaaS "
Applikationen" zu entwicklen sind gegeben.

Die Konfiguration der Infrastruktur und der Skalierbarkeit liegt nicht mehr in der Verantwortung des Entwicklungsteams,
welches somit mehr Ressourcen für die Entwicklung hat.
