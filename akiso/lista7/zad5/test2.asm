section .data
	mes	db	'Hello, World', 0x0a, 0x0

section .bss
	mes_ptr	resb	4

section .text
global _start
_start:
	mov	ebx, [mes]
	mov	[mes_ptr], ebx
	

end:	mov	eax, 1
	mov	ebx, 0
	int	0x80
