# DB SETUP

Les deux bases de données sont sur un seul et unique conteneur Docker, l'objectif étant de simplifier l'accéssibilité (bien que dans la réalité, les deux bases de données seraient sur des conteneurs différents).

Pour mettre en place et démarrer le service PostgreSQL, suivez les étapes ci-dessous :

## Mise en place des bases de données Simulateur et Emergency

Construisez l'image Docker à partir du fichier Dockerfile en exécutant la commande suivante :

```sh
docker-compose build
```

Une fois le build réalisé avec succès, on peut lancer les conteneurs docker en faisant :

```sh
docker-compose up
```

Cette étape devrait indique que les différentes bases de données on correctement été créer.

Pour pouvoir se connecter a ses deux bases, il suffit d'utiliser les infos suivant:

- User : **postgres**
- Password : **itssecret**
- Port : **5432**
- DbName : **[Emergency|Simulateur]** => Sur DBeaver (et potentiellement sur d'autre), pour avoir accés au deux BD il suffit de ne rien entrée dans le champs `name`
