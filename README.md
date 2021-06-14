# java-express-heroku
Test deploy Java Express to Heroku

## Steps
With heroku cli:
1. Create a file at root called _Procfile_ containing: _web: java $JAVA_OPTS -jar <your-application>.jar --port $PORT_
2. Create a file at root called _system.properties_ containing: _java.runtime.version=11_
3. Create new app at [Heroku](https://dashboard.heroku.com/apps)
4. in the terminal type:
  1. npm install -g heroku (if heroku cli isn't installed)
  2. heroku login
  3. git init (if not already connected with git)
  4. git add . && git commit -m "deploy to heroku"
  5. heroku buildpacks:set heroku/jvm
  6. git remote add heroku https://git.heroku.com/<heroku_app_name>.git
  7. git push heroku main