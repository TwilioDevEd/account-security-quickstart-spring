install:
	./gradlew build

serve:
	. .env
	./gradlew appRun
