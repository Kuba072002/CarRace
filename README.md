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

![image](https://github.com/user-attachments/assets/9ad5b0d1-872f-4b9e-a68e-0b900e697014)

- memory allocations:

![image](https://github.com/user-attachments/assets/0ee89463-60c7-477d-9309-e24e3283da31)

- timeline:

![image](https://github.com/user-attachments/assets/1cdf4fb2-5499-4775-b1aa-64580c317647)

### ExecutorService (fixedThreadPull)

- CPU time:

![image](https://github.com/user-attachments/assets/8e21c1fd-7b34-401b-a9ae-8ecc9e713cf7)

- memory allocations:

![image](https://github.com/user-attachments/assets/7cf3fa19-9c06-4146-95ec-8dd2a752a6b0)

- timeline:

![image](https://github.com/user-attachments/assets/8dc2937c-a603-4606-84b3-3a0c194a02fc)

### ExecutorService (cachedThreadPull)

- CPU time:

![image](https://github.com/user-attachments/assets/6a3a5096-ac96-4231-9bcf-86c8a5e28733)

- memory allocations:

![image](https://github.com/user-attachments/assets/28f0a40a-174f-4503-9373-da620cf42a82)

- timeline:
![image](https://github.com/user-attachments/assets/0d3f6662-2ab1-4e98-91da-369330d8c6c6)
