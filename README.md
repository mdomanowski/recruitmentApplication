# recruitmentApplication
Recruitment application for Envelo, based on API -> http://api.nbp.pl/
## Parametry zapytań o kursy walut:
 - {codeFrom} - trzyliterowy kod waluty z której nastąpi przeliczenie (standard ISO 4217), możliwe jest jedynie przeliczenie z PLN
 - {codeTo} - trzyliterowy kod waluty na którą nastąpi przeliczenie (standard ISO 4217)
 - {amount} - ilość jednostek waluty z której nastąpi przeliczenie

## Aplikacja udostępnia 4 endpointy

## localhost:8080/api/v1/exchangerates/currencies 
Wyświetla listę dostępnych walut na których można wykonać przeliczenia
## localhost:8080/api/v1/exchangerates/details
Udostępnia listę aktualnych kursów dla listy walut
## localhost:8080/api/v1/exchangerates/{codeFrom}/{codeTo}/{amount}
Wywołuje przeliczenie na podstawie obecnego kursu wymiany obu walut.
- przykład: localhost:8080/api/v1/exchangerates/PLN/GBP/156

## Opis parametrów odpowiedzi dot. kursów walut
- currentDate - data wywołania
- currentTime - godzina wywołania
- currencyFrom - symbol waluty z której nastąpiło przeliczenie
- currencyTo - symbol waluty na którą nastąpiło przeliczenie
- amount - ilość jednostek waluty z której nastąpiło przeliczenie
- bid - kurs kupna waluty
- ask - kurs sprzedaży waluty
- transferResult - ilość jednostek kupionej waluty po dokonaniu wymiany
- inverseTransferResult - ilość jednostek waluty którą użytkownik otrzymałby, gdyby walutę na którą dokonałby wymiany zamienił spowrotem na początkową, na przykład PLN -> EUR i EUR -> PLN
- spread - różnica pomiędzy ceną kupna i sprzedaży, w przypadku określonej przez użytkownika kwoty

