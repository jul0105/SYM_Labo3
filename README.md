# SYM : Labo 3 - Environnement I (Codes-barres, iBeacons et NFC)

> Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
> Date : 10.12.2020
> Classe : B



## 1. Introduction

TODO

## 2. Balises NFC

TODO

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