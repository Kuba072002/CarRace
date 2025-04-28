# 🏎️ Prosta symulacja wyścigu samochodowego – Runnable, Thread, ExecutorService

---

## 🎯 Cel projektu

Celem projektu było stworzenie aplikacji w języku **Java**, która demonstruje działanie wielowątkowości w dwóch wariantach:

- Klasyczne podejście z użyciem `Thread` i `Runnable`,
- Wysokopoziomowe podejście z użyciem `ExecutorService`.

Projekt przedstawia praktyczne różnice pomiędzy zarządzaniem pojedynczymi wątkami a zarządzaniem wątkami poprzez **pulę wątków** (ang. _thread pool_).

---

## 🎥 Demo

![car-race-demo](https://github.com/user-attachments/assets/367d756b-e40b-4936-99cf-aefa0832ceb7)

---

## 🛠️ Opis działania programu

Aplikacja jest graficzną symulacją wyścigu samochodowego, zbudowaną przy pomocy **Java Swing**.

- Każdy samochód to osobny pasek postępu (`JProgressBar`), który pokazuje pokonywaną przez samochód trasę.
- Po kliknięciu przycisku **"Start Race"**:
  - Dla każdego samochodu uruchamiany jest osobny wątek (klasycznie lub za pomocą puli wątków),
  - Każdy samochód co określony czas zwiększa swój przebyty dystans o losową wartość,
  - Pierwszy samochód, który osiągnie metę (pełne wypełnienie paska), kończy wyścig.

---

## ⚙️ Konfiguracja

Parametry wyścigu są wczytywane z pliku konfiguracyjnego `cars.properties`.  
Plik zawiera m.in.:

- Liczbę samochodów,
- Nazwy samochodów,
- Kolory pasków postępu,
- Ustawienia kolorystyki tła oraz przycisków.

### Przykład pliku `cars.properties`:

```properties
number_of_cars=6

car.1.name=Lightning
car.1.color=#2E8BC0

car.2.name=Thunder
car.2.color=#81B622

car.3.name=Blaze
car.3.color=#F8AFA6

car.4.name=Shadow
car.4.color=#C38370

car.5.name=Phantom
car.5.color=#845EC2

car.6.name=Titan
car.6.color=#777897

background.color=#C3E0E5
button.color=#41729F
text.color=#F7F7F7
```
---

## 📊 Profiler

### Threads

- CPU time:

![threadsCpu](https://github.com/user-attachments/assets/4c879c1b-d1d0-4f42-85ca-4c98b76df04f)

- memory allocations:

![threadsAllocations](https://github.com/user-attachments/assets/9b3e7b67-a839-4eaf-924b-7d050a40008f)

- timeline:

![threadsTimeline](https://github.com/user-attachments/assets/74f99210-5278-46d2-b2f8-80059809b41d)

### ExecutorService (fixedThreadPull)

- CPU time:

![fixedCpu](https://github.com/user-attachments/assets/4126aad2-78b9-47fe-a93a-cceed0ba6ca6)

- memory allocations:

![fixedAllocations](https://github.com/user-attachments/assets/a08efd94-6727-46f9-894a-01e75ad3d7cd)

- timeline:

![fixedTimeline](https://github.com/user-attachments/assets/21dcfa0f-70eb-4b3e-9b44-fd5f44eaafd5)

### ExecutorService (cachedThreadPull)

- CPU time:

![cachedCpu](https://github.com/user-attachments/assets/00b6449a-3132-48a0-be64-e480b044f086)

- memory allocations:

![cachedAllocations](https://github.com/user-attachments/assets/49cf8c88-1d25-4d30-aee8-b50b74fa9f78)

- timeline:

![cachedTimeline](https://github.com/user-attachments/assets/f6a73639-8fd1-46ee-ab55-6bf01039faec)
