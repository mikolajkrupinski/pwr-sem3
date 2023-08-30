section .data
    x   dd  16.0
    z   dd  0

section .text
    global _start

_start:
    finit

    fld1
    fld     dword[x]
    fyl2x

    fldl2e

    fdivr   st1

    fst     dword[z]

    mov     ebx, 0
    mov     eax, 1
    int     0x80