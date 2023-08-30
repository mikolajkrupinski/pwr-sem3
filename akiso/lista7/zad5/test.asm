section .data
	mes	dq	'Hello, World', 0x0a, 0x0

section .text
global _start
_start:
	mov	eax, mes
	

end:	mov	eax, 1
	mov	ebx, 0
	int	0x80
