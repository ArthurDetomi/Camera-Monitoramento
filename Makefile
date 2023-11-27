all:
	./mvnw clean package
rodar_programa:
	java -jar target/grafo-camera-1.0-SNAPSHOT.jar