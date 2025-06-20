# Amazon-en-mieux

On veut réaliser 4 microservices :

- `Product` qui expose une API REST permettant de faire du CRUD sur une ressource `Product`. Le diagramme de classe des entités métier est le suivant :

    ![product - class diagram](./data/ClassDiagram%20-%20Product1.svg)

    Lors de la reception d'un event sur le topic `amazon-orders`, il devra ajouter l'id de la commande à la liste des commandes de chacun des produits.

- `User` qui expose une API REST permettant de faire du CRUD sur une ressource `User`. Le diagramme de classe des entités métier est le suivant :

    ![user - class diagram](./data/ClassDiagram%20-User.svg)

    Lors de la reception d'un event sur le topic `amazon-orders`, il devra ajouter l'id de la commande à la liste des commandes de l'utilisateur.

- `Order` qui expose les points d'accès suivant :
  - GET `/orders/{id}` : `findById`
  - POST `/orders` : `save`

    Lors de la création d'un order, il devra publier sur un topic `amazon-orders` la commande nouvellement créée.

    La structure des entités métier est le suivante :

    ![order - class diagram](./data/ClassDiagram%20-%20Order.svg)

- `SimilarProduct` qui memorise les associations entre produits fréquemment achetés ensemble. La structure des entités métier est le suivante :

    ![similar product - class diagram](./data/ClassDiagram%20-%20SimilarProduct.svg)

    Lors de la reception d'un event sur le topic `amazon-orders`, il devra pour chaque produit ajouter les autres produits à la liste des produits similaires ou incrémenter leur nombre d'occurrence si'ils sont déjà présent.