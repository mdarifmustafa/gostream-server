heroku buildpacks:clear
heroku ps:scale web=1
java -Dserver.port=$PORT $JAVA_OPTS -war target/*.war