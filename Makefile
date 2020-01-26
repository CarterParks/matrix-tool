all: compile jar

compile: src/*.java
	javac -d out $?

jar:
	jar -cef Tool matrix-tool.jar -C out .

run:
	java -jar matrix-tool.jar

