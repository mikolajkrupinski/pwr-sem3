#include <stdio.h>

struct T
{
	unsigned int *b;
	char c;
	short d[2][7];
};

int main(void)
{
	struct T t[2];
	printf("%d\n", sizeof(t));

	return 0;
}

