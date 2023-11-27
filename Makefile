all:
	./mvnw clean package
rodar_programa_pos_compilacao:
	java -jar target/grafo-camera-1.0-SNAPSHOT.jar
rodar_programa_preparado:
	java -jar snapshots_jar/grafo-camera-1.0-SNAPSHOT.jar