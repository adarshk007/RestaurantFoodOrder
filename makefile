JC := javac
JR := java
OP := -cp
MP := -d 
DP := compiledPackage
CP := src
Dependency := src/json-20211205.jar

# @echo "Build in Progress .."
# @echo "Ready to Run .."
all:
	@make build
	@make run
build:
	@${JC} ${MP} ${DP} ${CP}/*.java
run:
	@${JR} ${OP} ${Dependency}:${DP} ${CP}.Driver

clean:
	rm -rf compiledPackage/src/*.class
