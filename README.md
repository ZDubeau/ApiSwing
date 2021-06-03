# ApiSwing

### _inroduction_



![N|Solid](https://www.wrike.com/blog/content/uploads/2019/05/Application-Programming-Interface-API-Explained-2-1.jpg)



Ce projet a été réalisé dans le cadre de ma formation chez Simplon afin de comprendre et maitriser une API.

1 - Vous devez maquetter les premiers screens de l'application et définir les scénarios.

2 - Vous allez concevoir la BDD (SQL || noSQL || autre) => Modélisation, schema, etc...

3 - Vous allez créer les premières tables ou documents

4 - développer avec un code défensif

5 - Afficher les infos requêtées sur le front

6 - Documenter l'API

7 - Déployer en ligne

8 - Mettre en place les outils de gestion de projet.

9 - Pensez que l'application sera portée sur Mobile et Desktop !



## Technologies utilisées

- [Java](https://www.java.com/en/) - projet java maven

- [Spring Boot](https://spring.io/projects/spring-boot) - Creér l'API 

- [Hibernate](https://hibernate.org/) - Connecter à la Base de donneées

- [MySQL](https://www.mysql.com/) - la Base de donneées

- [POSTMAN](https://www.postman.com/) - Veérifier URLs (API)

- [GitHub](https://github.com/) - Déployer le projet le côté **Git**

- [HEROKU](https://www.heroku.com/) - Déployer sur le server



## Test Scripts

`GET getUsers()`

```
https://api-swing.herokuapp.com/api/users
```



### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |


**EX. Success Response**


```sh
[
    {
        "id": 1,
        "username": "Sarah5",
        "firstname": "Zahra",
        "lastname": "Sadeghi",
        "mail": "bhr.sdgioiki@gmail.com",
        "password": "$2a$10$7Z2Hj23t8YiQUz71m0kiaeZSo9.PaC/awtbjapy7YXvbUccgNWQBq",
        "objects": []
    },
    {
        "id": 2,
        "username": "Sarah",
        "firstname": "Zahra",
        "lastname": "Sadeghi",
        "mail": "bhr.sdg@gmail.com",
        "password": "$2a$10$aAYXdTo1ImDyrSTsl8zDCerrLq9q9J8fncJeu/VWjA177BbH2QCt.",
        "objects": []
    }
]
```

**Ex. NOT_FOUND response**

```sh
{
  "timestamp": "02-06-2021 03:31:40",
  "status": "NOT_FOUND",
  "message": "Resource Not Found",
  "errors": [
    "User list is empty."
  ]
}
```

************************************************

`GET getObjects()`

```
https://api-swing.herokuapp.com/objects/all
```


### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |



**EX. Success Response**

```sh
[
  {
    "id": 1,
    "status": "BOOK",
    "title": "Le petit prince",
    "description": null,
    "ageMin": 2,
    "ageMax": 5
  }
]
```

**Ex. NOT_FOUND response**

```sh
{
  "timestamp": "02-06-2021 08:08:23",
  "status": "NOT_FOUND",
  "message": "Resource Not Found",
  "errors": [
    "Object list is empty."
  ]
}
```

************************************************


`GET getUserById()`

```
https://api-swing.herokuapp.com/api/user/{1}
```

### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |



**EX. Success Response**

```sh
{
    "id": 1,
    "username": "Sarah132",
    "firstname": "Baharyy",
    "lastname": "Merian",
    "mail": "bhr.dubqyyy@gmail.com",
    "password": "$2a$10$ttjiHMLb0VaymIOPbvOEv.uyZtc5jw1dN4rBD8wgiid7UXuOiFZ.G",
    "objects": []
}
```

**Si "id" de USER n'est pas dans la BDD, nous verrons la réponse suivante :**

*EX. NOT_FOUND Response*

``` "User with ID [?] Not Found!"
```


************************************************


`PUT updateUserById()`

```https://api-swing.herokuapp.com/api/update/{1}
```

### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |


##### **Body**  raw(json)

```sh
{
    "username": "Sarah132",
    "firstname": "Baharyy",
    "lastname": "Merian",
    "mail": "bhr.dubqyyy@gmail.com",
    "password": "$2a$10$ttjiHMLb0VaymIOPbvOEv.uyZtc5jw1dN4rBD8wgiid7UXuOiFZ.G",
    "objects": []
}
```

**EX. Success Response**

```sh
{
    "id": 1,
    "username": "Sarah202",
    "firstname": "Bahar",
    "lastname": "Dubeau",
    "mail": "bhr.waren@gmail.com",
    "password": "$2a$10$lhEWkZqCvVyRjVHeUDqf7e3rllMZD0txdPTrOnmhnxRizvF6FSE56",
    "objects": []
} 
```


************************************************


`DELETE deleteUserById()`

```https://api-swing.herokuapp.com/api/user/{1}
```

**EX. Success Response**

```User with ID : 1 is deleted
```

**Aussi toujours la réponse d' "id" qui n'existe pas dans la BDD est :**

*EX. NOT_FOUND Response*

``` "User with ID [?] Not Found!" 
```



************************************************


`POST saveUser()`

```https://api-swing.herokuapp.com/api/registration
```


## Annotation Pattern

J'ai défini différentes expressions régulières ("paterns") dans **model** afin de respecter le forme demandée pour chaque entrée.

Exemple :

- *Username (type = string) : regexp = "^[A-Za-z0-9]{4,15}", message = "length must be between 4 - 15 letters."*


- *Password (type = string) : regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,200}$", message = "length must be minimum 6, use Upper, Lower, letter and special characters"*



J'ai égaement sécuriser les données du coté de la Base de Donnée (BDD) : c'est-a-dire *valeur unique, not null*.


En plus, j'ai creer un package **exceptions** ou j'ai customise les exceptions renvoyés lorsque des erreurs surviennent.


### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |



### Request Headers

##### **Body**        raw(json)

```sh
{
  "username": "Sarah12",
  "firstname": "Zahra",
  "lastname": "Sadeghi",
  "mail": "bhr.sdgi@gmail.com",
  "password": "Zahra@23"
}
```



**EX. `Success Response`**

```sh
{
  "id": 3,
  "username": "Sarah12",
  "firstname": "Zahra",
  "lastname": "Sadeghi",
  "mail": "bhr.sdgi@gmail.com",
  "password": "$2a$10$jRYn4EnpsoOLDIQREe0oDeGBPlP1nS7lOcR4HgjMcB/O/mMMGGNGW",
  "objects": null
}
```

-----------------------------------------


### Request Headers

##### **Body**        raw(json)

```sh
{
  "username": "Sarah12",
  "firstname": "Zahra",
  "lastname": "Sadeghi",
  "mail": "bhr.sdgi@gmail.com",
  "password": "Zahra@23"
}
```

**EX. `DuplicateKeyException - Double`**



**Username et Mail sont `UNIQUE` dans la table (Sécurisé côté BDD)**

```sh
{
  "timestamp": "02-06-2021 08:44:51",
  "status": "CONFLICT",
  "message": "Unique value for USERNAME and EMAIL",
  "errors": [
   "Username: [Sarah12] and Email: [bhr.sdgi@gmail.com] already exists !"
  ]
}
```

*Si seulement Username est en double, la reponse sera* 

```
[
 "Username: [***] already exists !"
]
```

-----------------------------------------

*Si seulement MAIL est en double, la reponse sera*


```[
 "Email: [***] already exists !"
]
```


-----------------------------------------


`POST saveUser()`

```https://api-swing.herokuapp.com/api/registration
```

### Request Headers

| KEY | VALUE |
|----------|:----------:|
| Content-Type | application/json |



### Request Headers

##### **Body**        raw(json)

```sh
{
    "status": "BOOK",
    "title": "Le petit prince",
    "description": "Le Petit Prince est une œuvre de langue française, 
        la plus connue d'Antoine de Saint-Exupéry. Publié en 1943 à New York 
        simultanément à sa traduction anglaise1, c'est une œuvre poétique et 
        philosophique sous l'apparence d'un conte pour enfants.",
    "ageMin": 2,
    "ageMax": 100
}
```

**EX. `Success Response`**

```sh
{
    "id": 1,
    "status": "BOOK",
    "title": "Le petit prince",
    "description": "Le Petit Prince est une œuvre de langue française, 
        la plus connue d'Antoine de Saint-Exupéry. Publié en 1943 à New York 
        simultanément à sa traduction anglaise1, c'est une œuvre poétique et 
        philosophique sous l'apparence d'un conte pour enfants.",
    "ageMin": 2,
    "ageMax": 100
}
```



> ***Il y aura les mêmes requêtes pour `UPDATE` et `DELETE` pour un objet (A cause de manque de temps cette partie n'est pas codé)***



## Installation

### Versions de tech

. Java 15.0.2

. Spring Boot 2.5.0

. MySQL 8.0.22



- Créer le projet sur `GitHub` (Ce projet est privé)

- Créer [`Maven Project` dans eclipse](https://www.toolsqa.com/java/maven/create-new-maven-project-eclipse/)

- Ajouter Spring Boot au projet (dans mon cas, comme j'utilise `java EE`, j'ai pu creer     directement le projet Maven Spring Boot         [guide](https://medium.com/danielpadua/java-spring-boot-eclipse-744454468670)), sinon il     faut aller [spring initilizr](https://start.spring.io/) et créer un projet Maven et     ajouter les dependants nécessaires et en suite appuyer sur le boutton `GENERATE`. 

- Déplacer le dossier telecharge où vous souhaitez vous codez. Ensuite importer dossier     dans le projet maven existant. 



## Developpement

#### Packages !!!

Mon projet contient les differents packages (couches)

- Model (User, Objects)

- Repository (DAO le coœur de projet)

- Business (le cerveau de projet)

- Controller (la première couche où on communique avec l'interface (ici [POSTMAN](https://www.postman.com))

- JWT (authentification)

- Exceptions (exceptions customisées)

- Configuration (configurer l'authentification) , aussi on configure les parameteres de Spring Boot dans `applications.properties`.



et la partie qui nous permet de lancer le projet et au meme niveau que les autres packages est la classe `SwingApplication`.java



Tous les dependants MAVEN se trouve dans `pom.xml`.



Il est obligatoire de mettre a jour le projet apres ajout d'un dependant. 

>1. menu eclipse > Run Configuration > Maven Build > Click on Skip Tests > apply and Close

>2. Right click > Maven > Update Project ...



## Heroku

Après avoir déployé le projet sur GitHub, j'ai configuré mon projet pour pouvoir déployer égalemet sur Heroku. Pour cela :

J'ai crée une APP dans mon compte Heroku "**api-swing**".

Ajouté `Procfile`(Dyno), `.env`, `app.json`, `system.properties`

Ajouté `JawsDB MySQL`(Add-ons) dans Heroku (Malheureusement Heroku n'a pas MySQL Add-ons default)

Récupéré les parameters de connexion de JawsDB et créé une nouvelle connexion sur **MySQl Workbench**

Configurer `applications.properties`


```
spring.datasource.url= ${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}
```

>En plus 



[![image](https://www.linkpicture.com/q/Screenshot-2021-06-03-at-10.25.47_1.png)](https://www.linkpicture.com/view.php?img=LPic60b8953fe7c781078587667)



>Et afin sur Heroku, `Deploy > Deployment method > GitHub connect` nous allons choisir **Authomatic connection** + **branche main** (Il y a la possibilité de créer plusieurs piplines pour plusieures branches)





A partir de ce moment, à chaque fois que nous mergeons la branche de travail avec la branche *main*, le projet va se dépoyer authomatiquement sur heroku.



## Plugins



| Plugin | README |
| ------ | ------ |
| GitHub | [https://github.com/ZDubeau/ApiSwing#readme][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |





[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)



   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>

