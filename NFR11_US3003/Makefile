prog: NFR11.o 
	gcc -Wall -g NFR11.o -o prog -lpthread -lrt

NFR11.o: NFR11.c 
	gcc -Wall -g -c NFR11.c

run: prog
	./prog

clean:
	rm *.o prog

debug:
	ddd prog
