.PHONY: install serve

install:
	./gradlew build

serve:
	./gradlew appRun
