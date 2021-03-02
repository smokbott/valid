## Valid Backend

Servicio REST para administrar personas
construido con: java 8, spring, h2 database, hibernate, maven, sonar, swagger, junit. 

### Pre-requisitos 📋

```
- java
- maven
```
### Instalación 🔧

```
1. Descargar repositorio
2. abrir terminal cmd en la carpeta del proyecto
3. ejecutar el comando: mvn spring-boot:run
4. verificar que el servicio se este ejecutando en: http://localhost:8989/valid/persons
```

## Consultar personas

**URL** : `/persons`
**Method** : `GET`

# Success Response
**Code** : `200 OK`
**Content**
```json
[
    {
        "id": 1,
        "name": "diego",
        "lastname": "ibanez",
        "processed": false
    }
]
```
# Error Response
**Code** : `409 Not found`
**Content**
```json
{
    "timestamp": "2021-03-02T16:46:30.425+0000",
    "message": "find not found",
    "details": "uri=/valid/persons"
}
```
---------------------------------------------------
## Registrar persona

**URL** : `/persons`
**Method** : `POST`
**Request** 
```json

    {
        "name": "diego",
        "lastname": "ibanez",
        "processed": false
    }
```

# Success Response

**Code** : `200 OK`
**Content**
```json
{
    "id": 1,
    "name": "diego",
    "lastname": "ibanez",
    "processed": false
}
```
# Error Response

**Code** : `409 Conflict`
**Content**
```json
{
    "timestamp": "2021-03-02T16:50:10.819+0000",
    "message": "The person is already registered",
    "details": "uri=/valid/persons"
}
```
---------------------------------------------------
## Modificar personas

**URL** : `/persons`
**Method** : `PUT`
**Request** 

```json
[{
    "id": 1,
    "processed": true
}]
```
# Success Response

**Code** : `200 OK`
**Content**
```Text
1
```