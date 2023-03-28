# Backend

## SpringBoot

Wir nutzen das SpringBoot Framework für unsere Backend Applikation.  
SpringBoot bietet eine Menge Funktionalitäten *"out-of-the-Box"* und macht die Entwicklung somit schneller, flexibler
und einfacher.

## AOT vs JIT

### JIT

Just in Time Compilation bedeutet, dass Code beim Start oder Teile sogar erst zur Laufzeit compiliert werden.

**Vorteile:** reduzierte Latenz (concurrent garbage collectors), Resilienz bei Peaks,  
**Anwendungsfälle:** Klassischer Approach, *"Write once, run anywhere"*

### AOT

Ahead of Time Compilation bedeutet, dass Code bereits vor der Ausführung compiliert wurde.

**Vorteile:** schneller start/boot, kleinere Images, weniger RAM Usage
**Anwendungsfälle:** Cloud Services, Containers (Docker), Microservices

## FaaS

**Generally:** Yes  
**Preconditions:** Platform

Der Nachteil bei FaaS für kleine Projekte ist der Overhead einer etablierten Platform/Bezug dieser Platform durch
Geld.  
Sowie eingeschränktere Entwicklungsmöglichkeiten durch Rahmenbedingungen der Infrastruktur des Anbieters.

FaaS bietet jedoch auch einige sehr markante Vorteile für kleine Projekte, gegeben die Preconditions um FaaS "
Applikationen" zu entwicklen sind gegeben.  
Die Konfiguration der Infrastruktur und der Skalierbarkeit liegt nicht mehr in der Verantwortung des Entwicklungsteams,
welches somit mehr Ressourcen für die Entwicklung hat.
