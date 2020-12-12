# SYM : Labo 3 - Environnement I (Codes-barres, iBeacons et NFC)

> Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
> Date : 10.12.2020
> Classe : B



## 1. Introduction

TODO

## 2. Balises NFC

Login : `admin ` `admin` . 

### Implémentation

L’activité `NfcActivity` pourrait être lancé avec : 

```xml
<intent-filter>
    <action android:name="android.nfc.action.NDEF_DISCOVERED" />

    <category android:name="android.intent.category.DEFAULT" />

    <data android:mimeType="text/plain" />
</intent-filter>
```

Mais comme précisé dans le laboratoire elle est uniquement notifiée d’un tag `NDEF` lorsqu’elle est à l’écran. 

Pour ce qui est de la 2FA le choix suivant à été fait : 

* Lorsque l’activité NFC est lancée un fragment contenant la page de login apparaît celui-ci notifie grâce a une fonction de l’activité si le login est un succès. Note: les accès sont `admin` et `admin` . Cette page n’est plus affichée après (sauf fermeture complète de l’app). 

* Des le lancement de l’activité NFC l’app peut être notifiée d’un nouveau tag NFC `NDEF`  et uniquement lorsqu’elle est visible à l’écran. Si un tag contenant la chaîne `test` est scannée le niveau d’authentification passe a 15 et est ensuite décrémenté de 1 toutes les deux secondes pendant 20 secondes. Puis repasse à 0. 

Pour pouvoir “lire ” (avoir un `Toast` qui s’affiche avec `xx auth ok`) il faut avoir : 

* au moins 10 pour l’auth max
* au moins 5 pour l’auth medium 
* au moins 1 pour l’auth min 



> 2.4.1 Dans la manipulation ci-dessus, les tags NFC utilisés contiennent 4 valeurs textuelles codées en UTF-8 dans un format de message NDEF. Une personne malveillante ayant accès au porte-clés peut aisément copier les valeurs stockées dans celui-ci et les répliquer sur une autre puce NFC. A partir de l’API Android concernant les tags NFC3, pouvez-vous imaginer une autre approche pour  rendre  plus  compliqué  le  clonage  des  tags  NFC?Existe-il  des  limitations? Voyez-vous d’autres possibilités

NDEF n’a pas de protection contre le clonage. Grâce a l’API Android on pourrait utiliser différents tag. Avec  `getTechList()` on peut identifier la technologie du tag. Et il y’a des tag qui implémentent de la crypto ou d’autre mécanisme d’anti clonage. Notamment de chiffrer la clef sur la carte et qu’elle implémente un mécanisme encryptions et de communication.

  source: https://security.stackexchange.com/questions/63483/how-do-nfc-tags-prevent-copying

> 2.4.2 Est-ce qu’une solution basée sur la vérification de la présence d’un iBeacon sur l’utilisateur, par exemple sous la forme d’un porte-clés serait préférable? Veuillez en discuter.

Non car l’iBeacon à une grande portée donc un utilisateur pourrait s’authentifier avec l’iBeacon d’un autre utilisateur qui se trouverait par exemple dans la même pièce.  Ainsi le “vol” de ses credentials suffit à s’authentifier et le 2FA n’est pas efficace. 

## 3. Codes-barres

TODO

## 4. Balises iBeacon

### Implémentation

Références :

- Utilisation de la librairie *Android Beacon Library* permettant d’interagir avec les beacons : 
  - https://altbeacon.github.io/android-beacon-library/samples.html#ranging-example-code
- Création et utilisation de RecyclerView :
  - https://developer.android.com/guide/topics/ui/layout/recyclerview

### Question

> *Les iBeacons sont très souvent présentés comme une alternative à NFC. Vous commenterez cette affirmation en vous basant sur 2-3 exemples de cas d’utilisations (use-cases) concrets (par exemple e-paiement, second facteur d’identification, accéder aux horaires à un arrêt de bus, etc.).*

Le iBeacon est un système de positionnement en intérieur développé par Apple. Il offre une grande distance d'émissions allant jusqu'à ~70m alors que le NFC va jusqu'à ~10cm (des iBeacons longue portée peuvent même aller jusqu'à ~450m). Néanmoins, les iBeacons sont très sensibles aux interférences radios et aux obstacles.

Les iBeacons ont également l'avantage de diffuser à plusieurs client en même temps. Cela peut devenir être un problème de sécurité où un attaquant pourrait spoofer un beacon et l'utiliser de façon malveillante.

Voici quelques cas d'utilisation pratique :

- **Afficher le menu en ligne dans un restaurant :**

  L'objectif sera d'envoyer la carte du menu aux clients d'un restaurant lors de leurs arrivées. Pour cela, iBeacon est plus adapté car le client n'a pas besoin de venir scanner une balise comme ça serait le cas pour NFC. La carte serait reçu dès que le client entre dans la zone du restaurant. Deuxièmement, iBeacon serait plus économique car un seul iBeacon peut émettre à plusieurs client en même temps. Il faut donc moins de balise.

- **Paiement sans contact :**

  Dans ce deuxième cas, il est préférable d'utiliser NFC au lieu de iBeacon car iBeacon diffuse de façon complètement visible toutes ces informations et cela dans un rayon de 70m. Si l'application est mal implémenté, il serait possible qu'un attaquant puisse faire effectuer un paiement non voulu à la victime en spoofant le iBeacon. Le spoofing étant très facile à effectuer puisqu'il suffit de se situer à moins de 70m de la baliser et d'écouter ces émissions. A l'inverse, NFC émet à très faible distance (~10cm) ce qui est plus difficile à intercepter pour un attaquant. NFC fournit également des fonctions de chiffrements qui permettent de garantir la confidentialité des données.

- **Publicité dans un magasin :**

  Le but serait d'afficher les nouveaux produits et les soldes présent dans le magasin. Pour cela, le iBeacon est plus adapté car il couvre toute une zone, de la même façon que pour le restaurant.



## 5. Conclusion

TODO