#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h> 
#include <sys/wait.h>
#include <fcntl.h> 
#include <unistd.h>
#include <semaphore.h>

#define NUMBER_CLIENTS 5
#define CELLS_NUMBER 50
#define SHARED_BOARDS 3

typedef struct{
	char content[100];
}cell;				//Struct to simulate a cell

typedef struct{
	cell cells[CELLS_NUMBER];
}shared_board;		//Struct to simulate a shared board

void change_cell(shared_board *board, int cell_number, char *ptr){		//Function to make a post in a shared board
	printf("Which cell do you want to change?\n");
	printf("Cell(0 to 50):");
	scanf("%d", &cell_number);											//Get the cell to change by asking the user
	printf("\nContent:");
	scanf("%s", ptr);													//Get the content to post by asking the user
			
	strcpy(board->cells[cell_number].content,ptr);						//Update the shared board
			
	printf("\nShared Board State: \n");
	
	for(int i = 0; i < CELLS_NUMBER; i++){								//Print the shared board state
		printf("Cell %d Content: %s\n",i,board->cells[i].content);
	
	}	
}

//Function to generate child processes
int make_children(int n) {
    pid_t pid;
    int i;
	pid_t pidF = getpid();
	
    for (i = 0; i < n; i++) {
        pid = fork();
        if (pid < 0)													//Error
            return -1;
        else if (pid == 0)												//Son
            return i;
		else if (getpid() == pidF && i == n - 1)						//Father
			return pidF;
    }
    return 0;
}	

int main(){
	//Initializing variables, shared memory and semaphores
	int i;
	int size = sizeof(shared_board);
	shared_board *board, *board2, *board3;
	
	int fd = shm_open("/US3003_shm1",O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
	
	ftruncate(fd,size);
	
	board = (shared_board*) mmap(NULL,size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	
	int fd2 = shm_open("/US3003_shm2",O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
	
	ftruncate(fd2,size);
	
	board2 = (shared_board*) mmap(NULL,size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	
	int fd3 = shm_open("/US3003_shm3",O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
	
	ftruncate(fd3,size);
	
	board3 = (shared_board*) mmap(NULL,size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	
	sem_t *sem_part;
	
	if((sem_part = sem_open("/US3003_sem",O_CREAT|O_EXCL,0644,1)) == SEM_FAILED){
		perror("Error in sem_open()");
		exit(EXIT_FAILURE);
	}
	
	pid_t pidFather = getpid();
	int id = make_children(NUMBER_CLIENTS);
	
	//Childs
	if(id >= 0 && id != pidFather){
			sem_wait(sem_part);											//Block semaphore for only one child make changes at the same time
			
			printf("\n\nClient %d\n\n", id+1);							//Print the client
			
			int cell_number = 0, option,option2;
			char content[100];
			char *ptr = content;
			
			do{															//Get the action that the user want to make by asking him
				printf("Client Menu\nChange Boards->1\nView Boards->2\nOption: ");
				scanf("%d",&option2);
			
				if(option2==1){											//If the user want to make a post/change at the board
					do{
						printf("\nWhich board do you want to change(1,2 or 3):");
						scanf("%d",&option);
						
						switch(option){
							case 1:										//Case of the first board
								change_cell(board, cell_number, ptr);
								break;
							case 2:										//Case of the second board
								change_cell(board2, cell_number, ptr);
								break;
							case 3:										//Case of the third board
								change_cell(board3, cell_number, ptr);
								break;
							default: break;
							}
						}while(option<1 || option > 3);					//If the option inputed is invalid ask again
						
				} else if(option2==2){									//If the user want to see the boards
					
					printf("\nShared Board 1:\n");						//Print the board 1
					for(int i = 0; i < CELLS_NUMBER; i++){
						printf("Cell %d Content: %s\n",i,board->cells[i].content);
					}
					printf("\nShared Board 2:\n");						//print the board 2
					for(int i = 0; i < CELLS_NUMBER; i++){
						printf("Cell %d Content: %s\n",i,board2->cells[i].content);
					}
					printf("\nShared Board 3:\n");						//print the board 3
					for(int i = 0; i < CELLS_NUMBER; i++){
						printf("Cell %d Content: %s\n",i,board3->cells[i].content);
					}
						
				}
			}while(option2<1 || option2 > 2);							//If the option inputed is invalid ask again
			
			
			sem_post(sem_part);											//Allow the semaphore blocked above
			exit(0);													//Child finnished the word
	}	
	
	//Father waits for childs
	for(i = 0; i < NUMBER_CLIENTS; i++){
		wait(NULL);
	}
	
	if (sem_close(sem_part) == -1){										//Close semaphore
		 exit(EXIT_FAILURE);
	 }
	if (sem_unlink("/US3003_sem") == -1){								//Delete semaphore
		 exit(EXIT_FAILURE);
	 }
	
	if (munmap(board,size) < 0){										//Unmap shared memory
		 exit(1);    
	 }
	if (close(fd) < 0){													//Close field descriptor
		 exit(1);	
	 }				  
	if (shm_unlink("/US3003_shm1") < 0){								//Delete shared memory
		 exit(1);
	 }
	 
	 if (munmap(board2,size) < 0){										//Unmap shared memory
		 exit(1);    
	 }
	if (close(fd2) < 0){												//Close field descriptor
		 exit(1);	
	 }				  
	if (shm_unlink("/US3003_shm2") < 0){								//Delete shared memory
		 exit(1);
	 }
	 
	 if (munmap(board3,size) < 0){										//Unmap shared memory
		 exit(1);    
	 }
	if (close(fd3) < 0){												//Close field descriptor
		 exit(1);	
	 }				  
	if (shm_unlink("/US3003_shm3") < 0){								//Delete shared memory
		 exit(1);
	 }    
	
	return 0;
}
