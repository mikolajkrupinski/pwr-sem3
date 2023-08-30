#include <stdio.h>

int main(int argc, char **argv)
{
	for (int i = 0; i < 255; i++)
		printf("\033[38;5;%dmHello, world!\n", i);
	
	printf("\033[0m");
}
