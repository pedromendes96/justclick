# Developer for Java Project JustClick.Media

To launch the project, just run:

```
docker-compose up -d
```

It will launch the server, a postgres, a elasticsearch and a adminer.

The project it will load the following data:

| counter  | key  | url |
|---|---|---|
| 2 | dnoticias | https://www.dnoticias.pt |
| 4 | justclick | https://www.justclick.media/ |
| 6 | observador | https://observador.pt/ |
| 8 | youtube | https://youtube.com/ |
| 10 | wikipedia | https://en.wikipedia.org/ |
| 12 | reddit | https://www.reddit.com/ |

To test the app just replace the key value of your choice https://localhost:8080/link/{key} .

Enjoy!